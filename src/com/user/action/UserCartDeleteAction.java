package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDAO;

public class UserCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		String sessionId = request.getParameter("sessionId");
		CartDAO dao = CartDAO.getInstance();
		int res = dao.deleteSelectedCart(cartNo);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do?sessionId="+sessionId);
		}else {
			out.println("<script>");
			out.println("alert('제품 삭제 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
