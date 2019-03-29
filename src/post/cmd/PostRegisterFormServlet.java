package post.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group.biz.GroupBiz;
import group.entity.GroupEntity;
import ppt.biz.PptBiz;
import ppt.entity.PptEntity;
import user.entity.UserEntity;

/**
 * Servlet implementation class PostRegisterFormServlet
 */
@WebServlet("/post/postCreateForm")
public class PostRegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostRegisterFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String groupId = request.getParameter("groupId");
		GroupEntity thisGroup = null;
		ArrayList<PptEntity> myPptList = null;
		GroupBiz groupBiz = new GroupBiz();
		PptBiz pptBiz = new PptBiz();
		
		try {
			thisGroup = groupBiz.selectGroup(groupId);
			myPptList = pptBiz.selectPptList(((UserEntity)request.getSession().getAttribute("user")).getUserId());
			
			RequestDispatcher rd = request.getRequestDispatcher("/post/postCreate.jsp");
			request.setAttribute("group", thisGroup);
			request.setAttribute("pptList", myPptList);
			rd.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
			request.setAttribute("title", "Error !");
			request.setAttribute("message", "알 수 없는 에러가 발생했습니다.");
			rd.forward(request, response);
		}
	}

}
