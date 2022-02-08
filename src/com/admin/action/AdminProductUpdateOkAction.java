package com.admin.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.jsp.controller.Action;
import com.jsp.controller.ActionForward;
import com.jsp.model.ProductDAO;
import com.jsp.model.ProductDTO;

public class AdminProductUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProductDTO dto = new ProductDTO();
		
		// 첨부파일이 저장될 위치(경로)를 설정
		String saveFolder = "E:\\NCS\\workspace(jsp)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Project_JSP\\upload";
		
		// 첨부파일 용량(크기) 제한 - 파일 업로드 최대 크기
		int fileSize = 100 * 1024 * 1024; // 100MB
		
		// 이진 파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
		);
		
		// 상품 등록 폼 페이지에서 넘어온 데이터들을 받아 주어야 한다.
		
		int pNo = Integer.parseInt(multi.getParameter("pNo"));
		String ctg1 = multi.getParameter("Ctg1").trim();
		String ctg2 = multi.getParameter("Ctg2").trim();
		
		String pCode = multi.getParameter("pCode").trim();
		
		String pName = multi.getParameter("pName").trim();
		
		int pPrice = Integer.parseInt(multi.getParameter("pPrice").trim());
		
		int pSold = Integer.parseInt(multi.getParameter("pSold").trim());

		String[] sizes = multi.getParameterValues("pSize");
		
		String pSize = "";
		for(int i=0; i<sizes.length; i++)
		{
			pSize +=sizes[i];
			pSize +="|";
		}
		
		String pColor = multi.getParameter("pColor").trim();
		
		String pContent = multi.getParameter("pContent").trim();
		
		int pMileage = Integer.parseInt(multi.getParameter("pMileage").trim());
		
		
		File pImage1_new = multi.getFile("pImage1_new");
		
		if(pImage1_new != null) {	// 첨부 파일이 존재하는 경우
			
			
			String homedir = saveFolder+"\\"+ctg1+"\\"+ctg2;
			
			
			File path1 = new File(homedir);
						
			if(!path1.exists()) {	// 폴더가 존재하지 않는 경우
				path1.mkdirs();		// 실제 폴더를 만드는 메서드.
			}
			
			String filePath = homedir + "/" + pCode + "_thumb.jpg";
			
			System.out.println(filePath);
			
			File f = new File(filePath); // 파일 객체생성
			if(f.exists()) {
				f.delete(); // 파일이 존재하면 파일을 삭제한다.
			}
			
			
			String reFileName = pCode+"_thumb.jpg";
			
			pImage1_new.renameTo(new File(homedir+"/"+reFileName));
			
			String fileDBName = "/"+ctg1+"/"+ctg2+"/"+reFileName;
						
			dto.setPimage1(fileDBName);
		}
		
		if(pImage1_new == null) {	// 새로운 첨부파일 이미지가 없는 경우
			String pImage1_old = multi.getParameter("pImage1_old");
			
			dto.setPimage1(pImage1_old);
		}
		
		File pImage2_new = multi.getFile("pImage2_new");
		
		if(pImage2_new != null) {	// 첨부 파일이 존재하는 경우
			
			
			String homedir = saveFolder+"\\"+ctg1+"\\"+ctg2;
			
			
			File path1 = new File(homedir);
						
			if(!path1.exists()) {	// 폴더가 존재하지 않는 경우
				path1.mkdirs();		// 실제 폴더를 만드는 메서드.
			}
			
			String filePath = homedir + "/" + pCode + "_cont.jpg";
			
			System.out.println(filePath);
			
			File f = new File(filePath); // 파일 객체생성
			if(f.exists()) {
				f.delete(); // 파일이 존재하면 파일을 삭제한다.
			}
			
			
			String reFileName = pCode+"_cont.jpg";
			
			pImage2_new.renameTo(new File(homedir+"/"+reFileName));
			
			String fileDBName = "/"+ctg1+"/"+ctg2+"/"+reFileName;
						
			dto.setPimage2(fileDBName);
		}
		
		if(pImage2_new == null) {	// 새로운 첨부파일 이미지가 없는 경우
			String pImage2_old = multi.getParameter("pImage2_old");
			
			dto.setPimage2(pImage2_old);
		}
		
		
		dto.setPno(pNo);
		dto.setCtg1(ctg1);
		dto.setCtg2(ctg2);
		dto.setPcode(pCode);
		dto.setPname(pName);
		dto.setPprice(pPrice);
		dto.setPsold(pSold);
		dto.setPsize(pSize);
		dto.setPcolor(pColor);
		dto.setPcontent(pContent);
		dto.setPmileage(pMileage);
		
		ProductDAO dao = ProductDAO.getInstance();
	
		int check = dao.updateProduct(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_control.do");
		} else {
			out.println("<script>");
			out.println("alert('상품 정보 수정 실패..')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
