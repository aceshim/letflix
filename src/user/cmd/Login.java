package user.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.biz.UserBiz;
import user.entity.UserEntity;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		UserBiz biz = new UserBiz();
		UserEntity user = null;
		HttpSession session = null;

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		try {

			user = biz.login(id, pwd);

			if (user != null) {
				// session에 저장
				session = request.getSession();
				session.setAttribute("user", user);
				// 어디로 redirect 할까요 ?
				if (user.getLastestLoginDate() != null) {
					response.sendRedirect("post/postList");
				} else {
					//RequestDispatcher rd = request.getRequestDispatcher("/le/user/selectTag");
					RequestDispatcher rd = request.getRequestDispatcher("/TermsOfUse.html");
					rd.forward(request, response);
				}
			} else { // user 가 없을 경우 메세지 처리?
				request.setAttribute("message", "아이디가 없거나 비밀번호가 일치하지 않습니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {

		}

	}

}
