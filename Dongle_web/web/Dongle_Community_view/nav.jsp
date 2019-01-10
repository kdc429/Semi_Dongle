<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>asideRight</title>
</head>
<script>
function galleryTag(){
	location.href="<%=request.getContextPath()%>/albumGet?groupNo=1&adminId=admin";
}
</script>
<body>
	<nav>
		<div class="sideback center">

			<div class="sider"
				style='height: 100vh; background-color: rgb(228, 228, 228);'>
				<!-- 메뉴 버튼 -->
				<button class='btn btn-primary' value='1'>HOME</button>
				<br>
				<button class='btn btn-primary' value='2'>공지사항</button>
				<br>
				<button class='btn btn-primary' value='3'>피드</button>
				<br>
				<button class='btn btn-primary' onclick="galleryTag();" >갤러리</button>
				<br>
				<button class='btn btn-primary' value='5'>일정</button>
				<br>
			</div>
		</div>
	</nav>
	
	

</body>
</html>