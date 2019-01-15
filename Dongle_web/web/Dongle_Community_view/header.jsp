<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.dongle.member.model.vo.Member" %>

<!DOCTYPE html>
<html lang="en">
<%
	Member loginMember = (Member) session.getAttribute("loginMember");

%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Dongle_Community.css" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Bungee"
	rel="stylesheet">


</head>

<body>
	<header>
		<div class='logoback' style='width: 1024px; height: auto'>
			<!-- 로고 grid -->
			<div class='logo'
				style='width: 1024px; height: auto; background-color: rgb(228, 228, 228)'>
				<h2
					style="color: darkcyan; margin-left: 15px;">DONGLE</h2>
			</div>
		</div>
	</header>



</body>

</html>