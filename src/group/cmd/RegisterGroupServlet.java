package group.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group.biz.GroupBiz;
import group.entity.GroupEntity;
import user.entity.UserEntity;


@WebServlet("/RegisterGroupServlet")
public class RegisterGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		GroupEntity group = new GroupEntity();
		GroupBiz biz = new GroupBiz();
		
		// 그룹 정보 입력
		try {	
			group.setGroupId(request.getParameter(""));						 //파라미터 이름
			group.setGroupName(request.getParameter(""));					 //파라미터 이름
			group.setGroupCreateDate(request.getParameter(""));				 //파라미터 이름
			group.setGroupInvitationCode(request.getParameter(""));			 //파라미터 이름
			group.setGroupDescription(request.getParameter(""));             //파라미터 이름
			
			result = biz.insertGroup(group);
			
			if(result != 0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("");       //경로
				request.setAttribute("", group);                               //어트리뷰트 이름
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
				request.setAttribute("message", "그룹을 생성하는 도중 에러가 발생하였습니다.");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "그룹을 생성하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
