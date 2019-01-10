<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member"%>
<%

    	Member loginMember = (Member) session.getAttribute("loginMember");
    	Cookie[] cookies = request.getCookies();
    	String cookieValue = "";
    	if (cookies != null) {
    		for (Cookie c : cookies) {
    			if (c.getName().equals("saveId")) {
    				cookieValue = c.getValue();
    			}
    		}

    	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Dongle.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Dongle_Main.css" />
<link href="https://fonts.googleapis.com/css?family=Bungee"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Serif:700" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<header>
        <div class="headerBack">
            <div class="bar"><%=loginMember.getMemberId()%>님, 환영합니다!</div>
            <div class="logo">DONGLE
            </div>
            <div class="bar">로고 BAR</div>
        </div>
    </header>
</body>
</html>