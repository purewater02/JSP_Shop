package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;

public class UserOrderDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * int oNo = Integer.parseInt(request.getParameter("oNo")); int pNo =
		 * Integer.parseInt(request.getParameter("pNo"));
		 */
		String sessionId = request.getParameter("sessionId");		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user_cart_list.do?sessionId="+sessionId);
		
		return forward;
	}

}
