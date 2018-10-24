<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.happy.*" %>

<form action="boardRegMod" method="post">
	<input type="hidden" name="btype" value="${btype }">
	제목:<input type="text" name="btitle" >
	<br>
	비번:<input type="password" name="pw">
	<br>
	<textarea rows="10" cols="30" name="bcontent"></textarea>
	<br>
	<input type="submit" name="submit">
</form>