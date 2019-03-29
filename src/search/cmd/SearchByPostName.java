package search.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.entity.PostEntity;
import search.biz.SearchBiz;
import user.entity.UserEntity;


@WebServlet("/SearchByPostName")
public class SearchByPostName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public SearchByPostName() {
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
		
		String postName = request.getParameter(""); //post name을 파라미터로 받아옴
		SearchBiz biz = new SearchBiz();

		// 모든 리스트 출력
		try {
			ArrayList<PostEntity> searchPost = biz.searchByPostName(postName);
			RequestDispatcher rd = request.getRequestDispatcher("");     //경로
			request.setAttribute("", searchPost);                               //어트리뷰트 이름
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "Post를 검색하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
