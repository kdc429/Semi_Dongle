<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>주소 검색</title>
<style>
	div#search-container{padding:30px}
	input#search-address{display:inline;width:450px} 
	button#search-btn{float:right; width:80px}
</style>
</head>
<body>
	<div id="search-container">
		<div id="search-div">
			<label for="donglename">주소 검색</label> <Br>
			<input type="text" class="form-control" id="search-keyword">
			<button id="search-btn" class="btn btn-info">검색</button>
		</div>
	
		<hr>
		<div id="result-div">
			
		</div>
	</div>
	
</body>
</html>

<script>
$(function(){
	$("#search-btn").click(function(){
		var keyword = $("#search-keyword").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/manager/searchAddress",
			type:"post",
			data:{
				"keyword", keyword
			},
			dataType:"html",
			success:function(data){
				$('#result-div').html(data);
			},
			error:function(request){},
			complete:function(){console.log("ok");}
		})
	})
});
</script>