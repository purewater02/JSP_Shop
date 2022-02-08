package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.NoticeDAO;
import com.jsp.model.NoticeDTO;
import com.jsp.model.Paging;

public class AdminNoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		NoticeDAO dao = NoticeDAO.getInstance();		
		int totalCount = dao.getNoticeTotal();		
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
		
		List<NoticeDTO> list = dao.getNoticeList(paging);
		request.setAttribute("noticeList", list);
		request.setAttribute("paging", paging);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_notice_list.jsp");
		return forward;
	}

}
