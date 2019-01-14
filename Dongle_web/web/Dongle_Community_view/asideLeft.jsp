<%@page import="com.dongle.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.dongle.group.model.vo.Group, com.dongle.member.model.vo.Member" %>
<!DOCTYPE html>
<%
	Group g = (Group)request.getAttribute("group");
	Member m = (Member)request.getAttribute("loginMember");
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
				style='height: 100vh; background-color: rgb(228, 228, 228)'>
				<div class="sideitem"
					style="border: 1px solid rgba(255,0,0,0.1); left: 10%; right: 10%; height: 250px;">
					<!-- 동글 프로필 -->
					<img class="profile_img" src="<%=request.getContextPath()%>/images/group_profile/<%=g.getImgPath()%>" width="100px" height="100px">
					
					<!-- 동글이름 -->
					<p class="dongle_name"><%=g.getGroupName()%></p>
				<!-- 단추 -->
				<div class='profile_btn'>
					<span class="demoSpan1"></span>
				</div>
				<!-- 회원정보 -->
				<div class="user_info">
					<table>
						<tr>
						<td style="width: 70px"><img src="<%=request.getContextPath()%>/images/member_img/<%=m.getMemberImaPath()%>" width="50px" height="50px"></td>						
						<td><%=m.getMemberName() %></td>
						</tr>
					
					</table>
<%-- 					<p><%=m.getMemberName() %></p><br/>
					<img src="<%=request.getContextPath()%>/images/member_img/<%=m.getMemberImaPath()%>" width="50px" height="50px"> --%>
				</div>
				</div>
				<!-- 소개글 -->
				<div class="sideitem"
					style='border: 1px solid rgba(255,0,0,0.1); margin: 100px 15px 0px 15px; left: 10%; right: 10%; height: 150px;'>
					<%=g.getGroupIntro() %>
				</div>
			</div>
		</div>
		<script>
			$(function() {
				var flag = true;
				$('.profile_btn').click(function() {
					if(flag){
						$(this).next().slideDown();
						flag=false;
					}
					else{
						$(this).next().slideUp();
						flag=true;
					}
					
			})
				
			});
		</script>

	</aside>
</body>
</html>