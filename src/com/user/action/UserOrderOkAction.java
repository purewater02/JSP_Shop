package com.user.action;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDAO;
import com.jsp.model.CartDTO;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;
import com.jsp.model.OrderInfoDAO;
import com.jsp.model.OrderInfoDTO;
import com.jsp.model.OrderProductDAO;

public class UserOrderOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sessionId = request.getParameter("sessionId");		
		String receiver = request.getParameter("receiver").trim();
		String zipcode = request.getParameter("zipcode").trim();
		String addr1 = request.getParameter("address1").trim();
		String addr2 = request.getParameter("address2").trim();
		String rcvPhone = request.getParameter("rcvPhone").trim();
		String payerName = request.getParameter("payerName").trim();
		String Ocard = request.getParameter("cardCo").trim();
		String cardNo = request.getParameter("cardNo").trim();
		String cardPwd = request.getParameter("cardPwd").trim();
		String inputMileage = request.getParameter("inputMileage").trim();
		int mileage = 0;
		if(inputMileage == "") {
			mileage = 0;
		}else {
			mileage = Integer.parseInt(request.getParameter("inputMileage").trim());
		}
		int totOdrPrice = Integer.parseInt(request.getParameter("totOdrPrice"));
		int totalMile = Integer.parseInt(request.getParameter("totalMile"));
		
		OrderInfoDTO odto = new OrderInfoDTO();
		odto.setOid(sessionId);
		odto.setOreceiver(receiver);
		odto.setOaddr("("+zipcode+")"+addr1+addr2);
		odto.setOphone(rcvPhone);
		if(cardPwd != "") {
			odto.setOpay("카드 결제");
			odto.setOcard(Ocard);
		}else {
			odto.setOpay("무통장 입금");
			odto.setOcard(payerName);
		}		
		odto.setOcardno(cardNo);
		odto.setOcardpwd(cardPwd);
		odto.setOstate("배송 준비 중");
		
		OrderInfoDAO odao = OrderInfoDAO.getInstance();
		int count = odao.insertOrderInfo(odto); //orderNo		
		System.out.println("현재 cartNo>>>"+request.getParameter("cartNo"));
		
		CartDAO cdao = CartDAO.getInstance();
		OrderProductDAO opdao = OrderProductDAO.getInstance();
		if(request.getParameter("cartNo") == "") {
			List<CartDTO> list = cdao.getCartList(sessionId);			
			System.out.println("if문 실행되었음.");
			request.setAttribute("cartList", list);
			System.out.println("cartList>>>"+list);			 
			opdao.insertOP(list, count, list.size());			
			cdao.deleteCartAll(sessionId);					
		}else {
			//장바구니 삭제 전에 주문 결과 화면 출력을 위해 미리 cartList 생성
			String cartNoQS = request.getParameter("cartNo");	//1,2,3 의 방식으로 checkbox의 value들을 쿼리스트링으로 받아옴
			StringTokenizer stk = new StringTokenizer(cartNoQS, ","); //그걸 다시 배열로 쪼갬
			int[] cartNoArr = new int[stk.countTokens()]; //배열의 크기를 계산해서 int[] 를 생성
			int idx = 0;
			while(stk.hasMoreTokens()) {
				cartNoArr[idx] = Integer.parseInt(stk.nextToken());
				idx++;
			}			
			List<CartDTO> list = cdao.getCartCheckList(cartNoArr, cartNoArr.length);
			System.out.println("else문 실행 되었음.");
			System.out.println("cartList>>>" + list);			
			opdao.insertOP(list, count, cartNoArr.length);			
			request.setAttribute("cartList", list);
			request.setAttribute("cartNoQS", cartNoQS);
			for(int i=0; i<cartNoArr.length; i++) {
				cdao.deleteSelectedCart(cartNoArr[i]);
			}	
		}		
		MemberDAO md = MemberDAO.getInstance();
		md.updateMileage(sessionId, mileage, totalMile);
		MemberDTO mdto = md.MemberCont(sessionId);
		int newMileage = mdto.getMileage();
		String userPhone = mdto.getPhone();				
			
		request.setAttribute("mileage", newMileage);
		request.setAttribute("phone", userPhone);
		request.setAttribute("inputMile", mileage);
		request.setAttribute("totOdrPrice", totOdrPrice);
		request.setAttribute("totalMile", totalMile);
		request.setAttribute("oNo", count);
		
		ActionForward forward = new ActionForward();		
		forward.setRedirect(false);
		forward.setPath("user/user_order_result.jsp");
		
		return forward;
	}

}
