package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.NoticeDAO;
import com.jsp.model.NoticeDTO;

public class AdminNoticeUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));		
		String bTitle = request.getParameter("bTitle").trim();
		String bWriter = request.getParameter("bWriter").trim();
		String bCont = request.getParameter("bContent").trim();
		
		NoticeDTO dto = new NoticeDTO();
		dto.setbNo(bNo);
		dto.setbTitle(bTitle);
		dto.setbWriter(bWriter);
		dto.setbCont(bCont);
		
		NoticeDAO dao = NoticeDAO.getInstance();
		int res = dao.updateNotice(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_notice_list.do");
		}else {
			out.println("<script>");
			out.println("alert('공지사항 수정 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
