package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CategoryDAO;
import com.jsp.model.CategoryDTO;

public class AdminProductInputAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카테고리 코드 전체 리스트를 상품 등록 페이지(View Page)에
		// 전닳라는 비지니스 로직.
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		List<CategoryDTO> list1 = dao.getCategoryList1();
		List<CategoryDTO> list2 = dao.getCategoryList2();
		
		request.setAttribute("categoryList1", list1);
		request.setAttribute("categoryList2", list2);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("admin/admin_product_input.jsp");
		
		return forward;
	}

}
