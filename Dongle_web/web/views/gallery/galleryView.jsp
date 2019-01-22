<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dongle.gallery.model.vo.GalleryPath,java.util.*,com.dongle.member.model.vo.Member,
com.dongle.group.model.vo.GroupMember"

%>
<%
/* 	List<GalleryPath> list = (List)request.getAttribute("list");

	List<GalleryPath> allList = (List)request.getAttribute("allList"); */
	
	List<GalleryPath> tList = (List)request.getAttribute("tList");
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar=(String)request.getAttribute("pageBar");
 	Member loginMember = (Member)session.getAttribute("loginMember");
 	int groupNo=(int)request.getAttribute("groupNo");
 	String albumCode=(String) request.getAttribute("albumCode");
 	GroupMember gm = (GroupMember)request.getAttribute("groupMember");
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
	position:absolute;
	margin-top:-1200px;
	z-index:10;
	left:0;																																	
	right:0;
	width:100%;
	height:1500px;
	overflow:hidden;
	background-color:rgb(0,0,0);
	background-color:rgba(0,0,0,0.4);
}
.modal-content {
       background-color: #fefefe;
       margin: 15% auto; 
       padding: 20px;
       border: 1px solid #888;
       width: 40%;  
       border-radius: 5px;
   }
div.back-div{padding-left:90px;padding-top:50px;}
div.back-div a.list_btn{text-decoration:none;}
div.back-div a.list_btn img.back_icon{width:10px;height:10px;}
div.back-div button.insert-bnt{float:right; border:none;background-color:rgb(0,0,0,0);margin-right:90px;}
div#gallery-container div#pag-div{position:relative; border:none;}
div#gallery-container div#pag-div>table.pag_table{width:100%; text-align:center;}
div#gallery-container div#pag-div>table.pag_table tr td{align-items: center;}
div#gallery-container div#pag-div>table.pag_table tr td ul.pagnation li{text-align:center; align-items: center;color:green;}
div#gallery-container div#pag-div>table.pag_table tr td ul.pagnation li a{text-decoration:none;color:rgb(50,50,50);align-items: center;}

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
	//돌아가기(목록으로)
	$(function(){
		$(".back_icon").click(function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/gallery/albumGet?groupNo=<%=groupNo%>&memberNo=<%=loginMember.getMemberNo()%>",
				type:"post",
				dataType:"html",
				success:function(data){
					$('#content-div').html(data);
				},
				error:function(request){},
				complate:function(){console.log("ok");}
			});
		});
	});
</script>
<div id="gallery-container">
	<div class='back-div'>
		<a href='javascript:' class='list_btn'>
			<img class='back_icon' src='<%=request.getContextPath()%>/images/gallery/back.png' title='목록으로'>
		</a>
		<%if(loginMember.getMemberId()!=null&&(loginMember.getMemberId().equals("admin")||loginMember.getMemberNo()==gm.getMemberNo())){ %>
			<button class="insert-bnt" name="insert-bnt" >사진 추가하기</button>
		<%} %>
	</div>
	<hr style='width:78%'>
	<br>
	<div id="galleryList">
		<table style='margin-left:80px;'>
			<%if(tList.size()!=0){%>
					<%for(GalleryPath t :tList){ %>
						<%if(count%4==1){%>
							<tr>
							</tr>
							<td class="galleryBox" >
								<%-- <%if(t.getGalMultiStatus().equals("Y")){ %> --%>
									<img class="galImg" src="<%=request.getContextPath()%>/upload/gallery/<%=t.getGalFileNewPath() %>">
								<%-- <%} %> --%>
								<input type="hidden" name="groupNo" value="<%=t.getGroupNo()%>"/>
								<input type="hidden" name="albumCode" value="<%=t.getAlbumCode()%>"/>
								<input type="hidden" name="galFileNo" id="galFileNo" value="<%=t.getGalFileNo() %>"/>
								<input type="hidden" name="galNo" id="galNo" value="<%=t.getGalNo() %>"/>
							</td>
							<%count++; %>
						<%} 
						else{%>
							<td class="galleryBox" >
								<img class="galImg" src="<%=request.getContextPath()%>/upload/gallery/<%=t.getGalFileNewPath() %>">
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
				<div style='margin-left:100px;'>
					<h3>등록된 사진이 없습니다.</h3>
				</div>
			<%} %>
		</table>
<!-- 	</form> -->
	</div>
	<br><br>
	<div id="pag-div">
		<table class='pag_table'>
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
</div>

<!-- modal-container 모달 들어갈 곳->>(메인으로 이동함!)-->
<!-- <div class="modal-div">
	<div class="dialog" id="modal-container">
		<div class="modal-content">
		</div>
   	</div>
</div> -->
