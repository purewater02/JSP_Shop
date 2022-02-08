package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.NoticeDAO;
import com.jsp.model.NoticeDTO;

public class AdminNoticeWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bTitle = request.getParameter("bTitle").trim();
		String bWriter = request.getParameter("bWriter").trim();
		String bContent = request.getParameter("bContent").trim();
		
		NoticeDTO dto = new NoticeDTO();
		dto.setbTitle(bTitle);
		dto.setbWriter(bWriter);
		dto.setbCont(bContent);
		
		NoticeDAO dao = NoticeDAO.getInstance();
		int res = dao.writeNotice(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_notice_list.do");
		}else {
			out.println("<script>");
			out.println("alert('공지사항 글쓰기 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
