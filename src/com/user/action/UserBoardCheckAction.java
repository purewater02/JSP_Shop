package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;

import com.jsp.model.Paging;

import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;
import com.jsp.model.ReviewDAO;
import com.jsp.model.ReviewDTO;

public class UserBoardCheckAction implements Action {
 
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 고객 게시물 확인 페이지(Q&A, REVIEW)
		String id = request.getParameter("id").trim();
		String id1 = "admin";
		
		// 고객이 작성한 Q&A 불러오기
		QnaDAO dao1 = QnaDAO.getInstance();
		int totalCount = dao1.getQnaTotal();
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
		List<QnaDTO> list = dao1.getQnaList1(paging, id, id1);
		
		request.setAttribute("paging", paging);
		request.setAttribute("QnaList", list);
		
		
		
		
		
		// 고객이 작성한 리뷰페이지 불러오기
		ReviewDAO dao2 = ReviewDAO.getInstance();
		List<ReviewDTO> list2 = dao2.getReviewList1(id);
		request.setAttribute("ReviewList", list2);
		
		
		
		
		
		
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("user/user_board_check.jsp");
		
		return forward;
	}

}
