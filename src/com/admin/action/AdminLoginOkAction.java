package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.AdminDAO;
import com.jsp.model.AdminDTO;

public class AdminLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String admin_id = request.getParameter("admin_id").trim();
		String admin_pwd = request.getParameter("admin_pwd").trim();		
		AdminDAO dao = AdminDAO.getInstance();
		int res = dao.adminCheck(admin_id, admin_pwd);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		//세션을 생성하는 방법
		HttpSession session = request.getSession();		
		
		if(res > 0) {
			//관리자인 경우 관리자의 정보를 받아오기.
			AdminDTO dto = dao.getAdmin(admin_id);
			session.setAttribute("adminId", dto.getId());
			session.setAttribute("adminName", dto.getName());
			//세션 저장된 정보를 가지고 ViewPage로 이동
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
		}else if(res == -1) {
			//아이디는 맞으나 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			//관리자 아이디가 없는 경우
			out.println("<script>");
			out.println("alert('존재하지 않는 관리자입니다...')");
			out.println("history.back()");
			out.println("</script>");
		}		
		return forward;
	}

}
