package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.NoticeDAO;
import com.jsp.model.NoticeDTO;

public class UserNoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 유저 공지사항 페이지에서 게시물 상세 정보 불러오기
		
		int bNo = Integer.parseInt(request.getParameter("no"));
		int cPage = Integer.parseInt(request.getParameter("page"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		NoticeDTO dto = dao.getNoticeDetail(bNo);
		
		request.setAttribute("noticeDetail", dto);
		request.setAttribute("cPage", cPage);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("user/user_notice_detail.jsp");
		
		
		return forward;
	}

}
