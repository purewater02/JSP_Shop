package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;

public class UserQnaMypageSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 마이페이지 qna 검색 기능
		String id = request.getParameter("id").trim();
		String search_field = request.getParameter("search_field").trim();
		String search_keyword = request.getParameter("search_keyword").trim();
		
		QnaDAO dao = QnaDAO.getInstance();
		
		List<QnaDTO> searchList = dao.searchQnaList(id, search_field, search_keyword);
		request.setAttribute("SearchList", searchList);
		request.setAttribute("id", id);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_qna_search.jsp");
		
		return forward;
	}

}
