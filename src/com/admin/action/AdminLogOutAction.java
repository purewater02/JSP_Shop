package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;

public class AdminLogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate(); //세션을 만료시킴.
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("main.jsp");
		return forward;
	}

}
