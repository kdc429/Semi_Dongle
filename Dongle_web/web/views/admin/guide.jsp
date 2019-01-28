<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.dongle.gallery.model.vo.GalleryCommentJoin,java.util.*,com.dongle.member.model.vo.Member,
com.dongle.member.model.vo.ReportReason,com.dongle.gallery.model.vo.GalleryPath,com.dongle.group.model.vo.Group" %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
/* 모달창  */
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
/* 모달창에 있는 이미지 슬라이드  */
* {box-sizing:border-box}

body {font-family: 'netmarble Medium';;margin:0}

/* Slideshow container */

.slideshow-container {

width: 95%;
height:95%;
position: static;
background-size: cover;
margin: auto;
margin-top: 50px;

}

.main_slideImg{
width: 100%; 
height: 100%; 
top : 100px;
}

/* Next & previous buttons */

.slideshow-container a.prev, a.next{
  cursor: pointer;
  position: absolute;
  top: 50%;
  padding-right: 20px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 10px;
  text-decoration: none;
  background-color:rgba(250,250,250,0);
  z-index: 100;
  box-sizing: border-box;
}
.slideshow-container a{text-decoration: none;}
/* Position the "next button" to the right */

.next {
  margin-left:700px;
  border-radius: 10px;
}


/* Caption text */

.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */

.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */

.dot {
  cursor:pointer;
  height: 13px;
  width: 13px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;

}

.active, .dot:hover {
  background-color: #717171;
}

/* Fading animation */

.fade2 {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 0.5s;
  animation-name: fade;
  animation-duration: 5s;
}

@-webkit-keyframes fade2 {
  from {opacity: .4}
  to {opacity: 1}
}

@keyframes fade2 {
  from {opacity: .4}
  to {opacity: 1}
}

/* On smaller screens, decrease text size */

@media only screen and (max-width: 300px) {
  .slprev, .slnext,.text {font-size: 11px}
}

/* modalImg 스타일 */
*{box-sizing: border-box;}

/* 댓글창 스타일 */
div.comment-editor fieldset.modal_comment{
padding:8px 10px 10px;
border-bottom:1px solid #efefef;
font-family:'a흑진주L';
border-top:1px solid #e8e8e8;
background-color:rgb(248,248,248);
position:relative;
margin-top:2px;
}
.screen_out{overfloa:hidden;width:0;height:0;line-height:0;text-indent:-9999px;}
.lab_write{top:8px;left:14px;}
/* 댓글테이블!! */
div.comment-editor{border-top: 1px solid rgb(240,240,240);margin-top:5%;}
div.comment-ediotr ul{list-style:none;}
div.comment-ediotr ul li{list-style:none;}
.ico_skin{display:block;overflow:hidden;font-size:0;line-height: 0;text-indent:-9999px;}
.thumb_profile{
   width: 33px;
   height: 33px;
   margin-right: 11px;
   margin-top: 2px;
   background-position: -120px -20px;
   float:left;
   border-radius:48px;
}
img.img_profile{display:block;width:100%;height:100%;border-radius:48px;}

.comment_box{margin-top:4px;overflow:hidden;display:block;}
div.comment_box ul{}
li{padding:0;}
.comment_writer{float:left;overflow:hidden;color:rgb(250,250,250);text-overflow: ellipsis;white-space: nowrap;font-size:14px;margin-right:5px;max-width:120px;}
.comment_date{float:left;font-size:12px;color:#a7a7a7;margin-top:3px;}
.comment_content{display:block;font-size:13px;color:#5c5c5c;clear:both;line-height: 19px;padding-top:2px;}
div.tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; box-sizing: border-box;} 
li button.btn-reply,button.btn-delete{display:none; background-color:white;float:right;border:none;height:10px;}
li button.btn-delete{display:none;}
/* li:hover {background:lightgray;} */
li:hover button.btn-reply{display:inline;}
li:hover button.btn-delete{display:inline;}
li.level2{padding-left:50px;}
div.comment-editor fieldset.modal_comment div.comment-btn button#btn-insert{
float:right;
width:65px;height:28px;
font-size:14px;
line-height:15px;
border-radius: 20px;
border:none;
background-color:white;
}

</style>
<script>
$(function(){
	//선택한 이미지로 모달띄우기 이벤트
	var modal = document.getElementById('modal-container');
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
/* 이미지 슬라이드 스크립드 */
 //슬라이드 스크립
var slideIndex = 1;
showSlides(slideIndex);
function plusSlides(n) {
  showSlides(slideIndex += n);
}
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
var i;
var slides = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName("dot");
if (n > slides.length) {slideIndex = 1}
if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}


</script>
	<span class="close">&times;</span>
	<!-- 이미지 슬라이드 -->
	<!-- 메인 슬라이드 -->
	<div class="slideshow-container">
			<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image01.png">
				<div class="text">Caption Text</div>
			</div>
			<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image02.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image03.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image04.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image05.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image06.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image07.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image08.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image09.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image10.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image11.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image12.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image13.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image14.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image15.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image16.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image17.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image18.png">
				<div class="text">Caption Text</div>
			</div>
						<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath()%>/images/dongle_guide/image19.png">
				<div class="text">Caption Text</div>
			</div>
			
			
		<a class="prev" onclick="plusSlides(-1)">❮</a>
		<a class="next"onclick="plusSlides(1)">❯</a>
	</div>
	<br>
	<div style="text-align: center">
		<%for(int i=1;i<=19;i++){ %>
			<span class="dot" onclick="currentSlide(<%=i%>)"></span>
		<%} %>
	</div>
