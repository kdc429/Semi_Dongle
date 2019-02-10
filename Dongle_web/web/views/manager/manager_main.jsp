<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member, com.dongle.group.model.vo.Group,
com.dongle.group.model.vo.MultiLocation, com.dongle.group.model.vo.MultiTopic,
com.dongle.group.model.vo.TopicCtg, java.util.*" %>

<%
	int groupNo=(int)request.getAttribute("groupNo");
	Member loginMember = (Member)session.getAttribute("loginMember");
	Group g = (Group)request.getAttribute("group");
	List<MultiLocation> locList = (List)request.getAttribute("locList");
	List<MultiTopic> topicList = (List)request.getAttribute("topicList");
	List<TopicCtg> topicCtg = (List)request.getAttribute("topicCtg");
	
	String address = g.getLocCtgCode();
%>
<meta charset="UTF-8">


<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script>
	var topicCnt = 0;
	var topicArr = [];
	var topicFlag = false;
	$(function(){
		$("#addtopic-btn").click(function(){
			if(topicCnt > 2)
			{
				alert("토픽은 최대 3개만 가능합니다.")	
			}
			else
			{
				var topicCode = $("#selectTopic option:selected").val();
				var topicName = $("#selectTopic option:selected").text();
				
				for(var i = 0; i < topicArr.length; i++)
				{
					if(topicArr[i] == topicName)
					{
						topicFlag = true;
					}
					
				}
				if(!topicFlag)
				{
					var div = $("#topic-add-div");
					var str = "<span id='topic-span"+topicCnt+"' onclick='fn_delTopic("+topicCnt+")'><a class='aTopic'>"
					+topicName+"&nbsp&times</a>"+"<input type='hidden' name='topicCode' value='"+topicCode+"'>"
					+"<input type='hidden' name='topicName' value='"+topicName+"'></a></span>";
						
					div.append(str);
					
					topicArr.push(topicName);
					console.log("topicArr" + topicArr);
					topicCnt++; 
					
				}
				else
				{
					alert("같은 토픽은 선택할 수 없습니다.");
					topicFlag = false;
				}
				
			}
		});
	});
	
	function fn_delTopic(index)
	{
		var spanId = "#topic-span" + index;
		
		$(spanId).remove();
		topicCnt--;
		
		topicArr.splice(index,1);
		
	}
	function validate(){
		console.log($("#checkPwd").val()+" : <%=loginMember.getMemberPwd()%>");
		if($("#checkPwd").val() != '<%=loginMember.getMemberPwd()%>')
		{
			alert("비밀번호가 일치하지 않습니다.");
			return;	
		}
		modelFrm.submit();
	}
	
	
</script>
<style>
	input#address{display:"inline-block"}
	a.aTopic{padding-right:15px;}
