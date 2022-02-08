package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ProductDAO;
import com.jsp.model.ProductDTO;
import com.jsp.model.RecentlyDAO;
import com.jsp.model.RecentlyDTO;

public class UserProductContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		String sessionId = request.getParameter("sessionId");
		System.out.println("sessionId>>>"+sessionId);
		
		ProductDAO dao = ProductDAO.getInstance();
		
		ProductDTO dto = dao.productCont(pNo);
		
		request.setAttribute("productCont", dto);
		
		RecentlyDAO rdao = RecentlyDAO.getInstance();
		
		if (sessionId != "") {
			rdao.insertRecently(pNo, sessionId);
		}
		
		List<RecentlyDTO> list = rdao.getRecently(sessionId);
		
		request.setAttribute("recentlyList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("user/user_product_cont.jsp");
		
		return forward;
	}

}
