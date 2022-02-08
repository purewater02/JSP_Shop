package com.user.action;

import java.io.IOException; 
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String userId = request.getParameter("userId").trim();
		String userPwd = request.getParameter("userPwd").trim();
		String userName = request.getParameter("userName").trim();
		String userPhone = request.getParameter("userPhone").trim();
		String userEmail = request.getParameter("userEmail").trim();
		String addr1 = request.getParameter("address1").trim();
		String addr2 = request.getParameter("address2").trim();
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(userId);
		dto.setPwd(userPwd);
		dto.setName(userName);
		dto.setPhone(userPhone);
		dto.setEmail(userEmail);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int res = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res>0) {
			out.println("<script>");
			out.println("alert('회원가입에 성공했습니다. 가입하신 아이디로 로그인해주세요.')");
			out.println("location.href='main.jsp'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원가입에 실패했습니다ㅜ')");
			out.println("history.back()");
			out.println("</script>");

		}
		
		return null;
	}

}
