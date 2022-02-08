<%@page import="com.jsp.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <% String userId = request.getParameter("paramId").trim(); 
    	
    MemberDAO dao = MemberDAO.getInstance();
    
   	int res = dao.checkMemberId(userId);
   	
   	// ajax에게 응답해주자. res값 리턴
   	out.println(res); 
    %>