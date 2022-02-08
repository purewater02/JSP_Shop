package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;

public class UserQnaUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 고객 QnA글 수정 액션
		
		int bno = Integer.parseInt(request.getParameter("bno").trim());
		String Cont = request.getParameter("QnaCont").trim();
		String Id = request.getParameter("id").trim();
		int Page = Integer.parseInt(request.getParameter("page").trim());
		String writer = request.getParameter("writer").trim();
		
		QnaDTO dto = new QnaDTO();
		dto.setbNo(bno);
		dto.setbCont(Cont);
		dto.setbWriter(writer);
		
		QnaDAO dao = QnaDAO.getInstance();
		int res = dao.qnaUpdate(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("user_board_check.do?id="+Id+"&page"+Page);
		}else {
			out.println("<script>");
			out.println("alert('QnA 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
