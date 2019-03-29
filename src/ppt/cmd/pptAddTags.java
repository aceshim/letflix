package ppt.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ppt.biz.PptBiz;
import tag.biz.TagBiz;

/**
 * Servlet implementation class pptAddTags
 */
@WebServlet("/ppt/addTags")
public class pptAddTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pptAddTags() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 로그인 안됐을 경우 처리
//		UserEntity user = (UserEntity) request.getSession().getAttribute("user");
//		if (user == null) {
//			response.sendRedirect("/letflix/index.jsp");
//		} else {
//			doPost(request, response);
//		}
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		TagBiz biz = new TagBiz();
		String param = request.getParameter("tags");
		System.out.println(param);
		
		try {
			int result = 0;
			PptBiz pptBiz = new PptBiz();
			String pptId = pptBiz.getLastPptNum();
			for (String tagName: param.split(",")) {
				System.out.println(tagName);
					
				if (biz.insertTagByName(tagName) > 0) {

					System.out.println(tagName+" 입력성공");
					result++;
				}
				result = biz.insertPptTag(pptId, tagName);
			}
			if(result != 0) {
				response.setStatus(HttpServletResponse.SC_OK);
				PrintWriter out = response.getWriter();
			    out.append(pptId);
			    out.close();
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/common/message.jsp");
				request.setAttribute("title", "Error !");
				request.setAttribute("message", "ppt를 생성하는 도중 에러가 발생하였습니다.");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
