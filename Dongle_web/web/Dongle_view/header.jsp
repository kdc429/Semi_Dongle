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
<title>당신을 위한 맞춤형 동호회</title>
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
<link href="https://fonts.googleapis.com/css?family=Sunflower:300" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Serif:700" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/icon.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>



<body>
	<header>
        <div class="headerBack">
            <div class="bar">
            	<!-- <button class="search-icon" style="background-color: rgba(255,255,255,0);" > -->
            		<a href="<%=request.getContextPath()%>/main/searchPage"><img src="<%=request.getContextPath()%>/images/button-images/search.png" style="width: 30px; height: 30px;" id='search-btn'></a>
            	<!-- </button> -->
            	<!-- 정보수정 및 로그 아웃 버튼! -->
            	<div class="user-back">
            		<span><%=loginMember.getMemberName()%>님, 환영합니다!</span>
            	</div>&nbsp;&nbsp;
            	<div class="user-back">
            	<!-- 마이페이지 버튼 -->
            		<button class="img-icon">
            			<span class="sub-icon">마이페이지</span>
            			<img class="user-img" src="<%=request.getContextPath() %>/images/button-images/userEdit.png">
            		</button>
            		
            		
            	</div>
            	<div class="user-back">
            	<!-- 로그 아웃 버튼 -->
            		<button class="img-icon">
            			<span class="sub-icon">로그아웃</span>
            			<img class="user-img" src="<%=request.getContextPath()%>/images/button-images/logout.png">
            		</button>
            		
            	</div>
            </div>
            <div class="logo-back">
            	<div style="font-family:'SunFlower'";><h4>당신을 위한 맞춤형 동호회</h4></div>
            	<div class="logo">DONGLE</div>
            	<div class="dongle-guide">
            	<!-- 동글 가이드 버튼 -->
            		<button class="guide-btn" style="width:190px; background-color: rgba(0,0,0,0);">동글 가이드</button>
            	</div>
            </div>
        </div>
        <script>
        </script>
    </header>
</body>
</html>