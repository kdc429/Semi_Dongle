<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Bungee"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/Dongle_Community.css" rel="stylesheet">

</head>

<body>
	<script>
		
	</script>
	<div class='back'>
		<!-- 로고 헤더 -->

		<header>
			<div class='logoback' style='width: 1024px; height: auto'>
				<!-- 로고 grid -->
				<div class='logo'
					style='width: 1024px; height: auto; background-color: rgba(130, 238, 41, 0.8)'>
					<h2
						style="color: darkcyan; font-family: 'Bungee', cursive; margin-left: 15px;">DONGLE</h2>
				</div>
			</div>
		</header>
		<aside>
			<div class="sideback center">
				<!-- 왼쪽 사이드 -->
				<div class="sidel"
					style='height: 100vh; background-color: rgba(130, 238, 41, 0.8)'>
					<div class="sideitem"
						style="border: 1px solid red; left: 10%; right: 10%; height: 250px;">프로필</div>
					<div class="sideitem"
						style='border: 1px solid red; left: 10%; right: 10%; height: 150px'>소개글</div>
				</div>
			</div>

		</aside>
		<!-- 게시판 -->
		<section>
			<div class="main center"
				style='width: 684px; height: auto; background-color: rgb(255, 255, 255); border: 1px solid black;'>
				<h2 id="set-head">
					동글 관리자 페이지
					</h2>
				
					<div class="panel panel-default"
						style="margin-top: 5%; width: 600px">
						<div class="panel-heading">동글 관리</div>
						<div class="panel-body">
							<a data-toggle="modal" data-target="#change-dongle-modal">동글
								정보 수정</a>
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
									<form role="form">
										<div class="form-group">
											<label for="donglename">동호회 이름</label> <input type="text"
												class="form-control" id="donglename"> <br> <label
												for="topic">주제 카테고리</label><br> <select
												class="form-control" id="topic">
												<option value="인문/과학">인문/과학</option>
												<option value="스포츠/레저">스포츠/레저</option>
												<option value="여행/캠핑">여행/캠핑</option>
												<option value="문화/예술">문화/예술</option>
												<option value="취업/자격증">취업/자격증</option>
												<option value="외국어/어학">외국어/어학</option>
												<option value="건강/다이어트">건강/다이어트</option>
												<option value="게임/오락">게임/오락</option>
											</select> <br> <label for="area">지역 카테고리</label><br> <select
												class="form-control" id="area">
												<option value="경기도">경기도</option>
												<option value="서울">서울</option>
												<option value="강원도">강원도</option>
												<option value="경상북도">경상북도</option>
												<option value="경상남도">경상남도</option>
												<option value="전라북도">전라북도</option>
												<option value="전라남도">전라남도</option>
												<option value="제주도">제주도</option>
											</select> <br> <label>성별</label><br>
											<div class="radio">
												<label class="radio-inline"><input type="radio"
													name="gender" value="남자" checked>남자</label> <label
													class="radio-inline"><input type="radio"
													name="gender" value="여자">여자</label> <label
													class="radio-inline"><input type="radio"
													name="gender" value="무관">무관</label>
											</div>
											<br> <label>활동 시간대</label><br>
											<div class="radio">
												<label class="radio-inline"><input type="radio"
													name="activetime" value="주중" checked>주중</label> <label
													class="radio-inline"><input type="radio"
													name="activetime" value="주말">주말</label> <label
													class="radio-inline"><input type="radio"
													name="activetime" value="무관">무관</label>
											</div>

											<label for="">연령대</label><br>
											<div class="col-sm-3">
												<input type="number" class="form-control" min="1" max="100"
													placeholder="최소">
											</div>
											<div class="col-sm-1">~</div>
											<div class="col-sm-3">
												<input type="number" class="form-control" min="1" max="100"
													placeholder="최대">
											</div>
										</div>


									</form>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-default"
										data-dismiss="modal">완료</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">취소</button>

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
									<button type="button" class="btn btn-default"
										data-dismiss="modal">예</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">아니오</button>
								</div>
							</div>
						</div>
					</div>
			</div>

		</section>
		<!-- 오른쪽 사이드 -->
		<aside>
			<div class="sideback center">

				<div class="sider"
					style='height: 100vh; background-color: rgba(130, 238, 41, 0.8)'>
					<!-- 메뉴 버튼 -->
					<button class='btn btn-primary'>HOME</button>
					<br>
					<button class='btn btn-primary'>공지사항</button>
					<br>
					<button class='btn btn-primary' id="feed-btn">피드</button>
					<br>
					<button class='btn btn-primary'>갤러리</button>
					<br>
					<button class='btn btn-primary'>일정</button>
					<br>
				</div>
			</div>
		</aside>



	</div>



</body>

</html>