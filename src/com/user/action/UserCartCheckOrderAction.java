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

public class UserCartCheckOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sessionId = request.getParameter("sessionId");
		String cartNoQS = request.getParameter("cartNo");	//1,2,3 의 방식으로 checkbox의 value들을 쿼리스트링으로 받아옴
		StringTokenizer stk = new StringTokenizer(cartNoQS, ","); //그걸 다시 배열로 쪼갬
		int[] cartNoArr = new int[stk.countTokens()]; //배열의 크기를 계산해서 int[] 를 생성
		int idx = 0;
		while(stk.hasMoreTokens()) {
			cartNoArr[idx] = Integer.parseInt(stk.nextToken());
			idx++;
		}		
		MemberDAO md = MemberDAO.getInstance();
		MemberDTO mdto = md.MemberCont(sessionId);
		int userMileage = mdto.getMileage();
		String userPhone = mdto.getPhone();
		CartDAO dao = CartDAO.getInstance();
		List<CartDTO> list = dao.getCartCheckList(cartNoArr, cartNoArr.length);		
		System.out.println("cartList>>>" + list);
		request.setAttribute("cartList", list);		
		request.setAttribute("mileage", userMileage);
		request.setAttribute("phone", userPhone);
		request.setAttribute("cartNoQS", cartNoQS);		
		ActionForward forward = new ActionForward();		
		forward.setRedirect(false);
		forward.setPath("user/user_order.jsp");
		
		return forward;	
	}

}
