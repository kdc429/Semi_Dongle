<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member, com.dongle.group.model.vo.Group, com.dongle.member.model.vo.DongleRptMember, java.util.*" %>

<%
	int groupNo=(int)request.getAttribute("groupNo");
	Member loginMember = (Member)session.getAttribute("loginMember");
	Group g = (Group)request.getAttribute("group");
	
	List<DongleRptMember> rptList = (List)request.getAttribute("rptList");
	
	
%>
<meta charset="UTF-8">

<Style>
div#report-member-container
{
	margin : 25px;
}
	#tbl-manager-report {
	width: 630px;
	display: block;
	border-collapse: collapse;
	border: 0px solid black;
}

#tbl-manager-report th {
	border: 1px solid black;
	border-top : 0px;
	border-left : 0px;
	border-right: 0px;
	background-color: gray;
	text-align: center;
	padding: 8px;
	border-collapse: collapse;
}

#tbl-manager-report td {
	border: 1px solid black;
	border-top: 0px;
	border-left : 0px;
	border-right : 0px;
	text-align: left;
	padding: 8px;
	border-collapse: collapse;
}

#tbl-manager-report tbody {
	display: block;
	height: 220px;
	overflow: scroll ;
	overflow-x: hidden;
}

#tbl-manager-report th:nth-of-type(1), #tbl-manager-report td:nth-of-type(1) {
	width: 30px
}

#tbl-manager-report th:nth-of-type(2), #tbl-manager-report td:nth-of-type(2) {
	width: 100px
}

#tbl-manager-report th:nth-of-type(3), #tbl-manager-report td:nth-of-type(3) {
	width: 150px
}

#tbl-manager-report th:nth-of-type(4), #tbl-manager-report td:nth-of-type(4) {
	width: 250px
}

#tbl-manager-report th:nth-of-type(5){
	width: 100px
}
#tbl-manager-report td:nth-of-type(5) {
	width: 80px
}


</Style>

<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	
<div id="report-member-container">
	<h2>신고 회원 관리</h2>
		
		<table id="tbl-manager-report">
			<thead>
				<tr>
					<th></th>
					<th>아이디</th>
					<th>닉네임</th>
					<th>신고 사유</th>
					<th>신고 횟수</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (rptList.size() < 6) {
				%>
				<%for(DongleRptMember m : rptList) {%>
				<tr>
					<td><input type="radio" name="report-member" value="<%=m.getMemberNo()%>,<%=m.getRptCount() %>"></td>
					<td><%=m.getMemberId()%></td>
					<td><%=m.getMemberNickname()%></td>
					<td><%=m.getRptReason()%></td>
					<td><%=m.getRptCount()%></td>
					
				</tr>
				<%
					} 
					for(int i =0; i < 6-rptList.size(); i++)
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
				<%for(DongleRptMember m : rptList) {%>
				<tr>
					<td><input type="radio" name="report-member" value="<%=m.getMemberNo()%>,<%=m.getRptCount() %>"></td>
					<td><%=m.getMemberId()%></td>
					<td><%=m.getMemberNickname()%></td>
					<td><%=m.getRptReason()%></td>
					<td><%=m.getRptCount()%></td>
	
				</tr>
				<%
					}
				}
				
				%>
			</tbody>
		</table>
		<button id="report-btn">신고 승인</button>
	
</div>
<script>
$(function(){
	$("#report-btn").click(function(){
		var managerNo=<%=g.getMemberNo()%>;
		console.log($('[name="report-member"]').val());
		var values = $('[name="report-member"]').val().split(',');
		console.log(values);
		var selectMember = values[0];
		console.log(selectMember);
		var rptCount = values[1];
		console.log(rptCount);
		
		
		$.ajax({
			url:"<%=request.getContextPath()%>/manager/reportMemberSubmit?groupNo=<%=g.getGroupNo()%>",
			type:"post",
			data:{
				"managerNo":managerNo,
				"selectMember":selectMember,
				"rptCount":rptCount
			},
			dataType:"html",
			success:function(data){
				if(rptCount < 2)
				{
					alert("신고 승인이 완료되었습니다.");
				}
				else
				{
					alert("신고 횟수가 3번을 넘어서 블랙처리 되었습니다.");
				}
				$('#content-div').html(data);
			},
			error:function(request){},
			complete:function(){console.log("ok");}
		})
	})
});
</script>
