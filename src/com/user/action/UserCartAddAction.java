package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDAO;
import com.jsp.model.CartDTO;

public class UserCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pImage = request.getParameter("pImage");
		String pName = request.getParameter("pName");
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));		
		int pMileage = Integer.parseInt(request.getParameter("pMileage"));
		String pSize = request.getParameter("pSize");
		String pColor = request.getParameter("pColor");
		int pAmount = Integer.parseInt(request.getParameter("pAmount"));
		String sessionId = request.getParameter("sessionId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int totPrice = pAmount * pPrice;		
		
		CartDTO dto = new CartDTO();
		dto.setpImage(pImage);
		dto.setpName(pName);
		dto.setpPrice(pPrice);
		dto.setpMileage(pMileage);
		dto.setpSize(pSize);
		dto.setpColor(pColor);
		dto.setpAmount(pAmount);
		dto.setSessionId(sessionId);
		dto.setpNo(pNo);
		dto.setTotPrice(totPrice);
		
		CartDAO dao = CartDAO.getInstance();
		int res = dao.insertCart(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {			
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do?sessionId="+sessionId);
		}else {
			out.println("<script>");
			out.println("alert('장바구니 추가 실패...')");
			out.println("history.back()");
			out.println("</script>");
		}			
		return forward;
	}

}
