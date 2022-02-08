package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int res = dao.userCheck(userId, userPwd);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		if(res > 0) {
			MemberDTO dto = dao.MemberCont(userId);	// 아이디, 비번 일치할 경우 해당 회원에 대한 정보를 가져오는 메서드 호출
			session.setAttribute("loggedId", dto.getId());	// 회원정보 조회 후 가져온 아이디와 이름을 세션에 저장해준다. 
			session.setAttribute("loggedName", dto.getName());
			
			forward.setRedirect(false);
			forward.setPath("main.jsp");
			
		} else if(res==-1) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다. 다시 한번 확인해주세요!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('존재하지 않는 아이디입니다. 다시 한번 확인해주세요!')");
			out.println("history.back()");
			out.println("</script>");
		}	
		
		return forward;
	}

}
