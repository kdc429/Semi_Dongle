<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.dongle.group.model.vo.Group" %>
<!DOCTYPE html>
<%
	Group g = (Group)request.getAttribute("group");
%>
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
					style="border: 1px solid red; left: 10%; right: 10%; height: 250px;">
					<img src="<%=request.getContextPath()%><%=g.getImgPath()%>" width="100px" height="100px">
					프로필
					</div>
					
				<div class="sideitem"
					style='border: 1px solid red; left: 10%; right: 10%; height: 150px'><%=g.getGroupName() %></div>
			</div>
		</div>

	</aside>
</body>
</html>