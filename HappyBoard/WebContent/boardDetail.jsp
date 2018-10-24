<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.happy.*" %>
<% BoardDTO data = (BoardDTO)request.getAttribute("data"); 
%>
<a href="javascript:history.back();">뒤로 가기</a>

<script type="text/javascript">
	function frm(str) {
		if(str=="delete"){
			form1.action = "boardDelete";
		} else if(str=="modify"){
			form1.action = "boardCheck";
		}
		
	}
</script>

<table>
	<tr>
		<td><%=data.getSeq() %></td><td><%=data.getBtitle() %></td><td><%=data.getBregdate() %></td>
	</tr>
	<tr>
		<td colspan="3">
		<%=data.getBcontent() %>
		</td>
	</tr>
		
</table>
<div>
<form name="form1" method="post">
<input type="hidden" name="bid" value="<%=data.getBid() %>">
<input type="hidden" name="btype" value="<%=data.getBtype() %>">
<input type="hidden" name="checkpw" value="<%=data.getPw() %>">
<input type="text" name="pw" value="${pwfalse}">
<input type="submit" value="삭제" onclick='frm("delete");'>
<input type="submit" value="수정" onclick='frm("modify");'>
</form>
</div>
