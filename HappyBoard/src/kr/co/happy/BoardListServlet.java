package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btype = request.getParameter("btype");
		String page = request.getParameter("page");
		
		int intbtype = 1;
		int intpage = 1;
		if(btype != null) {
			intbtype = Integer.parseInt(btype);
		}
		request.setAttribute("btype", intbtype);
		
		if(page != null) {
			intpage = Integer.parseInt(page);
		} 
		request.setAttribute("page", intpage);
		
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> dto= dao.getBoardList(intpage, intbtype);
		
		int pageNo = dao.getPageNo(intbtype);
		
		request.setAttribute("pageno", pageNo);
		request.setAttribute("data", dto);
		request.setAttribute("target", "boardList");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
