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
import user.entity.UserEntity;

/**
 * Servlet implementation class PostUpdateFormServlet
 */
@WebServlet("/post/postUpdateForm")
public class PostUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String postId = request.getParameter("id");
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
		
		String postId = request.getParameter("id");
		PostBiz biz = new PostBiz();
		PostEntity post = null;
		ArrayList<PptEntity> myPpts = null;
		PptBiz pptBiz = new PptBiz();
		try {
			post = biz.selectPost(postId);
			myPpts = pptBiz.selectPptList(((UserEntity)request.getSession().getAttribute("user")).getUserId());
			RequestDispatcher rd = request.getRequestDispatcher("/post/postUpdate.jsp");
			request.setAttribute("post", post);
			request.setAttribute("pptList", myPpts);
			rd.forward(request, response);
		} catch(Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			request.setAttribute("title", "Error !");
			request.setAttribute("message", "포스트를 불러오는 도중 오류가 발생했습니다.");
			rd.forward(request, response);
		}
	}

}
