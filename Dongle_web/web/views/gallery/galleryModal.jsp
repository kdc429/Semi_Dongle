<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.dongle.gallery.model.vo.GalleryCommentJoin,java.util.*,com.dongle.member.model.vo.Member,com.dongle.gallery.model.vo.GalleryPath" %>
<%
	List<GalleryPath> gplist=(List)request.getAttribute("gplist");
	List<GalleryCommentJoin> gclist=(List)request.getAttribute("gclist");
	int groupNo = (int)request.getAttribute("groupNo");
	Member loginMember = (Member)session.getAttribute("loginMember");
%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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

body {font-family: 'a흑진주L';margin:0}

/* Slideshow container */

.slideshow-container {

width: 100%;
height: 100%;
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
  text-align : center;
  top: 30%;
  width:29px;
  padding-right: 20px;
  margin-top: -22px;
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
  right: 0;
  border-radius: 10px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover{
 background-color: rgba(0,0,0,0.8);
}
.next:hover {
  background-color: rgba(0,0,0,0.8);
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
background-color:rgb(240,240,240);
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
li button.btn-reply{display:none; background-color:white;float:right;border:none;height:10px;}
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

//사진 삭제
$('#deleteGallery').click(function(e){
	if(!confirm('사진을 삭제하겠습니까?'))
	{return;}
	else{
		$.ajax({
			url:"<%=request.getContextPath()%>/gallery/galleryDelete",
			data:{'groupNo':<%=groupNo%>,
				'albumCode':'<%=gplist.get(0).getAlbumCode()%>',
				'galNo':<%=gplist.get(0).getGalNo()%>
			},
			dataType:"html",
			type:"post",
			success:function(data){
				alert('Message: '+data);
				$('#modal-container').css('display','none');
				$.ajax({
					url:"<%=request.getContextPath()%>/gallery/galleryGet",
					data:{'groupNo':<%=groupNo%>,
						'albumCode':'<%=gplist.get(0).getAlbumCode()%>'
					},
					dataType:"html",
					type:"post",
					success:function(data){
						
						$('#content-div').html(data);
					}
				});
			}
		});
	}

});

</script>
	<li class="level2" style="list-style:none;">
		<span class='ico_skin thumb_profile'>
			<img class='img_profile' src='<%=request.getContextPath()%>/images/group_profile/<%=gplist.get(0).getGroupMemberImageNewPath() %>'>
		</span>
		<span class='comment_box'>
			<span class='comment-writer'><%=gplist.get(0).getGroupMemberNickname()%></span>
			<p id='' style='float:right;color:RGB(112,136,172);' >신고</p>
			<%if(loginMember.getMemberId().equals("admin")||gclist.get(0).getMemberNo()==loginMember.getMemberNo()){ %>
				<a id='deleteGallery' style='float:right;color:RGB(112,136,172);' >삭제</a>
			<%} %>
			<br/>
		</span>
	</li>
	<span class="close">&times;</span>
	<!-- 이미지 슬라이드 -->
	<!-- 메인 슬라이드 -->
	<div class="slideshow-container">
	 	<%for(int i=0;i<gplist.size();i++){ %>
			<div class="mySlides fade2">
				<img class="main_slideImg" src="<%=request.getContextPath() %>/upload/gallery/<%=gplist.get(i).getGalFileNewPath() %>">
				<div class="text">Caption Text</div>
			</div>
		<%} %>
		<a class="prev" onclick="plusSlides(-1)">❮</a>
		<a class="next"onclick="plusSlides(1)">❯</a>
	</div>
	<br>
	<div style="text-align: center">
		<%for(int i=0;i<gplist.size();i++){ %>
			<span class="dot" onclick="currentSlide(<%=i%>)"></span> 
		<%} %>
	</div>
	<div id="gal-content">
		<table>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td><%=gplist.get(0).getGalFileContent()%></td>
				<td style='float:right;'><%=gplist.get(0).getGalEnrollDate() %></td>
			</tr>
		</table>
	</div>
	<!-- 댓글창 시작 -->
	<div class="comment-editor">
		<ul>
			<%if(gclist!=null){ %>
				<%for(GalleryCommentJoin g:gclist){ %>
					<%if(g.getGalCommentLevel()==1){ %>
						<li class='level1' style="list-style:none;">
							<span class='ico_skin thumb_profile'>
								<img class='img_profile' src='<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupMemberImageNewPath() %>'>
							</span>
							<span class='comment_box'>
								<span class='comment-writer'><%=g.getGroupMemberNickname()%></span>
								<span class='comment-date'>
									<%=g.getGalCommentDate() %>
									<p id='' style='float:right;color:RGB(112,136,172);' >신고</p>
									<%if(loginMember.getMemberId().equals("admin")||gclist.get(0).getMemberNo()==loginMember.getMemberNo()){ %>
										<p id='deleteComment' value='<%=g.getGalCommentNo()%>' style='float:right;color:RGB(112,136,172);' >삭제</p>
									<%} %>
								</span>
								<br/>
								<span class='comment_content'>
									<%=g.getGalCommentContent() %>
									<button class='btn-reply' value='<%=g.getGalCommentNo()%>'>답글</button>
								</span>
							</span>
						</li>
					<%} 
					else{%>
						<li class="level2" style="list-style:none;">
							<span class='ico_skin thumb_profile'>
								<img class='img_profile' src='<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupMemberImageNewPath() %>'>
							</span>
							<span class='comment_box'>
								<span class='comment-writer'><%=g.getGroupMemberNickname()%></span>
								<span class='comment-date'>
									<%=g.getGalCommentDate() %>
									<p style='float:right;color:RGB(112,136,172);' >신고</p>
									<%if(loginMember.getMemberNo()==g.getMemberNo()){%>
										<p id='deleteComment' value='<%=g.getGalCommentNo()%>' style='float:right;color:RGB(112,136,172);'>삭제</p>
									<%} %>
								</span>
								<br/>
								<span class='comment_content'>
									<%=g.getGalCommentContent() %>
								</span>
							</span>
						</li>
					<%} %>
				<%} %>
			<%}%> 
		</ul>
		<input type="hidden" name="groupNo" id='groupNo' value="<%=gplist.get(0).getGroupNo() %>"/>
		<input type="hidden" name="galNo" id="galNo" value="<%=gplist.get(0).getGalNo() %>"/>
		<input type="hidden" name="galCommentWriterNo" id='galCommentWriterNo' value="<%=loginMember.getMemberNo() %>"/>
		<input type="hidden" name="galCommentLevel" id='galCommentLevel' value="1"/>
		<input type="hidden" name="galCommentRef" id='galCommentRef' value="0"/>
		<input type="hidden" name="albumCode" id='albumCode' value="<%=gplist.get(0).getAlbumCode()%>"/>
		<input type="hidden" name="galFileNo" id='galFileNo' value="<%=gplist.get(0).getGalFileNo()%>"/>
		<fieldset class='modal_comment'>
			<legend class='screen_out'>댓글쓰기 폼</legend>
			<div class='comment_write'>
				<label for='comment' class='lab_write screen_out'>내용</label>
				<textarea name="galCommentContent" id='galCommentContent' placeholder="소중한 댓글을 입력해주세요" tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>
			</div>
			<div class='comment_btn'>
				<button type="button" id='btn-insert'>입력</button>
			</div>
		</fieldset>
	</div>

<script>
	/* 댓글 삭제하기 */
	$(funtion(){
		$('#deleteComment').click(function(){
			if(!comfirm("정말로 삭제하시겠습니까?")){return;}
			else{
				$.ajax({
					url:"<%=request.getContextPath()%>/gallery/deleteComment",
					data:
				})
			}
		});
	});
	/* 대댓글 쓰기 함수*/
	$(function(){
		var eventflag;
		$('.btn-reply').on('click',function(e){
			console.log($(this));
			<%if(loginMember!=null){%>
				eventflag=true;
				var div=$("<div class='recomment_content'></div>");
				var html="";
				html+="<input type='hidden' name='groupNo' value='<%=groupNo %>'/>"
				html+="<input type='hidden' name='galNo' value='<%=gplist.get(0).getGalNo()%>'/>";
				html+="<input type='hidden' name='galCommentWriterNo' value='<%=loginMember.getMemberNo()%>'/>";
				html+="<input type='hidden' name='galCommentLevel' value='2'/>";
				html+="<input type='hidden' name='albumCode' value='<%=gplist.get(0).getAlbumCode()%>'/>";
				html+="<input type='hidden' name='galFileNo' value='<%=gplist.get(0).getGalFileNo()%>'/>";
				html+="<input type='hidden' name='galCommentRef2' value='"+$(this).val()+"'/>";
				html+="<fieldset class='modal_comment'>";
				html+="<div class='comment_write'>";
				html+="<textarea name='galCommentContent' id='galCommentContent' placeholder='소중한 댓글을 입력해주세요' tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>";
				html+="</div>";
				html+="<div class='comment_btn'>";
				html+="<button value='"+$(this).val()+"' type='button' id='btn-insert' style='float:right;width:65px;height:28px;font-size:14px;line-height:15px;border-radius: 20px;border:none;background-color:white;'>입력</button>";
				html+="</div>"
				html+="</fieldset>"
				div.html(html);
				div.insertAfter($(this).parent().parent().parent()).children("span").slideDown(800);
				/* 연결된 이벤트 삭제 */
				$(this).off('click');
				/* 빈공간 누르면 display바뀌는 것 */
/* 				$('.recomment_content').click(function(){
					if(eventflag)
					{
						$(this).css('display','none');
						eventflag=false;
						return;
					}
				}) */
				
				div.find('#btn-insert').click(function(e){
					if(<%=loginMember==null%>)
					{
					 	fn_loginAlert();
						e.preventDefault();
						return;
					}
					/* var len=($(this).parent().find()).siblings('textarea').val().trim().length;
					if(len==0)
					{
						e.preventDefault();
					} */
					$.ajax({
						url:"<%=request.getContextPath()%>/gallery/commentInsert",
						data:{"groupNo":$('#groupNo').val(),
							"galNo":$('#galNo').val(),
							"galCommentWriterNo":$('#galCommentWriterNo').val(),
							"galCommentLevel":2,
							"galCommentRef":$(this).val(),
							"albumCode":$('#albumCode').val(),
							"galFileNo":$('#galFileNo').val(),
							"galCommentContent":$('#galCommentContent').val(),
						},
						type:"post",
						success:function(data){
							if(data!=null)
							{	
								alert('댓글 등록 완료!');
								$('.comment-editor').html(data);
							}
							else
							{
								alert('댓글 등록에 실패하였습니다');
							}
						},
						error:function(request){console.log(request);}
					})
				});
				div.find("textarea").focus();
			<%}%>
		});
		function fn_loginAlert()
		{
			alert('로그인 후 이용할 수 있습니다.');
		}

		
		/* 댓글 등록 */
		$(function(){
			$('#btn-insert').click(function(){
				$.ajax({
					url:"<%=request.getContextPath()%>/gallery/commentInsert",
					data:{"groupNo":$('#groupNo').val(),
						"galNo":$('#galNo').val(),
						"galCommentWriterNo":$('#galCommentWriterNo').val(),
						"galCommentLevel":$('#galCommentLevel').val(),
						"galCommentRef":$('#galCommentRef').val(),
						"albumCode":$('#albumCode').val(),
						"galFileNo":$('#galFileNo').val(),
						"galCommentContent":$('#galCommentContent').val(),
						"dataNum":2
					},
					type:"post",
					success:function(data){
						if(data!=null)
						{	
							alert('댓글 등록 완료!');
							$('.comment-editor').html(data);
						}
						else
						{
							alert('댓글 등록에 실패하였습니다');
						}
					},
					error:function(request){console.log(request);}
				});					
			});
		});
	});
</script>
