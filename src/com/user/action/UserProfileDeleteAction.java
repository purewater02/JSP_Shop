package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;

public class UserProfileDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 고객 개인정보 수정 페이지에서 회원탈퇴 버튼 클릭 시 DB에서 고객 정보 지우는 액션
		
		String id = request.getParameter("id").trim();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int res = dao.deleteMember(id);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			HttpSession session = request.getSession();
			session.invalidate();  // 세션 만료 함수 
			forward.setRedirect(true);
			forward.setPath("main.do");
		} else {
			out.println("<script>");
			out.println("alert('계정 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
