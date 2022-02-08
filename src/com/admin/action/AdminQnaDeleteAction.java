package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;

public class AdminQnaDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bNo = Integer.parseInt(request.getParameter("no"));		
		QnaDAO dao = QnaDAO.getInstance();
		int res = dao.deleteQna(bNo);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_qna_list.do");
		}else {
			out.println("<script>");
			out.println("alert('Q & A 삭제 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}	 /*
			 * else if(res == -1) { out.println("<script>");
			 * out.println("alert('글 비밀번호가 틀렸습니다...')"); out.println("history.back()");
			 * out.println("</script>"); }
			 */
		return forward;
	}

}
