package com.user.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDTO;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;
import com.jsp.model.OrderInfoDAO;
import com.jsp.model.OrderInfoDTO;
import com.jsp.model.OrderProductDAO;

public class UserOrderDirectOkAction implements Action {

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
		
		String pImage = request.getParameter("image");
		String pName = request.getParameter("name");
		String pSize = request.getParameter("size");
		String pColor = request.getParameter("color");
		int totPrice = Integer.parseInt(request.getParameter("totPrice"));
		int pAmount = Integer.parseInt(request.getParameter("amount"));
		int pMileage = Integer.parseInt(request.getParameter("mileage"));
		
		CartDTO dto = new CartDTO();
		dto.setpImage(pImage);
		dto.setpName(pName);
		dto.setpSize(pSize);
		dto.setpColor(pColor);
		dto.setTotPrice(totPrice);
		dto.setpAmount(pAmount);
		dto.setpMileage(pMileage);
		
		OrderProductDAO opdao = OrderProductDAO.getInstance();		
		List<CartDTO> list = new ArrayList<CartDTO>();
		list.add(dto);		
		request.setAttribute("cartList", list);
		System.out.println("cartList>>>"+list);			 
		opdao.insertOP(list, count, list.size());						
				
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
