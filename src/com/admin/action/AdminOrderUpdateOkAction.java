package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.OrderInfoDAO;
import com.jsp.model.OrderProductDAO;

public class AdminOrderUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 상품 번호에 해당하는 상품의 상세 내역을
		// 조회하여 수정 폼 페이지로 상세 내역을 전달하는 비지니스 로직.
		
		int oNo = Integer.parseInt(request.getParameter("oNo"));
		String pNoList = request.getParameter("pNoList");
		String oState1List = request.getParameter("oState1List");
		String oState2 = request.getParameter("oState2");
		System.out.println("pNoList>>>"+pNoList);
		System.out.println("oState1List>>>"+oState1List);
		System.out.println("oState2>>>"+oState2);
		
		StringTokenizer stk = new StringTokenizer(pNoList, ",");
		int[] pNoArr = new int[stk.countTokens()];
		int idx1 = 0;
		while(stk.hasMoreTokens()) {
			pNoArr[idx1] = Integer.parseInt(stk.nextToken());
			idx1++;
		}
		
		StringTokenizer stk2 = new StringTokenizer(oState1List, ",");
		String[] oState1Arr = new String[stk2.countTokens()];
		int idx2 = 0;
		while(stk2.hasMoreTokens()) {
			oState1Arr[idx2] = stk2.nextToken();
			idx2++;
		}
		
		OrderInfoDAO dao = OrderInfoDAO.getInstance();
		OrderProductDAO odao = OrderProductDAO.getInstance();
		
		int res1 = dao.updateOrder(oNo, oState2);
		int res2 = odao.updateOrder(oNo, pNoArr, oState1Arr, pNoArr.length);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(res1 > 0 && res2 > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_order_control.do");
		}else {
			out.println("<script>");
			out.println("alert('주문내역 수정 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	
	}

}
