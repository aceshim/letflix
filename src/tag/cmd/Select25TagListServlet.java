package tag.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tag.biz.TagBiz;
import tag.entity.TagEntity;
import user.entity.UserEntity;


@WebServlet("/user/selectTag")
public class Select25TagListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select25TagListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String userId = ((UserEntity)request.getSession().getAttribute("user")).getUserId();
		TagBiz biz = new TagBiz();
		UserEntity user = (UserEntity)request.getSession().getAttribute("user");
		
		
		//25개 리스트 출력
		try {
			ArrayList<TagEntity> userTagList = biz.selectUserTag(user.getUserId());
			ArrayList<TagEntity> tagList = biz.select25TagList();
			RequestDispatcher rd = request.getRequestDispatcher("/user/selectTag.jsp");               //경로
			request.setAttribute("tagList", tagList);
			request.setAttribute("userTagList", userTagList);
			rd.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "25개 태그를 불러오는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
