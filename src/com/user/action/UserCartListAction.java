package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDAO;
import com.jsp.model.CartDTO;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sessionId = request.getParameter("sessionId");	
		MemberDAO md = MemberDAO.getInstance();
		MemberDTO mdto = md.MemberCont(sessionId);
		int userMileage = mdto.getMileage();
		CartDAO dao = CartDAO.getInstance();
		List<CartDTO> list = dao.getCartList(sessionId);			
		request.setAttribute("cartList", list);		
		request.setAttribute("mileage", userMileage);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_cart.jsp");
		return forward;
	}

}
