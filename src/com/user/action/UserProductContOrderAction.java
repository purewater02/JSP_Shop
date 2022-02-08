package com.user.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.CartDTO;
import com.jsp.model.MemberDAO;
import com.jsp.model.MemberDTO;

public class UserProductContOrderAction implements Action {

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
						
		MemberDAO md = MemberDAO.getInstance();
		MemberDTO mdto = md.MemberCont(sessionId);
		int userMileage = mdto.getMileage();
		String userPhone = mdto.getPhone();		
		List<CartDTO> list = new ArrayList<CartDTO>();
		list.add(dto);
		request.setAttribute("cartList", list);		
		request.setAttribute("mileage", userMileage);
		request.setAttribute("phone", userPhone);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_order_direct.jsp");
		return forward;		
	}

}
