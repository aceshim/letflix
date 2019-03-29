package ppt.cmd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.Util.PptConverter;
import ppt.biz.PptBiz;
import ppt.entity.PptEntity;
import user.entity.UserEntity;


@WebServlet("/ppt/pptCreate")
public class PptRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public PptRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 로그인 안됐을 경우 처리
		UserEntity user = (UserEntity) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/letflix/index.jsp");
		} else {
			doPost(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
		String realPath = request.getSession().getServletContext().getRealPath("upload").replaceAll("\\\\", "/");
		System.out.println("realPath : " + realPath);
		
		// MultipartRequest로 request 전환 // 10MB 용량제한
		response.setContentType("text/html;charset=UTF-8");
		
		
		int result = 0;
		PptEntity ppt = null;
		PptBiz biz = new PptBiz();
		
		// 그룹 정보 입력
		try {	
			String nextPptNum = biz.getNextPptNum();
//			File fileDir = new File(realPath + "/" + nextPptNum);
//			if (!fileDir.exists()) {
//				fileDir.mkdirs();
//			}
			realPath += "/../../../../../../../LETFLIX/WebContent/upload/" + nextPptNum;
			File fileDir = new File(realPath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			MultipartRequest multi = new MultipartRequest(request, realPath, 1024*1024*100, "UTF-8", new DefaultFileRenamePolicy());
			
			@SuppressWarnings("rawtypes")
			Enumeration files = multi.getFileNames();
			
			String realFileName = null;
			while(files.hasMoreElements()) {
//				realFileName = multi.getParameter("pptFile");
				
				String fileType = (String)files.nextElement();
				
				realFileName = multi.getFilesystemName(fileType);
				String realFilePath = realPath;
				System.out.println("realFilePath : " + realFilePath);
				// File 객체 가져오기
				
				File file = new File(realFilePath + "/" + realFileName);
				
				// 변경할 파일명
				String modifyFileName = realFileName;
				String modifyFilePath = realFilePath + "/" + modifyFileName;
				
				file.renameTo(new File(modifyFilePath));
				
				PptConverter converter = new PptConverter(modifyFileName, realFilePath);
				int pptLength = converter.convert();
				
				ppt = new PptEntity();
				ppt.setPptId(nextPptNum);				
				ppt.setUserId(user.getUserId());    				
				ppt.setPptLocation("01.JPG");				 	
				ppt.setPptLength(pptLength);	
				ppt.setPptLike(0);     
				ppt.setPptViews(0);  
				ppt.setPptTitle(modifyFileName);
				result = biz.insertPpt(ppt);
			}	
			
			if(result != 0) {
				response.setStatus(HttpServletResponse.SC_OK);
				PrintWriter out = response.getWriter();
				  out.append(nextPptNum);
				  out.close();
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Error !");
				request.setAttribute("message", "ppt를 생성하는 도중 에러가 발생하였습니다.");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			request.setAttribute("title", "Error !");
			request.setAttribute("message", "ppt를 생성하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
