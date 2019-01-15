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
	<span class="close">&times;</span>
	<!-- 모달로 이미지 받기.. 부트스트랩 ㅂㄷㅂㄷ... -->
<%-- 	<div class="container">
     <h2>Carousel Example</h2>  
     <div id="myCarousel" class="carousel slide" data-ride="carousel" style='text-shadow: none;width:50%;'>
       <!-- Indicators -->
       <ol class="carousel-indicators">
          <%for(int i=0;i<gplist.size();i++){ %>
             <%if(i==0){ %>
               <li data-target="#myCarousel" data-slide-to=<%=i %> class="active" style='text-shadow: none;'></li>
            <%}
             else{%>
               <li data-target="#myCarousel" data-slide-to=<%=i %> style='text-shadow: none;'></li>
            <%}%>
         <%} %>
       </ol>
   
       <!-- Wrapper for slides -->
       <div class="carousel-inner">
            <%for(int i=0;i<gplist.size();i++){ %>
               <%if(i==0){ %>
               <div class="item active">
                    <img src="<%=request.getContextPath() %>/images/gallery/<%=gplist.get(i).getGalFileNewPath() %>" alt="<%=gplist.get(i).getGalFileNo() %>" >
                  </div>
               <%}
               else{ %>
                  <div class="item">
                    <img src="<%=request.getContextPath() %>/images/gallery/<%=gplist.get(i).getGalFileNewPath() %>" alt="<%=gplist.get(i).getGalFileNo() %>" >
                  </div>
               <%} %>
         <%} %>
       </div>
   
       <!-- Left and right controls -->
       <a class="left carousel-control" href="#myCarousel" data-slide="prev" style='text-shadow: none;'>
         <span class="glyphicon glyphicon-chevron-left" style='text-shadow: none;'></span>
         <span class="sr-only" style='text-shadow: none;'>Previous</span>
       </a>
       <a class="right carousel-control" href="#myCarousel" data-slide="next" style='text-shadow: none;'>
         <span class="glyphicon glyphicon-chevron-right" style='text-shadow: none;'></span>
         <span class="sr-only" style='text-shadow: none;'>Next</span>
       </a>
     </div>
   </div> --%>
   
	
	<div id="modalImg-div">
		<hr>
		<%for(GalleryPath g:gplist){ %>
			<img class="modalImg" src="<%=request.getContextPath()%>/images/gallery/<%=g.getGalFileNewPath()%>" >
		<%} %>
		<hr>
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
	<div class="comment-editor">
		<form action="<%=request.getContextPath()%>/gallery/commentInsert" name="galleryCommentFrm" method="post">
				<input type="hidden" name="groupNo" value="<%=gplist.get(0).getGroupNo() %>"/>
				<input type="hidden" name="galNo" value="<%=gplist.get(0).getGalNo() %>"/>
				<input type="hidden" name="galCommentWriterNo" value="<%=loginMember.getMemberNo() %>"/>
				<input type="hidden" name="galCommentLevel" value="1"/>
				<input type="hidden" name="galCommentRef" value="0"/>
				<input type="hidden" name="albumCode" value="<%=gplist.get(0).getAlbumCode()%>"/>
				<input type="hidden" name="galFileNo" value="<%=gplist.get(0).getGalFileNo()%>"/>
			<textarea cols='50' rows='3' style='resize:none;' name="galCommentContent"></textarea>
			<button type="submit" id='btn-insert'>등록</button>
		</form>
	</div>
	<table id="tbl-comment">
		<%if(gclist!=null){ %>
			<%for(GalleryCommentJoin g:gclist){ %>
				<%if(g.getGalCommentLevel()==1){ %>
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
		<%} 
		else{%>
			<tr>
				<td colspan='2'></td>
			</tr>
		<%} %>
	</table>
	<script>
		<%-- $(function(){
			$('.btn-reply').on('click',function(e){
				<%if(loginMember!=null){%>
					var tr=$("<tr></tr>");
					var html="<td style='display:none;text-align:left;' colspan='2'>";
					html+="<form action='<%=request.getContextPath()%>/gallery/commentInsert' method='post'>";
					html+="<input type='hidden' name='groupNo' value='<%=groupNo %>'/>"
					html+="<input type='hidden' name='galNo' value='<%=gplist.get(0).getGalNo()%>'/>";
					html+="<input type='hidden' name='galCommentWriterNo' value='<%=loginMember.getMemberNo()%>'/>";
					html+="<input type='hidden' name='galCommentLevel' value='2'/>";
					html+="<input type='hidden' name='albumCode' value='<%=gplist.get(0).getAlbumCode()%>'/>";
					html+="<input type='hidden' name='galFileNo' value='<%=gplist.get(0).getGalFileNo()%>'/>";
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
		}); --%>
	</script>
