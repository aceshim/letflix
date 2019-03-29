package ppt.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ppt.biz.PptBiz;
import ppt.entity.PptEntity;
import user.entity.UserEntity;


@WebServlet("/UpdatePptServlet")
public class UpdatePptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdatePptServlet() {
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
		PptEntity ppt = new PptEntity();
		PptBiz biz = new PptBiz();
		
		// 태그 정보 수정
		try {
			ppt.setPptId(request.getParameter(""));						 	//파라미터 이름
			ppt.setUserId(request.getParameter(""));             			//파라미터 이름
			ppt.setPptDate(request.getParameter(""));					 	//파라미터 이름
			ppt.setPptLocation(request.getParameter(""));				 	//파라미터 이름
			ppt.setPptLength(Integer.parseInt(request.getParameter("")));	//파라미터 이름
			ppt.setPptLike(Integer.parseInt(request.getParameter("")));     //파라미터 이름
			ppt.setPptViews(Integer.parseInt(request.getParameter("")));    //파라미터 이름
			
			result = biz.updatePpt(ppt);
			
			if(result != 0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("");     //경로
				request.setAttribute("", ppt);                               //어트리뷰트 이름
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
				request.setAttribute("message", "ppt를 수정하는 도중 에러가 발생하였습니다.");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "ppt를 수정하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
