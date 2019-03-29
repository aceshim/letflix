package user.cmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tag.entity.TagEntity;
import user.biz.UserBiz;
import user.entity.UserEntity;

/**
 * Servlet implementation class selectUserTagsServlet
 */
@WebServlet("/user/selectUserTagsServlet")
public class selectUserTagsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectUserTagsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// 로그인 안됐을 경우 처리
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
		response.setContentType("text/html;charset=UTF-8");
		ArrayList <TagEntity> userTagList = null; 
	
		
		try {
			HttpSession session = request.getSession();
			UserBiz biz = new UserBiz();
			
			UserEntity user = (UserEntity) session.getAttribute("user");
			
			
			if (user != null) {
				String userId = user.getUserId();
				
				
				userTagList = biz.selectUserTagList(userId);
				
				RequestDispatcher rd = request.getRequestDispatcher("/user/UserProfile2.jsp");               //경로
				request.setAttribute("userTagList", userTagList);
				rd.forward(request, response);
			} else {
				//System.out.println("user가 없습니다.");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "관심사 태그를 불러오는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
	}

}
