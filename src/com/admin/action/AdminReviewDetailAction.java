package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ReviewDAO;
import com.jsp.model.ReviewDTO;

public class AdminReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("no"));	
		int cPage = Integer.parseInt(request.getParameter("page"));
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewDTO dto = dao.getReviewDetail(bNo);
		request.setAttribute("ReviewDetail", dto);
		request.setAttribute("cPage", cPage);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_review_detail.jsp");
		return forward;
	}

}
