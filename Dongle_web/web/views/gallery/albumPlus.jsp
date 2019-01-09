<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String groupNo=(String)request.getAttribute("groupNo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앨범 추가하기</title>
<style>
section#albumPlus-container{padding:0 auto;margin:10px;}
section#albumPlus-container input{font-size:18px;font-family:'a흑진주L';}
section#albumPlus-container input[type=submit]{background-color:rgb(0,30,50);color:white;border:none;}
</style>
</head>
<body>
	<section id="albumPlus-container">
		<form action="<%=request.getContextPath()%>/albumNameCheck" name="albumPlusFrm" method="post">
			앨범 명:&nbsp;<input type="text" name="albumNameP" id="albumNameP" size="20"/>
			<input type="submit" value="확인"/>
			<input type="hidden" value=<%=groupNo %>/>
		</form>
	</section>
</body>
</html>