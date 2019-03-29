package post.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import post.biz.PostBiz;
import post.entity.PostEntity;
import ppt.biz.PptBiz;
import user.entity.UserEntity;

/**
 * Servlet implementation class postDetail
 */
@WebServlet("/post/postDetail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/letflix/index.jsp");
		} else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String postId = (String) request.getParameter("id");
		PostBiz biz = new PostBiz();
		PptBiz pptBiz = new PptBiz();
		
		
		try {
			HttpSession session = request.getSession();
			
			String userId = ((UserEntity)session.getAttribute("user")).getUserId();
			
			
			PostEntity post = biz.selectPost(postId);
			
			int liked = pptBiz.likedBefore(post.getPptId(), userId);
			int likes = pptBiz.getLike(post.getPptId());
			
			int viewCount = pptBiz.getViewCount(post.getPptId());
			pptBiz.incViewCount(post.getPptId(), viewCount);
			viewCount =  pptBiz.getViewCount(post.getPptId());
					
			//biz.insertReadHistory(userId, postId);
			
			if (post != null) {
				RequestDispatcher rd = request.getRequestDispatcher("/post/postDetail.jsp");
				//RequestDispatcher rd = request.getRequestDispatcher("/userProfileServlet");
				request.setAttribute("post", post);
				request.setAttribute("liked", liked);
				request.setAttribute("likes", likes);
				request.setAttribute("viewCount", viewCount);
				rd.forward(request, response);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
