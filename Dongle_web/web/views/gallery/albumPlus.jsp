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
section#albumPlus-container{padding:0 auto;margin-left:120px; margin-top:30px;}
section#albumPlus-container input{font-size:18px;font-family:'a흑진주L';}
section#albumPlus-container input[type=submit]{color:gray;border:none;font-family:'a흑진주L';}
</style>
</head>
<body>
	<section id="albumPlus-container">
		<form action="<%=request.getContextPath()%>/albumNameCheck" name="albumPlusFrm" method="post">
			<table>
				<tr>
					<td>앨범 명:&nbsp;<input type="text" name="albumNameP" id="albumNameP" size="20"/></td>
					<td><input type="submit" value="확인"/>
					<input type="hidden" value=<%=groupNo %>/></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>