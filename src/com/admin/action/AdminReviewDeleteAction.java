package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ReviewDAO;

public class AdminReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("no"));		
		ReviewDAO dao = ReviewDAO.getInstance();
		int res = dao.deleteReview(bNo);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_review_list.do");
		}else {
			out.println("<script>");
			out.println("alert('Review 삭제 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}	
		return forward;
	}

}
