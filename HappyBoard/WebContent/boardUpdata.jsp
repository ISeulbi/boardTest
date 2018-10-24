<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.happy.*" %>
<% String btype = request.getParameter("btype");
	BoardDTO data = new BoardDTO();
	if(request.getAttribute("data")!=null){
		data = (BoardDTO)request.getAttribute("data");
	}
%>

<form action="boardUpdata" method="post">
	<input type="hidden" name="btype" value=<%=btype %>>
	<input type="hidden" name="bid" value="<%=data.getBid() %>">
	제목:<input type="text" name="btitle" value="<%=data.getBtitle()%>">
	<br>
	비번:<input type="password" name="pw" value="<%=data.getPw()%>">
	<br>
	<textarea rows="10" cols="30" name="bcontent"><%=data.getBcontent()%></textarea>
	<br>
	<input type="submit" name="submit">
</form>