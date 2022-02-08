package com.jsp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 현재 프로젝트 명과 파일명을 문자열로 반환해줌.
		// "/Project_JSP/*.do" 를 반환.
		String uri = request.getRequestURI();
		
		// 현재 프로젝트 명을 문자열로 반환.
		// Project_JSP가 반환됨.
		String path = request.getContextPath();
		
		String command = uri.substring(path.length() + 1);
		
		Action action = null;
		ActionForward forward = null;
		
		Properties prop = new Properties();		
		
		FileInputStream fis = 
				new FileInputStream("E:\\NCS\\workspace(jsp)\\Project_JSP\\src\\com\\jsp\\controller\\mapping.properties");
		prop.load(fis);
		String value = prop.getProperty(command);
		System.out.println("value>>>"+ value);
		
		if(value.substring(0, 7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value, "|");
			String url_1 = st.nextToken();
			String url_2 = st.nextToken();
			
			/*
			 * 동적 객체 생성: newInstance()
			 * - Class 객체를 이용하면 new 연산자의 사용없이 동적으로 객체 생성이 가능함.
			 * - 코드 작성 시에 클래스의 이름을 결정할 수 없고, 런타임(실행)시에 클래스의 이름이
			 * 		결정되는 경우에 유용하게 사용됨.
			 * - newInstance() 메서드는 기본생성자를 호출해서 객체를 생성.
			 * 		반드시 클래스에 기본생성자가 존재해야 함.
			 * 		해당 클래스가 추상클래스이거나 인터페이스일 경우 예외 발생.
			 * 		또는 접근제한자로 인해 접근할 수 없는 경우에 발생.
			 * 		따라서 예외처리가 필요함.
			 * - 반환 타입은 Object 타입. 형변환 필요.
			 * 		하지만, 클래스의 타입을 모르기 때문에 형변환이 불가능.
			 * 		이 때문에 인터페이스를 이용함.
			 * - Class.forName(class 이름)은 파라미터로 받은 class 이름에 해당하는 클래스를 로딩한 후,
			 * 		그 클래스에 해당하는 인스턴스를 리턴.
			 * 		newInsatance() 메서드는 로딩한 클래스의 객체를 생성하는 메서드, 이렇게 생성된 객체를 동적 객체 생성이라 함.
			 */
			
			try {
				Class url = Class.forName(url_2);
				// 동적으로 객체 생성하는 방법
				action = (Action)url.newInstance();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {//value값이 execute가 아닌 경우
			//view page로 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath(value);
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	}
}
