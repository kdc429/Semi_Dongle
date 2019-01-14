<%@page import="com.dongle.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.dongle.group.model.vo.*, com.dongle.member.model.vo.Member" %>
<!DOCTYPE html>
<%
	Group g = (Group)request.getAttribute("group");
	Member m = (Member)request.getAttribute("loginMember");
	GroupMember gm = (GroupMember)request.getAttribute("groupMember");
	int result = (int)request.getAttribute("result");
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
				<div class="sideitem1"
					style="border: 1px solid rgba(255,0,0,0.1); left: 10%; right: 10%; height: 250px;">
					<!-- 동글 프로필 -->
					<img class="profile_img" src="<%=request.getContextPath()%>/images/group_profile/<%=g.getImgPath()%>" width="100px" height="100px">
					
					<!-- 동글이름 -->
					<p class="dongle_name"><%=g.getGroupName()%></p>
					<!-- 멤버 회원수 / 그룹멤버 전체 보기-->
					<div class="dongle_info">
						<div id="dongle_info_view" style="font-size: 11px;">회원수 : <%=result %> 명 &nbsp;</div>
						<div id="dongle_info_view">
							<button id="dongle_mem_btn">멤버보기</button>
						</div>
					</div>
					<br/>
					
				<!-- 단추 -->
				<div class='profile_btn'>
					<span class="demoSpan1"></span>
				</div>
				<!-- 회원정보 -->
				<div class="user_info">
					<table border="1px solid black">
						<tr>
						<td rowspan="2"><img id="user_img" src="<%=request.getContextPath()%>/images/member_img/<%=m.getMemberImaPath()%>"></td>						
							<td class="gm_info">이름 : <%=m.getMemberName() %></td>
						</tr>
						<tr>
							<td class="gm_info" style="font-size: 9px">가입일 : <%=gm.getGroupMemberEnrollDate() %></td>
						</tr>
						<tr> 							
							<td colspan="2" class="gm_info">닉네임 : <%=gm.getGroupMemberNickname() %></td>
						</tr>					
					</table>

				</div>
				<!-- 소개글 -->
				<div class="sideitem2"
					style='border: 1px solid rgba(255,0,0,0.1);  right: 10%; height: 150px'>
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