package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ReviewDAO;
import com.jsp.model.ReviewDTO;

public class UserReviewWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bWriter = request.getParameter("bWriter");
		int bStar = Integer.parseInt(request.getParameter("bStar"));
		String bPwd = request.getParameter("bPwd");
		
		String pname = request.getParameter("pName");
		int ono = Integer.parseInt(request.getParameter("oNo"));
		System.out.println("ono  = " + ono);
		
		ReviewDAO dao = ReviewDAO.getInstance();
		
		ReviewDTO dto = new ReviewDTO();
		
		dto.setbTitle(bTitle);
		dto.setbCont(bContent);
		dto.setbWriter(bWriter);
		dto.setBstar(bStar);
		dto.setbPwd(bPwd);
		
		int res = dao.reviewWrite(dto, pname, ono);		
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('리뷰가 등록되었습니다.')");
			out.println("history.go(-2)");
			out.println("</script>");
		} else if(res == -2) {
			out.println("<script>");
			out.println("alert('이미 리뷰를 작성하셨습니다.')");
			out.println("history.go(-2)");
			out.println("</script>");
		} 

		return forward;
	}

}
