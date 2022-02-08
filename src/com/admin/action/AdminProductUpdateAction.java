package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CategoryDAO;
import com.jsp.model.CategoryDTO;
import com.jsp.model.ProductDAO;
import com.jsp.model.ProductDTO;

public class AdminProductUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 상품 번호에 해당하는 상품의 상세 내역을
		// 조회하여 수정 폼 페이지로 상세 내역을 전달하는 비지니스 로직.
		
		int pNo = Integer.parseInt(request.getParameter("pNo").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO dto = dao.productCont(pNo);
		
		CategoryDAO cdao = CategoryDAO.getInstance();
		List<CategoryDTO> list1 = cdao.getCategoryList1();
		List<CategoryDTO> list2 = cdao.getCategoryList2();
		
		request.setAttribute("productDto", dto);
		request.setAttribute("categoryList1", list1);
		request.setAttribute("categoryList2", list2);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_update.jsp");
		
		return forward;
	}

}
