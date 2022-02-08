package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserMypageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 멤버 DB 받아오기
		
		String id = request.getParameter("id").trim();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		
		
		MemberDTO dto = dao.MemberCont(id);
		
		request.setAttribute("memberList", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("user/mypage.jsp");
		
		return forward;
	}

}
