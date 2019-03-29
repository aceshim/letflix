package tag.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tag.biz.TagBiz;
import tag.entity.TagEntity;
import user.entity.UserEntity;


@WebServlet("/DeleteTagServlet")
public class DeleteTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public DeleteTagServlet() {
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
		TagEntity tag = new TagEntity();
		TagBiz biz = new TagBiz();
		
		// 태그 정보 삭제
		try {
			tag.setTagId(request.getParameter(""));                         //파라미터 이름
			tag.setTagName(request.getParameter(""));                       //파라미터 이름
			
			result = biz.deleteTag(tag);
			
			if(result != 0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("");     //경로
				request.setAttribute("", tag);                               //어트리뷰트 이름
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
				request.setAttribute("message", "태그를 삭제하는 도중 에러가 발생하였습니다.");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "태그를 삭제하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
