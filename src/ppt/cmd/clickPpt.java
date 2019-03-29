package ppt.cmd;

import java.io.IOException;

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
 * Servlet implementation class clickPpt
 */
@WebServlet("/clickPpt")
public class clickPpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public clickPpt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("조회수 올리기 servlet page?");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	
		String pptId = request.getParameter("pptId");
		int viewCount = 0; 
		
		try {
			HttpSession session = request.getSession();
			String userId = ((UserEntity)session.getAttribute("user")).getUserId();
			PptBiz biz = new PptBiz();
			viewCount = biz.getViewCount(pptId);
	
			int result = biz.incViewCount(pptId, viewCount);
			
		
			//ppt 나중에 update 된 like 뿌려주자 ? 
			
		
				
				//RequestDispatcher rd = request.getRequestDispatcher("/user/UserProfile2.jsp");               //경로
				//request.setAttribute("likes", likes+1);
				//rd.forward(request, response);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "조회수 에러 ?.");
			rd.forward(request, response);
		}
	}

}
