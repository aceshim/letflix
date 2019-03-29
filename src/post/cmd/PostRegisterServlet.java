package post.cmd;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import group.biz.GroupBiz;
import group.entity.GroupEntity;
import post.biz.PostBiz;
import post.entity.PostEntity;
import ppt.biz.PptBiz;
import ppt.entity.PptEntity;
import user.entity.UserEntity;


@WebServlet("/post/postCreate")
public class PostRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PostRegisterServlet() {
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
		UserEntity user = (UserEntity) request.getSession().getAttribute("user");

		request.setCharacterEncoding("UTF-8");		
		response.setContentType("text/html;charset=UTF-8");
		
		
		int result = 0;
		PostEntity post = new PostEntity();
		PostBiz biz = new PostBiz();
		PptBiz pptBiz = new PptBiz();
		GroupBiz groupBiz = new GroupBiz();
		// 태그 정보 입력  --> 필요한 파라미터만 사용하시면 됩니다.
		try {
			PptEntity ppt = pptBiz.selectPpt(request.getParameter("pptId"));
			GroupEntity group = groupBiz.selectGroup(request.getParameter("groupId"));
			// DAO에서 추가 : postId 채번, createDate - SYSDATE, tagList
			post.setUserId(user.getUserId());                    
			post.setUserName(user.getUserName());            
			post.setUserCompany(user.getUserCompany());             
			post.setUserPosition(user.getUserPosition());             
			post.setGroupId(request.getParameter("groupId"));                  
//			post.setGroupName(group.getGroupName());                 
			post.setPptId(request.getParameter("pptId"));                         
			post.setTitle(request.getParameter("title"));                 
			post.setLength(ppt.getPptLength());
			post.setThumbnail("01.JPG");                  
			post.setDescription(request.getParameter("description"));          
			
			// TODO: pptId 채번하는 코드 따로 분리?
			result = biz.insertPost(post);
			
			if(result != 1) {
				throw new Exception();
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Success !");
				request.setAttribute("message", "성공적으로 포스트를 추가했습니다.");
				rd.forward(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			request.setAttribute("title", "Error !");
			request.setAttribute("message", "태그를 수정하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
