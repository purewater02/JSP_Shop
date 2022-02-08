package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDAO;

public class UserCartAmountUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		int pAmount = Integer.parseInt(request.getParameter("pAmount"));		
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		String sessionId = request.getParameter("sessionId");
		CartDAO dao = CartDAO.getInstance();
		int res = dao.updateCartOption(cartNo, pAmount, pPrice);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do?sessionId="+sessionId);
		}else {
			out.println("<script>");
			out.println("alert('수량 변경 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
