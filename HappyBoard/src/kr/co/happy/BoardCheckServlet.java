package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardCheck")
public class BoardCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("pw")!=null&&request.getParameter("checkpw")!=null) {
			String pw = request.getParameter("pw");
			String str = request.getParameter("bid");
			int bid = Integer.parseInt(str);
			String checkPw = request.getParameter("checkpw");
			BoardDAO dao = BoardDAO.getInstance();
			
			if(pw.equals(checkPw)) {
				BoardDTO data = dao.getBoardDetail(bid);
				request.setAttribute("target", "boardUpdata");
				request.setAttribute("data", data);
				RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
				rd.forward(request, response);
			}else {
				BoardDTO data = dao.getBoardDetail(bid);
				request.setAttribute("pwfalse", "false");
				request.setAttribute("data", data);
				request.setAttribute("target", "boardDetail");
				RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
				rd.forward(request, response);
			}
		}
	}

}
