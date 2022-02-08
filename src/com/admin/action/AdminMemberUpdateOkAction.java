package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class AdminMemberUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String id = request.getParameter("id").trim();		
		String pwd = request.getParameter("pwd").trim();		
		String name = request.getParameter("name").trim();		
		String phone = request.getParameter("phone").trim();		
		String email = request.getParameter("email").trim();		
		String addr1 = request.getParameter("addr1").trim();		
		String addr2 = request.getParameter("addr2").trim();		
		int mileage = Integer.parseInt(request.getParameter("mileage").trim());		
		String regdate = request.getParameter("regdate").trim();
		
	
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setPhone(phone);
		dto.setEmail(email);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setMileage(mileage);
		dto.setRegdate(regdate);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.updateMember(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_member_control.do");
		} else {
			out.println("<script>");
			out.println("alert('회원 정보 수정 실패..')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
