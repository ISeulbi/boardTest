<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/common.css">
<title>${target}</title>
</head>
<body>
<jsp:include page="top_menu.jsp"></jsp:include>
<div class="content">
<jsp:include page="${target}.jsp"></jsp:include>
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>