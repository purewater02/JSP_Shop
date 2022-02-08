package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserFindIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO id = dao.MemberFindId(userName, userPhone);
		
		request.setAttribute("Id", id);
		
		ActionForward forward = new ActionForward();

		forward.setRedirect(false);
		forward.setPath("user/user_findIdResult.jsp");
		
		return forward;

	}

}
