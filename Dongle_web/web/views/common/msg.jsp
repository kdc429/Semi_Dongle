<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 알림</title>

<script>
	alert('<%=msg%>');
	location.href='<%=request.getContextPath()+loc%>';
</script>
</head>
<body>

</body>
</html>