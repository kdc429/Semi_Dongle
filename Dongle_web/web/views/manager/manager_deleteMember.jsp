<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member, com.dongle.group.model.vo.Group, com.dongle.group.model.vo.GroupMember, java.util.*" %>

<%
	int groupNo=(int)request.getAttribute("groupNo");
	Member loginMember = (Member)session.getAttribute("loginMember");
	Group g = (Group)request.getAttribute("group");
	
	List<GroupMember> memberList = (List)request.getAttribute("memberList");
	
	
%>
<meta charset="UTF-8">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div id="delete-member-container">
	<h2>멤버 탈퇴 관리</h2>
	<table id="tbl-manager-delete">
			<thead>
				<tr>
					<th></th>
					<th>아이디</th>
					<th>닉네임</th>
					<th>신고 횟수</th>
				</tr>
			</thead>
			<tbody>
			<%
					if (memberList.size() < 6) {
				%>
				<%for(GroupMember m : memberList) {%>
				<tr>
					<td><input type="radio" name="delete-member" value="<%=m.getMemberNo()%>"></td>
					<td><%=m.getGroupMemberNickname()%></td>
					<td><%=m.getGroupMemberEnrollDate()%></td>
					<td><%=m.getReportDongleCount()%></td>
				</tr>
				<%
					} 
					for(int i =0; i < 6-memberList.size(); i++)
					{
				%>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>&nbsp</td>
				</tr>
				<%}
					}else { %>
				<%for(GroupMember m : memberList) {%>
				<tr>
					<td><input type="radio" name="delete-member" value="<%=m.getMemberNo()%>"></td>
					<td><%=m.getGroupMemberNickname()%></td>
					<td><%=m.getGroupMemberEnrollDate()%></td>
					<td><%=m.getReportDongleCount()%></td>
	
				</tr>
				<%
					}
				}
				
				%>
			</tbody>
	</table>
	<button id="delete-btn">회원 탈퇴</button>
</div>

<script>
$(function(){
	$("#delete-btn").click(function(){
		var isDelete = confirm("해당 회원을 탈퇴시키겠습니까?");
		
		if(isDelete){
		var managerNo=<%=g.getMemberNo()%>;
		var selectMemberNo = $('[name="delete-member"]').val()
		
		
		$.ajax({
			url:"<%=request.getContextPath()%>/manager/deleteMemberSubmit?groupNo=<%=g.getGroupNo()%>",
			type:"post",
			data:{
				"managerNo":managerNo,
				"selectMemberNo":selectMemberNo,
			},
			dataType:"html",
			success:function(data){
				alert("해당 회원을 탈퇴했습니다.")
				$('#content-div').html(data);
			},
			error:function(request){},
			complete:function(){console.log("ok");}
		})
		}
	})
});
</script>