</style>
<div class="manager-container">
	
	<h2 id="set-head">동글 관리자 페이지</h2>

	<div class="panel panel-default" style="margin-top: 5%; width: 600px">
		<div class="panel-heading">동글 관리</div>
		<div class="panel-body">
			<a data-toggle="modal" data-target="#change-dongle-modal">동글 정보
				수정</a>
		</div>
		<div class='panel-body'>
			<a id='update-main-image'>동글 메인 변경</a>
		</div>
		<div class="modal fade"></div>
		<div class="panel-heading">동글 회원관리</div>
		<div class="panel-body"><a id="delete-member-btn">회원 탈퇴 관리</a></div>
		<div class="panel-body"><a id="report-member-btn">신고 회원 관리</a></div>
	</div>
	<div class="panel panel-default" style="width: 600px">
		<div class="panel-body">
			<a style="color: red; font-style: italic;" data-toggle="modal"
				data-target="#remove-dongle-modal">동글 삭제</a>
		</div>
	</div>


	<!-- 동글 정보 수정창(modal) -->
	<div class="modal fade" id="change-dongle-modal" role="dialog">
		<div class="modal-dialog">


			<div class="modal-content">
				<div class="modal-header" style="padding: 35px 50px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3>
						<span class="glyphicon glyphicon-cog"></span> 동글 정보 수정
					</h3>
				</div>
				<form action="<%=request.getContextPath() %>/manager/updateDongle?groupNo=<%=groupNo %>" method="post">
				<div class="modal-body" style="padding: 40px 50px;">
					
						<div class="form-group">
							<label for="donglename">동호회 이름</label> 
								<input type="text" class="form-control" name="dongleName"id="dongleName"> <br>
							<label for="topic">토픽</label><br> 
							<select	class="form-control" style="padding_bottm:2px; width:79%; display:inline" name="selectTopic" id="selectTopic" >
								<%for(TopicCtg topic : topicCtg){ %>
								<option value="<%=topic.getTopicCtgCode()%>"><%=topic.getTopicCtgName() %></option>
								<%} %>
								
							</select>
							<button type="button" class="btn btn-info" id="addtopic-btn" style="width:20%; height:34px;">토픽 추가</button>
							<br>
							<div id="topic-add-div" style="height:19px">
									
							</div>
							<br>
							<label>지역</label><br> 
							<select	class="form-control" style="padding_bottm:2px; width:33%; display:inline" name="selectMetro" id="selectMetro" >
								<%for(TopicCtg topic : topicCtg){ %>
								<option value="<%=topic.getTopicCtgCode()%>"><%=topic.getTopicCtgName() %></option>
								<%} %>
								
							</select>
								<%-- <input type="text" class="form-control" id="address" name="address" value="<%=address %>" readonly>
								<button type="button" id="search-address" class="btn btn-info">검색</button><br> --%>

							<br> 
							<label>활동 시간대</label><br>
							<div class="radio">
								<label class="radio-inline">
									<input type="radio" name="activetime" value="주중" checked>주중
								</label> 
								<label class="radio-inline">
									<input type="radio" name="activetime" value="주말">주말
								</label> 
							</div>
							<br>
							<label for>연령대</label><br>
							
								<input type="number" class="form-control" style="width:20%; display:inline;"name="minAge" id="minAge" min="1" max="100" placeholder="최소">
							
							&nbsp~&nbsp
							
								<input type="number" class="form-control" style="width:20%; display:inline;" name="maxAge" id="maxAge" min="1" max="100" placeholder="최대">
							
							<br><br><br>
							
							<label>소개글</label>
							<textarea class="form-control" rows="5" name="intro" id="intro"></textarea>
						</div>


					
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-default" data-dismiss="modal">완료</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

				</div>
				</form>
			</div>

		</div>
	</div>



	<!-- 동글 삭제 확인창(modal) -->
	<div class="modal fade" id="remove-dongle-modal" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">동글 삭제</h4>
				</div>
				<div class="modal-body">
					<p>정말로 동글을 삭제하시겠습니까?</p>
					<p>삭제한 동글은 복구가 불가능합니다.</p>
					<br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"  data-toggle="modal" data-target="#check-modal"data-dismiss="modal">예</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">아니오</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 매니저 비밀번호 확인창(modal) -->
	<div class="modal fade" id="check-modal" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">비밀번호 확인</h4>
				</div>
				<form name='modelFrm' action="<%=request.getContextPath() %>/manager/deleteDongle" method="post">
					<div class="modal-body">
						<label for="checkPwd">비밀번호 입력</label><br>
						<input type="password" class="form-control" name="checkPwd" id="checkPwd">
						
						<input type="hidden" name="groupNo" value="<%=groupNo%>"/>
						<input type="hidden" name="memberPwd" value="<%=loginMember.getMemberPwd() %>"/>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" onclick="validate()">예</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">아니오</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script>
/* 동글 정보변경 -> 주소 검색 */
$(function(){
	$("#search-address").click(function(){
		var url="<%=request.getContextPath()%>/views/manager/manager_searchAddress.jsp";
		var title="주소 검색";
		var shape="left=200px, top=100px, width=600px, height=700px";
		
		var popup=open(url,title,shape);
	})
});

/* 신고관리버튼 */
$(function(){
	$("#report-member-btn").click(function(){
		var managerNo=<%=g.getMemberNo()%>;
		
		console.log(<%=g.getMemberNo()%>);
		$.ajax({
			url:"<%=request.getContextPath()%>/manager/reportMemberView?groupNo=<%=g.getGroupNo()%>",
			type:"post",
			data:{
				"managerNo":managerNo
			},
			dataType:"html",
			success:function(data){
				$('#content-div').html(data);
			},
			error:function(request){},
			complete:function(){console.log("ok");}
		})
	})
});

/* 멤버탈퇴관리버튼 */
$(function(){
	$("#delete-member-btn").click(function(){
		var managerNo=<%=g.getMemberNo()%>;
		
		console.log("매니저넘버" + <%=g.getMemberNo()%>);
		$.ajax({
			url:"<%=request.getContextPath()%>/manager/deleteMemberView?groupNo=<%=g.getGroupNo()%>",
			type:"post",
			data:{
				"managerNo":managerNo
			},
			dataType:"html",
			success:function(data){
				$('#content-div').html(data);
			},
			error:function(request){},
			complete:function(){console.log("ok");}
		})
	})
});

//동글 메인 이미지 변경
$(function(){
	$('#update-main-image').click(function(){
		var groupNo=<%=groupNo%>
		$.ajax({
			url:"<%=request.getContextPath()%>/dongle/updateMain%>",
			data:{"groupNo":groupNo},
			type:'post',
			success:function(data){
				$('#content-div').html(data);
			};
		});
	});
})

</script>