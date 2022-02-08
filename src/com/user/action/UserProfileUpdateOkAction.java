package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserProfileUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 마이페이지 고객 정보 수정 화면에서 받아온    고객 개인정보 수정 액션
		
		String userId = request.getParameter("id").trim();
		String userPwd = request.getParameter("userPwd").trim();
		String userName = request.getParameter("userName").trim();
		String userPhone = request.getParameter("userPhone").trim();
		String userEmail = request.getParameter("userEmail").trim();
		String address1 = request.getParameter("address1").trim();	
		String address2 = request.getParameter("address2").trim();
		String zipcode = request.getParameter("zipcode").trim();
		 
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(userId);
		dto.setPwd(userPwd);
		dto.setName(userName);
		dto.setPhone(userPhone);
		dto.setEmail(userEmail);
		dto.setAddr1(address1);
		dto.setAddr2(address2);
		dto.setZipcode(zipcode);
		int res = dao.userUpdateMember(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("main.do");
		} else {
			out.println("<script>");
			out.println("alert('개인정보 수정에 실패하셨습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
