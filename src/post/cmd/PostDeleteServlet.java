package post.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.biz.PostBiz;
import post.entity.PostEntity;
import user.entity.UserEntity;


@WebServlet("/post/postDelete")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


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
				request.setAttribute("message", "포스트를 삭제할 수 없습니다.");
				rd.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int result = 0;
		String postId = request.getParameter("postId");
		PostBiz biz = new PostBiz();
		
		try {
			
			result = biz.deletePost(postId);
			
			if (result != 1) {
				throw new Exception();
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Success !");
				request.setAttribute("message", "포스트가 성공적으로 삭제되었습니다.");
				rd.forward(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			request.setAttribute("title", "Error !");
			request.setAttribute("message", "포스트를 삭제하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
