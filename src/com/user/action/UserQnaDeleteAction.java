package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;

public class UserQnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 마이페이지 고객qna 삭제하기
		
		int bGroup = Integer.parseInt(request.getParameter("bgroup").trim());
		String Id = request.getParameter("id").trim();
		int Page = Integer.parseInt(request.getParameter("page").trim());
		
		QnaDAO dao = QnaDAO.getInstance();
		int res = dao.deleteQna1(bGroup);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("user_board_check.do?id="+Id+"&page"+Page);
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
