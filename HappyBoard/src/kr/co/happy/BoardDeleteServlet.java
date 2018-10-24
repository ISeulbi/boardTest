package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("btype");
		int btype = Integer.parseInt(s); 
		String pw = request.getParameter("pw");
		String str = request.getParameter("bid");
		String checkPw = request.getParameter("checkpw");
		int bid = Integer.parseInt(str);
		BoardDAO dao = BoardDAO.getInstance();
		
		if(pw.equals(checkPw)) {
			dao.setDelete(bid);
			response.sendRedirect("boardList.do?btype="+btype);
			
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
