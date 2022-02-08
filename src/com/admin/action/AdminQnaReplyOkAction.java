package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;

public class AdminQnaReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		int bStep = Integer.parseInt(request.getParameter("bStep"));
		int bIndent = Integer.parseInt(request.getParameter("bIndent"));
		String bWriter = request.getParameter("bWriter");
		String bTitle = request.getParameter("bTitle".trim());
		String bCont = request.getParameter("bCont").trim();
		String bPwd = request.getParameter("bPwd").trim();
		
		QnaDTO dto = new QnaDTO();
		dto.setbNo(bNo);
		dto.setbGroup(bGroup);
		dto.setbStep(bStep);
		dto.setbIndent(bIndent);
		dto.setbWriter(bWriter);
		dto.setbTitle(bTitle);
		dto.setbCont(bCont);
		dto.setbPwd(bPwd);
		
		QnaDAO dao = QnaDAO.getInstance();
		dao.replyUpdate(bGroup, bStep);
		int res = dao.replyQna(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_qna_list.do");
		}else {
			out.println("<script>");
			out.println("alert('답변 추가 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}				
		return forward;
	}

}
