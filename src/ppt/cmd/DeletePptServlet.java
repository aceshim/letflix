package ppt.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.biz.PostBiz;
import post.entity.PostEntity;
import ppt.biz.PptBiz;
import ppt.entity.PptEntity;
import user.entity.UserEntity;

@WebServlet("/ppt/pptDelete")
public class DeletePptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeletePptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 로그인 안됐을 경우 처리
		UserEntity user = (UserEntity) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/letflix/index.jsp");
		} else {
			String pptId = request.getParameter("pptId");
			PptBiz biz = new PptBiz();
			
			try {
				
				PptEntity thisPpt = biz.selectPpt(pptId);
				if (user.getUserId().equals(thisPpt.getUserId())) {
					doPost(request, response);
				} else {
					throw new Exception();
				}
			} catch(Exception e) {
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Error !");
				request.setAttribute("message", "PPT 파일을 삭제할 수 없습니다.");
				rd.forward(request, response);
				
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		UserEntity user = (UserEntity) request.getSession().getAttribute("user");
		String pptId = request.getParameter("pptId");
		int result = 0;
		PptEntity ppt = null;
		PptBiz biz = new PptBiz();
		PostBiz postBiz = new PostBiz();
		// ppt 정보 삭제
		try {
			PostEntity post = postBiz.selectPostByPptId(pptId);
			String postId = post.getPostId();
			
			// Post 먼저 삭제
			postBiz.deletePost(postId);
			
			// Tagup에서 삭제
			biz.deletePptTag(pptId);
			
			// PPT 삭제
			result = biz.deletePpt(pptId);
			if (result != 0) {
				System.out.println("성공적으로 삭제");
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				System.out.println("삭제 실패");
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Error !");
				request.setAttribute("message", "ppt를 삭제하는 도중 에러가 발생하였습니다.");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/common/errors.jsp");
			request.setAttribute("message", "ppt를 삭제하는 도중 에러가 발생하였습니다.");
			rd.forward(request, response);
		}

	}

}
