<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.dongle.group.model.vo.Group,com.dongle.member.model.vo.Member"%>
	<%
		Group gg = (Group)request.getAttribute("group");
		Member loginMember2=(Member) request.getAttribute("loginMember");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>asideRight</title>
</head>
<script>
	function galleryTag(){
		location.href="<%=request.getContextPath()%>/albumGet?<%=gg.getGroupNo()%>=&adminId=<%=loginMember2.getMemberId()%>";
	}
	var url;
	var params;
	var params2=[];
	window.onload=function getQuerystring(paramName){ 
		
		window.location.search.substring(1); //주소창 주소중 맨뒤 파라미터 ? 다음부터 추출함 
		url=window.location.search.substring(1);
		if(params==""){
			return;
		}
		params=url.split('&'); //'&' 로 분리해서 파라미터 'key=value' 형식으로 분리함
		console.log(params);
		console.log(params[0].split('='));
		
		for(var i=0;i<params.length;i++){ // 다시 '='으로 분리해서 파라미터 값만 따로 추출
			params2[i]=params[i].split("=");
			console.log(params2);
		}
		var gNo=params2[0][1];  //groupNo
		var mNo=params2[1][1]; //memberNo
		console.log(gNo+":"+mNo);
		
		console.log("<%=request.getContextPath()%>/albumGet?groupNo="+gNo+"&adminId="+mNo);
		return gNo,mNo;
		


</script>
<body>
	<nav>
		<div class="sideback center">

			<div class="sider"
				style='height: 100vh; background-color: rgba(130, 238, 41, 0.8);'>
				<!-- 메뉴 버튼 -->
				<button class='btn btn-primary' value='1'>HOME</button>
				<br>
				<button class='btn btn-primary' value='2'>공지사항</button>
				<br>
				<button class='btn btn-primary' value='3'>피드</button>
				<br>
				<button class='btn btn-primary' >갤러리</button>
				<br>
				<button class='btn btn-primary' value='5'>일정</button>
				<br>
			</div>
		</div>
	</nav>
	
	

</body>
</html>