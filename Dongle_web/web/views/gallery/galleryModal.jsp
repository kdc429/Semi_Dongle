<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.dialog{
		display:none;
		position:fixed;
		z-index:1;
		left:0;
		right:0;
		width:100%;
		height:100%;
		overflow:auto;
		background-color:rgb(0,0,0);
		background-color:rgba(0,0,0,0.4);
	}
	.modal-content {
            background-color: #fefefe;
            margin: 15% auto; 
            padding: 20px;
            border: 1px solid #888;
            width: 50%;                    
    }
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
	<!-- ㅡmodal-container -->
	<div class="dialog" id="modal-container">
		<div class="modal-content">
			<span class="close">&times;</span>
			<div>
				<hr>
					<h2>안녕</h2>
				<hr>
			</div>
		</div>
    </div>
</body>
</html>