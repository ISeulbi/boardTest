package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardRegMod")
public class BoardRegModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardRegModServlet() {
        super();
    }
    
    //board insert

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("btype");
		int btype = Integer.parseInt(str);
		request.setAttribute("target", "boardRegMod");
		request.setAttribute("btype", btype);
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		String str = request.getParameter("btype");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int btype = Integer.parseInt(str);
		dao.setBoardInsert(btype, btitle, bcontent, pw);
		
		response.sendRedirect("boardList?btype="+btype);
	}
}
