package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;

public class UserQnaDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("no"));
		int cPage = Integer.parseInt(request.getParameter("page"));
		QnaDAO dao = QnaDAO.getInstance();
		QnaDTO dto = dao.getQnaDetail(bNo);
		request.setAttribute("qnaDetail", dto);
		request.setAttribute("cPage", cPage);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_qna_cont.jsp");
		return forward;
	}

}
