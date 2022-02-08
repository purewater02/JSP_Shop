package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.OrderInfoDAO;
import com.jsp.model.OrderInfoDTO;
import com.jsp.model.OrderProductDAO;
import com.jsp.model.OrderProductDTO;

public class AdminOrderViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 등록 폼 페이지를 통해서 DB에 저장된 전체 상품 목록을 조회하여
		// 상품 목록 페이지(View Page)로 이동시키는 비지니스 로직.
		
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		
		OrderInfoDAO dao = OrderInfoDAO.getInstance();
		
		OrderInfoDTO dto = dao.getOrderView(oNo);
		
		OrderProductDAO pdao = OrderProductDAO.getInstance();
		
		List<OrderProductDTO> list = pdao.getOrderProductList(oNo);
		
		request.setAttribute("cont", dto);
		
		request.setAttribute("oProdList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("admin/admin_order_view.jsp");
		
		return forward;
	}

}
