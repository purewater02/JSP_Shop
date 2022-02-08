package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;

public class AdminQnaReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("no"));
		int cPage = Integer.parseInt(request.getParameter("page"));
		QnaDAO dao = QnaDAO.getInstance();
		QnaDTO dto = dao.getQnaDetail(bNo);
		request.setAttribute("reply", dto);
		request.setAttribute("cPage", cPage);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_qna_reply.jsp");
		return forward;
	}

}
