package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserFindPwdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO pwd = dao.MemberFindPwd(userName, userId);
		
		request.setAttribute("Pwd", pwd);
		
		ActionForward forward = new ActionForward();

		forward.setRedirect(false);
		forward.setPath("user/user_findPwdResult.jsp");
		
		return forward;
	}

}
