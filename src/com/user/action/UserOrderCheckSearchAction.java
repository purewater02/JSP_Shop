package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.OrderProductDAO;
import com.jsp.model.OrderProductDTO;

public class UserOrderCheckSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String id = request.getParameter("id");
		String oState=request.getParameter("order_status");
	    String date1=request.getParameter("date1");
	    String date2=request.getParameter("date2");
	    System.out.println("searchaction oState>>>"+oState);
	    
	    OrderProductDAO dao = OrderProductDAO.getInstance();
	      
	    List<OrderProductDTO> list = dao.searchOrder(id, oState, date1, date2);
	    
	    request.setAttribute("ordList", list);
	    request.setAttribute("date1", date1);
	    request.setAttribute("date2", date2);
	    request.setAttribute("oState", oState);	    
	    
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("user/user_order_check_result.jsp");
		
		return forward;
	}

}
