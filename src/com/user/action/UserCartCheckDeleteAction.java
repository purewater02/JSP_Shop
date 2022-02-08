package com.user.action;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDAO;

public class UserCartCheckDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sessionId = request.getParameter("sessionId");
		String cartNoQS = request.getParameter("cartNo");	//1,2,3 의 방식으로 checkbox의 value들을 쿼리스트링으로 받아옴
		StringTokenizer stk = new StringTokenizer(cartNoQS, ","); //그걸 다시 배열로 쪼갬
		int[] cartNoArr = new int[stk.countTokens()]; //배열의 크기를 계산해서 int[] 를 생성
		int idx = 0;
		while(stk.hasMoreTokens()) {
			cartNoArr[idx] = Integer.parseInt(stk.nextToken());
			idx++;
		}
		
		CartDAO dao = CartDAO.getInstance();		
		for(int i=0; i<cartNoArr.length; i++) {
			dao.deleteSelectedCart(cartNoArr[i]);
		}
		
		ActionForward forward = new ActionForward();		
		forward.setRedirect(true);
		forward.setPath("user_cart_list.do?sessionId="+sessionId);
		
		return forward;	
	}

}
