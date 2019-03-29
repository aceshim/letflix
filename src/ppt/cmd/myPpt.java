package ppt.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ppt.biz.PptBiz;
import ppt.entity.PptEntity;
import user.entity.UserEntity;


@WebServlet("/ppt/myPpt")
public class myPpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public myPpt() {
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
		
		UserEntity user = (UserEntity) request.getSession().getAttribute("user");
		PptBiz biz = new PptBiz();

		// 모든 리스트 출력
		try {
			ArrayList<PptEntity> pptList = biz.selectPptList(user.getUserId());
			if (pptList != null) {
				RequestDispatcher rd = request.getRequestDispatcher("/ppt/pptList.jsp");     //경로
				request.setAttribute("pptList", pptList);                               //어트리뷰트 이름
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "ppt 리스트를 불러오는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}
		
	}

}
