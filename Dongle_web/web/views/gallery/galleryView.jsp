<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dongle.gallery.model.vo.GalleryPath,java.util.*,com.dongle.member.model.vo.Member"%>
<%
	List<GalleryPath> list = (List)request.getAttribute("list");
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
 	Member loginMember = (Member)session.getAttribute("loginMember");
 	int groupNo=(int)request.getAttribute("groupNo");
 	String albumCode=(String) request.getAttribute("albumCode");
	int count=1;
%>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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
		margin-top:-400px;
		/* z-index:10; */
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
        border-radius: 5px;
    }
    div#btn-div{margin-left:60px;}
    div#btn-div span button#list-btn{float:left;}
    div#btn-div span button#insert-btn{float:right;}
</style>
<script>
	$(function(){
		$('.galImg').mouseover(function(){
			$(this).fadeTo(100,0.4);
		});
		$('.galImg').mouseleave(function(){
			$(this).fadeTo(100,1);
		});
	});
	$(function(){
		//사진 추가하기 form
		$('.insert-bnt').click(function(){
			console.log("tt");
			$.ajax({
				url:"<%=request.getContextPath()%>/gallery/insertGallery?groupNo=<%=groupNo%>&albumCode=<%=albumCode%>",
				type:"post",
				dataType:"html",
				success:function(data){
					$('#gallery-container').html(data);
				},
				error: function(){console.log("gg");}
			});
		});
	});
	$(function(){
		//모달띄우기
		$('.galImg').click(function(event){
			var galFileNo = $(event.target).nextAll('#galFileNo')[0].value;
			var galNo = $(event.target).nextAll('#galNo')[0].value;
			$.ajax({
				url:"<%=request.getContextPath()%>/gallery/galleryAllList?groupNo=<%=groupNo%>&albumCode=<%=albumCode%>&galFileNo="+galFileNo+"&galNo="+galNo+"&dataNum="+1,
				type:"post",
				dataType:"html",
				success:function(data){
					$('.modal-content').html(data);
					$('#modal-container').css('display','block');
				},
				error:function(request,m,e){console.log(request);}
				
			});	
		});
		
	});
</script>
<section id="gallery-container">
	<div id="btn-div" style="position:relative;width:550px;">
		<hr>
		<span><button type="submit" id="list-bnt" name="list-bnt">목록으로</button></span>
		<button class="insert-bnt" name="insert-bnt" >사진 추가하기</button>
	</div>
	<br>
	<div id="galleryList">
		<table style='margin-left:60px;'>
			<%if(list.size()!=0){%>
					<%for(GalleryPath t : list){ %>
						<%if(count%4==1){%>
							<tr>
							</tr>
							<td class="galleryBox" >
								<img class="galImg" src="<%=request.getContextPath()%>/images/gallery/<%=t.getGalFileNewPath() %>">
								<input type="hidden" name="groupNo" value="<%=t.getGroupNo()%>"/>
								<input type="hidden" name="albumCode" value="<%=t.getAlbumCode()%>"/>
								<input type="hidden" name="galFileNo" id="galFileNo" value="<%=t.getGalFileNo() %>"/>
								<input type="hidden" name="galNo" id="galNo" value="<%=t.getGalNo() %>"/>
							</td>
							<%count++; %>
						<%} 
						else{%>
							<td class="galleryBox" >
								<img class="galImg" src="<%=request.getContextPath()%>/images/gallery/<%=t.getGalFileNewPath() %>">
								<input type="hidden" name="groupNo" value="<%=t.getGroupNo()%>"/>
								<input type="hidden" name="albumCode" value="<%=t.getAlbumCode()%>"/>
								<input type="hidden" name="galFileNo" id="galFileNo" value="<%=t.getGalFileNo()%>"/>
								<input type="hidden" name="galNo" id="galNo" value="<%=t.getGalNo() %>"/>
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
<!-- 	</form> -->
	</div>
	<br><br>
	<div id="pag-div">
		<table width="610px" text-align="center" style='margin-left:40%;'>
			<tr>
	   			<td>
	        		<ul class="pagination" id="paging">
		   				<%=pageBar %>
	        		</ul>
	       		</td>
			</tr>
		</table>
	</div>
	
	<br><br>
</section>
<!-- ㅡmodal-container -->
	
<div class="modal-div">
		<div class="dialog" id="modal-container">
			<div class="modal-content">
			</div>
	    </div>
    </div>
