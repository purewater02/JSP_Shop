package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.Paging;
import com.jsp.model.ReviewDAO;
import com.jsp.model.ReviewDTO;

public class UserComReviewSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String option = request.getParameter("search_option");
		String keyword = request.getParameter("search_input").trim();
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		int totalCount = dao.getReviewFilterTotal(option, keyword);
		int page = 0;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}else {
			page = 1;
		}
		Paging paging = new Paging();		
		paging.setPage(page);		
		paging.setDisplayRow(10);
		paging.setDisplayPage(5);
		paging.setTotalCount(totalCount);
			
		List<ReviewDTO> list = dao.getReviewFilterList(paging, keyword, option);
		request.setAttribute("ReviewList", list);
		request.setAttribute("paging", paging);
		request.setAttribute("option", option);
		request.setAttribute("keyword", keyword);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_community_review_list.jsp");
		return forward;
	}

}
