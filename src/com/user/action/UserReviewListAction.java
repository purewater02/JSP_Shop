package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ReviewDAO;
import com.jsp.model.ReviewDTO;

public class UserReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int rowsize = 5;		// 한 페이지당 보여질 게시물의 수.
		int block = 3;			// 페이지 하단에 보여질 페이지의 최대 수
		int totalRecord = 0;	// DB 상의 게시물 전체 수
		int allPage = 0;		// 전체 페이지 수 
		
		int page = 0; 			// 현재 페이지 변수
		
		if(request.getParameter("page") != null) {	
			page = Integer.parseInt(request.getParameter("page"));
		} else {	// 처음으로 [전체 게시물 목록] 을 클릭한 경우.
			page = 1;
		}
		
		
		// 각 페이지에서 시작 글 번호. 페이지 당 게시물 3개 => 1 / 4 / 7 / ... 19 
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 글 번호.  => 3 / 6 / 8 / ... 21
		int endNo = (page * rowsize);
		
		// 해당 페이지 시작 블럭	
		int startBlock = (((page - 1) / block) * block) + 1;
		
		// 해당 페이지 끝 블럭	
		int endBlock = (((page - 1) / block) * block) + block;
		
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		String pcode = request.getParameter("pcode");
		totalRecord = dao.getReviewCount(pcode);	// 전체 게시물 수
		
		allPage = (int)Math.ceil(totalRecord / (double)rowsize); 
		
		if(endBlock > allPage) {	
			endBlock = allPage;
		}
		
		String pname = request.getParameter("pname");
		List<ReviewDTO> list = dao.getReviewList(pcode, page, rowsize);
	
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", list);

		request.setAttribute("pName", pname);
		request.setAttribute("pCode", pcode);
		
		forward.setRedirect(false);
		forward.setPath("user/user_review_list.jsp");
		
		return forward;
	}

}
