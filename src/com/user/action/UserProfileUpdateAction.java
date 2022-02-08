package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserProfileUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 마이페이지에서 프로필 클릭 시 고객 정보 수정 페이지 불러오기.
		
		String id = request.getParameter("id").trim();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO dto = dao.MemberCont(id);
		
		request.setAttribute("memberList", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		 
		forward.setPath("user/user_profile_update.jsp");
		
		return forward;
	}

}
