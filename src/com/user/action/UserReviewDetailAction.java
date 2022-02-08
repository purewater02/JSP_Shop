package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ProductDAO;
import com.jsp.model.ProductDTO;
import com.jsp.model.ReviewDAO;
import com.jsp.model.ReviewDTO;

public class UserReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 마이페이지 보드 내가 쓴 리뷰 확인하기에서 리뷰 상세정보 불러오기.
		String id = request.getParameter("id").trim();
		String pcode = request.getParameter("pcode").trim();
		
		
		// 유저가 쓴 review 정보 불러오기
		ReviewDAO dao = ReviewDAO.getInstance();
		 ReviewDTO dto = dao.getReviewList2(id);
		request.setAttribute("ReviewList", dto);
		
		
		// review한 제품 정보 불러오기
		ProductDAO dao1 = ProductDAO.getInstance();
		ProductDTO dto1 = dao1.getProductList3(pcode);
		request.setAttribute("ProductList", dto1);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_review_detail.jsp");
		
		return forward;
	}

}
