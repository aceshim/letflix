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
import post.entity.PostListGroupMapEntity;
import tag.entity.TagEntity;
import user.entity.UserEntity;

/**
 * Servlet implementation class PostListServlet
 */
@WebServlet("/post/postTagList")
public class PostTagListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostTagListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = ((UserEntity)request.getSession().getAttribute("user")).getUserId();
		PostBiz biz = new PostBiz();
		
		try {	
			ArrayList<PostListGroupMapEntity> postListGroup = biz.selectPostListGroup(userId);
			RequestDispatcher rd = request.getRequestDispatcher("/post/postTagList.jsp");
			request.setAttribute("postListGroup", postListGroup);
			rd.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "글 목록을 불러오는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
	}

}
