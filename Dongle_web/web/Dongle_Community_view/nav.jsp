<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	System.out.println(request.getRequestURL());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>asideRight</title>
</head>
<script>
<<<<<<< HEAD
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
		
	function galleryTag(){
		location.href="<%=request.getContextPath()%>/albumGet?groupNo="+gNo+"&adminId="+mNo;
		console.log("<%=request.getContextPath()%>/albumGet?groupNo="+gNo+"&adminId="+mNo);
	}
	};




</script>
<body>
	<nav>
		<div class="sideback center">

			<div class="sider"
				style='height: 100vh; background-color: rgba(130, 238, 41, 0.8);'>
				<!-- 메뉴 버튼 -->
				<button class='btn btn-primary' value='1'>HOME</button>
				<br>
				<button class='btn btn-primary' onclick="boardTag();">공지사항</button>
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