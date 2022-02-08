package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.MemberDAO;
import com.jsp.model.ZipDTO;

public class UserZipCheckOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String dong = request.getParameter("dong");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		List<ZipDTO> list = dao.zipCheck(dong);
		
		request.setAttribute("ZipList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("user/zipCheckOk.jsp");
		
		return forward;
		
	}

}
