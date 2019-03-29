package user.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tag.entity.TagEntity;
import user.biz.UserBiz;
import user.entity.UserEntity;

/**
 * Servlet implementation class userProfileServlet
 */
@WebServlet("/user/userProfile")
public class userProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
		String sUserId = request.getParameter("sUserId");
		if (user == null) {
			response.sendRedirect("/letflix/index.jsp");
		} else {
			ArrayList<TagEntity> tagList = null;
			UserBiz biz = new UserBiz();
			
			try {
				UserEntity sUser = biz.getUserProfile(sUserId);
				tagList = biz.selectUserTagList(sUserId);
				
				RequestDispatcher rd = request.getRequestDispatcher("/user/userProfile3.jsp");
				request.setAttribute("sUser", sUser);
				request.setAttribute("userTagList", tagList);
				rd.forward(request, response);
			} catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Error !");
				request.setAttribute("message", "유저 정보를 불러오는 도중 에러가 발생했습니다.");
				rd.forward(request, response);
			}
		}
		
	}

}
