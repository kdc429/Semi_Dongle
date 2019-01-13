<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dongle.gallery.model.vo.GalleryPath,java.util.*,com.dongle.member.model.vo.Member"%>
<%
	List<GalleryPath> list = (List)request.getAttribute("list");
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
 	Member loginMember = (Member)session.getAttribute("loginMember");
 	int groupNo=(int)request.getAttribute("groupNo");
	int count=1;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<!-- 부트스트랩 -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/gallery_style.css"> 
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
	function fn_gallery_validate(){
		return true;
	}
	$(function(){
		$('.galImg').mouseover(function(){
			$(this).fadeTo(100,0.4);
		});
		$('.galImg').mouseleave(function(){
			$(this).fadeTo(100,1);
		})
		
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
<title>Insert title here</title>
</head>
<body>
<section id="gallery-container">
	<!-- ㅡmodal-container -->
	<div class="dialog" id="modal-container">
		<div class="modal-content">
			<span class="close">&times;</span>
			<div>
				<hr>
				<%-- <img class="modalImg" src=<%=list.get(3).getGalFilePath()%> width="150px" height="150px"> --%>
				<hr>
			</div>
		</div>
	
    </div>
	<form name="galleryList" id="galleryList" onsubmit="return fn_gallery_validate();">
		<table >
			<%if(list.size()!=0){ %>
					<%for(GalleryPath t : list){ %>
						<%if(count%4==1){%>
							<tr>
							</tr>
							<td class="galleryBox" >
								<img class="galImg" src="<%=t.getGalFilePath() %>">
								<input type="hidden" name="groupNo" value="<%=t.getGroupNo()%>"/>
								<input type="hidden" name="albumCode" value="<%=t.getAlbumCode()%>"/>
								<input type="hidden" name="galFileNo" value="<%=t.getGalFileNo() %>"/>
							</td>
							<%count++; %>
						<%} 
						else{%>
							<td class="galleryBox" >
								<img class="galImg" src="<%=t.getGalFilePath() %>">
								<input type="hidden" name="groupNo" value="<%=t.getGroupNo()%>"/>
								<input type="hidden" name="albumCode" value="<%=t.getAlbumCode()%>"/>
								<input type="hidden" name="galFileNo" value="<%=t.getGalFileNo()%>"/>
							</td>
							<%count++; %>
						<%} %>
					<%} %>
			<%}
			else{%>
				<div>
					<h3>등록된 사진이 없습니다.</h3>
				</div>
			<%} %>
		</table>
	</form>
	<br><br>
	<table width="610px" text-align="center">
		<tr>
   			<td>
        		<ul class="pagination" id="paging">
	   				<%=pageBar %>
        		</ul>
       		</td>
		</tr>
	</table>
	<br><br>
</section>
</body>
</html>