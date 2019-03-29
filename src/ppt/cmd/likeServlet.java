package ppt.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ppt.biz.PptBiz;
import user.entity.UserEntity;

/**
 * Servlet implementation class likeServlet
 */
@WebServlet("/likeServlet")
public class likeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
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
		
		
	
	
		String pptId = request.getParameter("pptId");
		int likes = 0; 
		
		try {
			HttpSession session = request.getSession();
			String userId = ((UserEntity)session.getAttribute("user")).getUserId();
			PptBiz biz = new PptBiz();
			likes = biz.getLike(pptId);
	
			int result = biz.like(pptId,userId, likes);
			
			likes++;
			//System.out.println("업데이트 된 likes : " + likes);
			
		
			
			String updatedLikes = ""+likes;
			
			
			response.getWriter().write(updatedLikes);
			response.getWriter().close();
			    
			
			//RequestDispatcher rd = request.getRequestDispatcher("/post/postDetail.jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("/user/UserProfile2.jsp");               //경로
			//request.setAttribute("likes2", likes);
			//rd.forward(request, response);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "관심사 태그를 불러오는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
	}

}
