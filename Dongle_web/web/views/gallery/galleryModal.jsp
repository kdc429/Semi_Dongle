<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
     .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>
<script>
$(function(){
	//선택한 이미지로 모달띄우기
	var modal = document.getElementById('modal-container');
	$('.galleryBox').click(function(obj){
		console.log(obj);
		modal.style.display="block";
	});
	$('.close').click(function(){
		modal.style.display="none";
	});
	window.onclick = function(event){
		if(event.target==modal)
		{
			modal.style.display="none";
		}
	}
});
</script>
</head>
<body>
	<span class="close">&times;</span>
		<div>
			<hr>
				<h2>안녕</h2>
			<hr>
		</div>
</body>
</html>