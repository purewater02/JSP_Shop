package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.NoticeDAO;
import com.jsp.model.NoticeDTO;

public class AdminNoticeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("no"));
		int cPage = Integer.parseInt(request.getParameter("page"));
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeDTO dto = dao.getNoticeDetail(bNo);
		request.setAttribute("noticeDetail", dto);
		request.setAttribute("cPage", cPage);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_notice_update.jsp");
		return forward;
	}

}
