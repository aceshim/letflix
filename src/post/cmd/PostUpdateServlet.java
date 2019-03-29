package post.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.biz.PostBiz;
import post.entity.PostEntity;
import ppt.biz.PptBiz;
import ppt.entity.PptEntity;
import tag.entity.TagEntity;
import user.entity.UserEntity;

/**
 * Servlet implementation class PostUpdateServlet
 */
@WebServlet("/post/postUpdate")
public class PostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String postId = request.getParameter("postId");
		// 로그인 안됐을 경우 처리
		UserEntity user = (UserEntity) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/letflix/index.jsp");
		} else {
			// 자기 포스트가 아닌 경우 처리
			PostBiz biz = new PostBiz();
			PostEntity post = null;
			try {
				post = biz.selectPost(postId);
				if (user.getUserId().equals(post.getUserId())) {
					
					doPost(request, response);
				} else {
					throw new Exception();
				}
				
			} catch(Exception e) {
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Error !");
				request.setAttribute("message", "포스트 수정 권한이 없습니다.");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
		
		PostEntity post = new PostEntity();
		post.setPostId(request.getParameter("postId"));
		post.setPptId(request.getParameter("pptId"));
		post.setTitle(request.getParameter("title"));
		post.setDescription(request.getParameter("description"));
		System.out.println(request.getParameter("postId"));
		System.out.println(request.getParameter("pptId"));
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("description"));
		post.setUserId(user.getUserId());
		post.setUserName(user.getUserName());
		post.setUserCompany(user.getUserCompany());
		post.setUserPosition(user.getUserPosition());

		PostBiz biz = new PostBiz();
		PptBiz postBiz = new PptBiz();
		int result = -1;
		try {
			PptEntity ppt = postBiz.selectPpt(request.getParameter("pptId"));
			post.setLength(ppt.getPptLength());
			
			ArrayList<TagEntity> tags = biz.selectTagListByPptId(request.getParameter("pptId"));
			post.setTagList(tags);
			
			result = biz.updatePost(post);
			if (result != 1) {
				throw new Exception();
			} else {				
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Success !");
				request.setAttribute("message", "성공적으로 포스트를 수정하였습니다.");
				rd.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			request.setAttribute("title", "Error !");
			request.setAttribute("message", "포스트를 수정하는 도중 오류가 발생하였습니다.");
		}
		
	}

}
