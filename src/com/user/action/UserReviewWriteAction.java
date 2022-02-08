package com.user.action;

import java.io.IOException; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;

public class UserReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 제품명과 함께 리뷰 작성 폼 페이지로 이동 
		
		String pname = request.getParameter("pname");
		int ono = Integer.parseInt(request.getParameter("ono"));
		System.out.println("ono  = " + ono);
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("pName", pname);
		request.setAttribute("oNo", ono);
		
		forward.setRedirect(false);
		forward.setPath("/user/user_review_write.jsp");
		
		return forward;
	}

}
