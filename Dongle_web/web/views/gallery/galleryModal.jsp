<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.dongle.gallery.model.vo.GalleryCommentJoin,java.util.*,com.dongle.member.model.vo.Member,com.dongle.gallery.model.vo.GalleryPath" %>
<%
	List<GalleryPath> gplist=(List)request.getAttribute("gplist");
	List<GalleryCommentJoin> gclist=(List)request.getAttribute("gclist");
	Member loginMember = (Member)session.getAttribute("loginMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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
/* 댓글테이블!! */
table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
table#tbl-comment button.btn-reply{display:none;}
table#tbl-comment button.btn-delete{display:none;}
table#tbl-comment tr:hover {background:lightgray;}
table#tbl-comment tr:hover button.btn-reply{display:inline;}
table#tbl-comment tr:hover button.btn-delete{display:inline;}
table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
table#tbl-comment tr.level2 sub.comment-writer {color:black; font-size:14px;font-family: "a흑진주L";}
table#tbl-comment tr.level2 sub.comment-date {color:rgb(80,80,80); font-size:10px;font-family: "a흑진주L";}
/* modalImg 스타일 */
div#modalImg-div{align-items: center;justify-content: center;display:inline-block;}
div#modalImg-div img.modalImg{align-content: center; display: block; width:300px;height:300px;}

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
	<div id="modalImg-div">
		<hr>
		<%for(GalleryPath g:gplist){ %>
			<img class="modalImg" src="<%=request.getContextPath()%>/<%=g.getGalFileOldPath()%>" >
		<%} %>
		<hr>
	</div>
	<div class="comment-editor">
		<form action="<%=request.getContextPath()%>/gallery/galleryInsert" name="galleryCommentFrm" method="post">
			<input type="hidden" name="galCommentNo" value="<%=gclist %>"/>
			<input type="hidden" name="galCommentWriter" value="<%=loginMember.getMemberNo() %>"/>
			<input type="hidden" name="galCommentLevel" value="1"/>
			<input type="hidden" name="galCommentRef" value="0"/>
			<textarea cols='50' rows='3' style='resize:none;' name="galCommentContent"></textarea>
			<button type="submit" id='btn-insert'>둥록</button>
		</form>
	</div>
	<table id="tbl-comment">
		<%if(gclist!=null){ %>
			<%for(GalleryCommentJoin g:gclist){ %>
				<%if(g.getgalCommentLevel()==1){ %>
					<tr class='level1'>
						<td>
							<sub class='comment-writer'><%=g.getGroupMemberNickname()%></sub>
							<sub class='comment-date'><%=g.getGalCommentDate() %></sub>
							<br/>
							<%=g.getGalCommentContent() %>
						</td>
						<td>
							<button class='btn-reply' value='<%=g.getGalNo()%>'>답글</button>
						</td>
					</tr>
				<%} 
				else{%>
					<tr class="level2">
						<td>
							<sub class="comment-writer"><%=g.getGroupMemberNickname() %></sub>
							<sub class="comment-date"><%=g.getGalCommentDate() %></sub>
							<br/>
							<%=g.getGalCommentContent() %>
						</td>
						<td>
						</td>
					</tr>
				<%} %>
			<%} %>
		<%} %>
	</table>
	<script>
		<%-- $(function(){
			$('.btn-reply').on('click',function(e){
				<%if(loginMember!=null){%>
					var tr=$("<tr></tr>");
					var html="<td style='display:none;text-align:left;' colspan='2'>";
					html+="<form action='<%=request.getContextPath()%>/gallery/commentInsert' method='post'>";
					html+="<input type='hidden' name='galNo' value='<%=gplist.get[0].getGalNo()%>'/>";
					html+="<input type='hidden' name='galCommentWriter' value='<%=loginMember.getMemberId()%>'/>";
					html+="<input type='hidden' name='galCommentLevel' value='2'/>";
					html+="<input type='hidden' name='galCommentRef' value='"+$(this).val()+"'/>";
					html+="<textarea name='galCommentContent' style='resize: none' cols='60' rows='2'></textarea>";
					html+="<button type='submit' class='btn-insert2'>등록</button>";
					html+="</form></td>";
					tr.html(html);
					tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
					/* 연결된 이벤트 삭제 */
					$(this).off('click');
					
					tr.find('form').submit(function(e){
						if(<%=loginMember==null%>)
						{
							fn_loginAlert();
							e.preventDefault();
							return;
						}
						var len=$(this).children('textarea').val().trim().length;
						if(len==0)
						{
							e.preventDefault();
						}
					});
					tr.find("textarea").focus();
				<%}%>
			});
		}) --%>
	</script>
	
</body>
</html>