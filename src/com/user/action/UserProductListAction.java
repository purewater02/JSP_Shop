package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ProductDAO;
import com.jsp.model.ProductDTO;

public class UserProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int rowsize = 6;		
		int block = 5;			
		int totalRecord = 0;	
		int allPage = 0;		
		
		int page = 0; 			
		
		if(request.getParameter("page") != null) {	
			page = Integer.parseInt(request.getParameter("page"));
		} else {	
			page = 1;
		}
		
		int startNo = (page * rowsize) - (rowsize - 1);
				
		int endNo = (page * rowsize);
				
		int startBlock = (((page - 1) / block) * block) + 1;
			
		int endBlock = (((page - 1) / block) * block) + block;
		
		
		ProductDAO dao = ProductDAO.getInstance();
		
		totalRecord = dao.getProductCount();	// 전체 상품 수
		
		allPage = (int)Math.ceil(totalRecord / (double)rowsize); 
		
		if(endBlock > allPage) {	
			endBlock = allPage;
		}	
		
		List<ProductDTO> list = dao.getAllProductList(page, rowsize);
		
		ActionForward forward = new ActionForward();
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("productList", list);
		
		forward.setRedirect(false);
		forward.setPath("user/user_product_list.jsp");
		
		return forward;
	}
	
}
