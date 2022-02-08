package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.OrderInfoDAO;
import com.jsp.model.OrderInfoDTO;

public class AdminOrderSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String search_field=request.getParameter("search_field").trim();
	    String search_keyword=request.getParameter("search_keyword").trim();
	    
	    OrderInfoDAO dao=OrderInfoDAO.getInstance();
	      
	    List<OrderInfoDTO> list = dao.searchBoard(search_field, search_keyword);
	    
	    request.setAttribute("orderList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("admin/admin_order_list.jsp");
		
		return forward;
	}

}
