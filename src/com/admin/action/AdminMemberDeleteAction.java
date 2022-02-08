package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;

public class AdminMemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 제품번호에 해당하는 제품을
		// DB에서 삭제하는 비지니스 로직.
		
		String id = request.getParameter("id").trim();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.deleteMember(id);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_member_control.do");
		} else {
			out.println("<script>");
			out.println("alert('회원 삭제 실패..')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
