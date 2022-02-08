package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;

public class UserQnaCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// mypage qnapage에서 qna상세 정보 불러오기
		
		String id = request.getParameter("id").trim();
		int bno = Integer.parseInt(request.getParameter("bno").trim());
		int page = Integer.parseInt(request.getParameter("page").trim());
		
		QnaDAO dao = QnaDAO.getInstance();
		QnaDTO dto = dao.getQnaDetail(bno);
		request.setAttribute("QnaDetail", dto);
		request.setAttribute("Page", page);
		request.setAttribute("id", id);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_qna_check.jsp");
		
		return forward;
	}

}
