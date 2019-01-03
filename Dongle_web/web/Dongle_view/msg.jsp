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
<title>Insert title here</title>

<script>
//서블릿에서 작성한 메세지 호출
	alert('<%=msg%>');
	//호출후 페이지 메인으로 이동
	location.href='<%=request.getContextPath()+loc%>';
</script>
</head>
<body>

</body>
</html>