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

public class AdminOrderUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 상품 번호에 해당하는 상품의 상세 내역을
		// 조회하여 수정 폼 페이지로 상세 내역을 전달하는 비지니스 로직.
		
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		
		OrderInfoDAO dao = OrderInfoDAO.getInstance();
		
		OrderInfoDTO dto = dao.getOrderView(oNo);
		
		OrderProductDAO pdao = OrderProductDAO.getInstance();
		
		List<OrderProductDTO> list = pdao.getOrderProductList(oNo);
		
		request.setAttribute("cont", dto);
		
		request.setAttribute("oProdList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("admin/admin_order_update.jsp");
		
		return forward;
	}

}
