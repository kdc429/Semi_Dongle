<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member"%>

<%
		Member loginMember2 = (Member) session.getAttribute("loginMember");
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
div.bar span{
	color : white;
}
div.user-back #userInfo_btn{
	width: 28px;
	height: 28px;
}
</style>
</head>
<body>
	<header>

		<div class="bar" style="background-color: rgb(20,150,200); width: 1024px; height: 45px;">
     	 	<img src="<%=request.getContextPath() %>/images/button-images/user-img.png" id='userInfo_btn' onclick="memberView();" style="width: 30px; height: 30px; margin-bottom: 5px;">
           	<span style="color:black; margin-bottom: 5px;"><%=loginMember2.getMemberName()%>님, 환영합니다!</span>
            	<div class="user-back">
	            	<img src="<%=request.getContextPath()%>/images/button-images/logout-btn.png" id='logout_btn' onclick="location.href='<%=request.getContextPath()%>/member/logout'" style="height: 25px; width: 25px; margin: 0 10px 5px 5px;">
            		<script>
            			function memberView(){
            				$.ajax({
            					url:"<%=request.getContextPath()%>/Dongle_view/memberView?userId=<%=loginMember2.getMemberId()%>",

            					success:
            						function(data){
            							$('section').html(data);
            					}
            				});
            			}
            		</script>
          		</div>
   		</div>
    </header>
</body>
</html>