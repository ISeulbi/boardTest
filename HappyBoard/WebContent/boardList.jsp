<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "kr.co.happy.*" %>
<%
	ArrayList<BoardDTO> list = (ArrayList<BoardDTO>)request.getAttribute("data");
	int paging = (int)request.getAttribute("page");
	int btype = (int)request.getAttribute("btype");
	int pageNo = (int)request.getAttribute("pageno");
%>

<table>
<tr>
	<td>번호</td><td>제목</td><td>날짜</td>
</tr>
<%
	if(!list.isEmpty()){
		for(BoardDTO x : list){
%>
		<tr>
			<td><%=x.getSeq() %></td>
			
			<td><a href="boardDetail?bid=<%=x.getBid() %>"><%=x.getBtitle() %></a></td>
			
			<td><%=x.getBregdate() %></td>
		</tr>
<%
		}
	} else {
%>
		<tr>
			<td colspan="3">자료없음</td>
		</tr>
<%
	}
%>
</table>
<div class="paging">
<%
	for(int i = 1; i <= pageNo; i++){
		if(i==paging){
			out.print(i);
		} else {
%>
		<a href="boardList?page=<%=i%>"> <%=i %> </a>
<% 
		}
	}
%>
</div>
<div class="link">
	<a href="boardRegMod?btype=<%=btype %>">
	<input type="button" value="글쓰기">
	</a>
</div>