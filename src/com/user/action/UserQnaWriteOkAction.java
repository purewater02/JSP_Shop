package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.QnaDAO;
import com.jsp.model.QnaDTO;

public class UserQnaWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ctg = "["+request.getParameter("ctg_select")+"] ";
		String bTitle = request.getParameter("bTitle").trim();
		String bWriter = request.getParameter("bWriter");
		String bContent = request.getParameter("bContent").trim();
		String bPwd = request.getParameter("bPwd").trim();
		
		QnaDTO dto = new QnaDTO();
		dto.setbCont(bContent);
		dto.setbTitle(ctg+bTitle);
		dto.setbPwd(bPwd);
		dto.setbWriter(bWriter);		
		
		QnaDAO dao = QnaDAO.getInstance();
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		int res = dao.insertQna(dto);
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("user_qna_list.do"); //mypage > qna 리스트			
		}else {
			out.println("<script>");
			out.println("alert('문의사항 작성에 실패했습니다...')");
			out.println("history.back()");
			out.println("</script>");
		}		
		return forward;
	}

}
