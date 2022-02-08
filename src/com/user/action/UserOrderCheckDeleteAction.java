package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.OrderProductDAO;

public class UserOrderCheckDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String sid = request.getParameter("sid");
		int ono = Integer.parseInt(request.getParameter("ono"));
		int pno = Integer.parseInt(request.getParameter("pno"));
		String oState = request.getParameter("ostate");
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		System.out.println("deleteaction oState>>>"+oState);
		
		OrderProductDAO dao = OrderProductDAO.getInstance();		
		dao.deleteOrder(ono, pno);
		
		request.setAttribute("date1", date1);
	    request.setAttribute("date2", date2);
	    request.setAttribute("oState", oState);
		
		ActionForward forward = new ActionForward();		
		forward.setRedirect(true);
		forward.setPath("user_order_check_search.do?id="+sid+"&order_status="+oState+"&date1="+date1+"&date2="+date2);
		
		return forward;	
	}

}
