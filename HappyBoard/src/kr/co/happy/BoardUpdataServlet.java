package kr.co.happy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardUpdata")
public class BoardUpdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdataServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		String s = request.getParameter("bid");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		String str = request.getParameter("btype");
		int btype = Integer.parseInt(str);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int bid = Integer.parseInt(s);
		dao.setBoardUpdata(bid, btitle, bcontent, pw);
		
		response.sendRedirect("boardList?btype="+btype);
	}

}
