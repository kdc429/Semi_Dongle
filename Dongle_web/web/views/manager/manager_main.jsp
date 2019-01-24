<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member, com.dongle.group.model.vo.Group" %>

<%
	int groupNo=(int)request.getAttribute("groupNo");
	Member loginMember = (Member)session.getAttribute("loginMember");
	Group g = (Group)request.getAttribute("group");
	
	String address = g.getLocMetroName() + " " + g.getLocAreaName() + (g.getLocTownName()!=null?" " +g.getLocTownName():"");
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
</style>
<div class="manager-container">
	
	<h2 id="set-head">동글 관리자 페이지</h2>

	<div class="panel panel-default" style="margin-top: 5%; width: 600px">
		<div class="panel-heading">동글 관리</div>
		<div class="panel-body">
			<a data-toggle="modal" data-target="#change-dongle-modal">동글 정보
				수정</a>
		</div>
		<div class="modal fade"></div>
		<div class="panel-body">동글 메인 꾸미기</div>
		<div class="panel-heading">동글 회원관리</div>
		<div class="panel-body">신고 회원 관리</div>
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
				<div class="modal-body" style="padding: 40px 50px;">
					<form >
						<div class="form-group">
							<label for="donglename">동호회 이름</label> 
								<input type="text" class="form-control" id="donglename"> <br>
							<label for="topic">주제</label><br> 
							<select	class="form-control" id="topic">
								<option value="인문/과학">인문/과학</option>
								<option value="스포츠/레저">스포츠/레저</option>
								<option value="여행/캠핑">여행/캠핑</option>
								<option value="문화/예술">문화/예술</option>
								<option value="취업/자격증">취업/자격증</option>
								<option value="외국어/어학">외국어/어학</option>
								<option value="건강/다이어트">건강/다이어트</option>
								<option value="게임/오락">게임/오락</option>
							</select> <br> 
							<label for="address">지역</label><br> 
								<input type="text" class="form-control" id="address"  value="<%=address %>" readonly>
								<button type="button" class="btn btn-info">검색</button><br>
							
							<!-- <select	class="form-control" id="area">
								<option value="경기도">경기도</option>
								<option value="서울">서울</option>
								<option value="강원도">강원도</option>
								<option value="경상북도">경상북도</option>
								<option value="경상남도">경상남도</option>
								<option value="전라북도">전라북도</option>
								<option value="전라남도">전라남도</option>
								<option value="제주도">제주도</option>
							</select> <br>  -->
							<label>성별</label><br>
							<div class="radio">
								<label class="radio-inline">
									<input type="radio" name="gender" value="남자" checked>남자
								</label>
								<label class="radio-inline">
									<input type="radio" name="gender" value="여자">여자
								</label> 
								<label class="radio-inline">
									<input type="radio" name="gender" value="무관">무관
								</label>
							</div>
							<br> 
							<label>활동 시간대</label><br>
							<div class="radio">
								<label class="radio-inline">
									<input type="radio" name="activetime" value="주중" checked>주중
								</label> 
								<label class="radio-inline">
									<input type="radio" name="activetime" value="주말">주말
								</label> 
								<label class="radio-inline">
									<input type="radio" name="activetime" value="무관">무관
								</label>
							</div>

							<label for="">연령대</label><br>
							<div class="col-sm-3">
								<input type="number" class="form-control" min="1" max="100" placeholder="최소">
							</div>
							<div class="col-sm-1">~</div>
							<div class="col-sm-3">
								<input type="number" class="form-control" min="1" max="100" placeholder="최대">
							</div>
						</div>


					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-default" data-dismiss="modal">완료</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

				</div>
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
