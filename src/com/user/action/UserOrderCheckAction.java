package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserOrderCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 마이페이지에서 넘어온 정보를 주문내역 체크 뷰 페이지로 넘겨주자.
		
		String id = request.getParameter("id").trim();
	
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO dto = dao.MemberCont(id);
		
		request.setAttribute("list", dto);

		
		ActionForward forward = new ActionForward();
		
		
		forward.setRedirect(false);
		
		forward.setPath("user/user_order_check.jsp");
		
		
		return forward;
		
	}

}
