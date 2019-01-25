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
<link rel='icon' href='https://i.imgur.com/8k8yVjE.png'>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Dongle.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Dongle_Main.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_memberList.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_dongleList.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Admin_blackMemberList.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="<%=request.getContextPath() %>/css/icon.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
.guide-btn{
	width:120px; 
	background-color: rgba(0,0,0,0); 
/* 	margin-left: 850px; 
	margin-top: 100px; */
	font-family: '여기어때잘난서체';
	border: 1px;
	font-weight: bold;
	font-size: 17px;
	color : white;
}
.search-btn{
	width:100px; 
	background-color: rgba(0,0,0,0);
	font-family: '여기어때잘난서체';
	border: 1px;
	font-weight: bold;
	font-size: 17px;
	margin-bottom: 2px;
	color: white;

}
div.bar span{
	color : white;
}
/* div.bar .search-btn{
	width: 20px;
	height: 20px;
	margin-bottom: 2px;
	margin-right: 3px;
} */
div.user-back #userInfo_btn{
	width: 28px;
	height: 28px;
}
div.user-back #logout_btn{
	width: 25px;
	height: 25xp;
	margin-left: 50px;
}
</style>
</head>

<script>
 	$('#dongleLogoCk'){
		location.href="<%=request.getContextPath()%>/communityJoin?userId=<%=loginMember.getMemberId()%>&password=<%=loginMember.getMemberPwd()%>";
	}
</script>

<body>
	<header>
        <div class="headerBack">
        <div  style="width: 280px; height: 100px; background-color: rgba(0,0,0,0); float: left; margin-top: 70px;" id="dongleLogoCk"></div>
            <div class="bar">
            	<!-- <button class="search-icon" style="background-color: rgba(255,255,255,0);" > -->
            	<img src="<%=request.getContextPath() %>/images/button-images/user-img.png" id='userInfo_btn' onclick="memberView();" style="width: 30px; height: 30px;">
           		
           		<span><%=loginMember.getMemberName()%>님, 환영합니다!</span>
            	<!-- </button> -->
            	<!-- 정보수정 및 로그 아웃 버튼! -->
            	<div class="user-back">
            		
            		<%-- <a href="<%=request.getContextPath()%>/main/searchPage"><img src="<%=request.getContextPath()%>/images/button-images/search-btn.png" id='search-btn'></a> --%>
            		<img src="<%=request.getContextPath()%>/images/button-images/logout-btn.png" id='logout_btn' onclick="location.href='<%=request.getContextPath()%>/member/logout'">
            		
            		<script>
            			function memberView(){
            				$.ajax({
            					url:"<%=request.getContextPath()%>/Dongle_view/memberView?userId=<%=loginMember.getMemberId()%>",
            					success:
            						function(data){
            							$('section').html(data); //header에 section으로 이동
            					}
            				});
            			}
            		</script>
            	</div>&nbsp;&nbsp;
            </div>
            <div style="width: 300px; float: right; margin-top: 55px; margin-right: 60px;">
	           	<button class="search-btn" onclick="<%=request.getContextPath()%>/main/searchPage">동글 검색</button>
	           	<button class="guide-btn">동글 가이드</button>
           	</div>
        </div>
        <script>
        </script>
    </header>
</body>
</html>