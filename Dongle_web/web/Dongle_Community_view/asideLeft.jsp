<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member,com.dongle.group.model.vo.Group"%>
<%
    	Member loginMember = (Member) session.getAttribute("LoginMember");
    	Group group=(Group)request.getAttribute("group");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>asideLeft</title>

</head>
<body>
	<aside>
		<div class="sideback center">
			<!-- 왼쪽 사이드 -->
			<div class="sidel"
				style='height: 100vh; background-color: rgba(130, 238, 41, 0.8)'>
				<div class="sideitem"
					style="border: 1px solid red; left: 10%; right: 10%; height: 250px;">프로필</div>
				<div class="sideitem"
					style='border: 1px solid red; left: 10%; right: 10%; height: 150px'><%=group.getGroupName() %></div>
			</div>
		</div>

	</aside>
</body>
</html>