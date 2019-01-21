<%@page import="com.dongle.gallery.model.vo.AlbumCategory,java.util.*,com.dongle.member.model.vo.Member,com.dongle.gallery.model.vo.GalleryPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	List<AlbumCategory> list = (List)request.getAttribute("list");
 	int groupNo=(int)request.getAttribute("groupNo");
 	List<GalleryPath> galList = (List)request.getAttribute("galList");
 	Member loginMember = (Member)session.getAttribute("loginMember");
 	int count=1;
 	int albumCount=1;
 	int empty=0;
 %>
<meta charset="UTF-8">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/gallery_style.css"> 
<script>
//ajax이용하기여 앨범 불러오기
$(function(){
	$('.alImg').click(function(event){
		var albumCode = $(event.target).nextAll("#albumCode")[0].value;
		console.log(albumCode);
		$.ajax({
			url:"<%=request.getContextPath()%>/gallery/galleryGet?groupNo=<%=groupNo%>&albumCode="+albumCode,
			dataType:"html",
			success:function(data){
				$('#content-div').html(data);
			},
			error:function(request){
			}
		});
	});
});
$(function(){
	$('.alname').click(function(event){
		var albumCode = $(event.target).nextAll("#albumCode")[0].value;
		console.log(albumCode);
		$.ajax({
			url:"<%=request.getContextPath()%>/gallery/galleryGet?groupNo=<%=groupNo%>&albumCode="+albumCode,
			dataType:"html",
			success:function(data){
				$('#content-div').html(data);
			},
			error:function(request){
			}
		});
	});
});
$(function(){
	//앨범추가
	$('#albumPlusBtn').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/albumPlus?groupNo=<%=groupNo%>",
			dataType:"html",
			type:"post",
			success:function(data){
				$('#album-container').html(data);
			}
		});
	});
	
	//앨범삭제
	$('#albumDeleteBtn').click(function(e){
		var m,t1,t2;

		$('.delcheck').removeAttr('hidden');
		$('.delsubmit').removeAttr('hidden');
		
		$('.delcheck').click(function(e){
			m=$(this).val();
			t1=$('.delcheck').siblings('#groupNo').val();
			t2=$(this).siblings('#albumCode').val();
			console.log(m);
			console.log(t1+t2);
		})
		
		$('.delsubmit').click(function(){
			if(!confirm('앨범을 삭제하겠습니까?'))
			{return;}
			else{
				$('.delcheck').attr('hidden');
				$('.delsubmit').attr('hidden');

				$.ajax({
					url:"<%=request.getContextPath()%>/gallery/albumDelete",
					data:{'groupNo':t1,
						'albumCode':t2,
						'albumName':m
					},
					dataType:"html",
					type:"post",
					success:function(data){
						alert('Message: '+data);
						$.ajax({
							url:"<%=request.getContextPath()%>/gallery/albumGet",
							data:{'groupNo':t1,
							},
							dataType:"html",
							type:"post",
							success:function(data){
								$('#album-container').html(data);
							}
						});
					}
				});
			}
		});

	});
});

</script>

<div id="album-container">
	<table width="370px" id="albumPlus-tbl" style='margin-left:25%;'>
		<tr>
			<td>
				<%if(loginMember.getMemberId()!=null&loginMember.getMemberId().equals("admin")){ %>
				<div><br></div>
					<div id="albumPlus">
						<input style="float:right; border:none; background-color:rgb(0,0,0,0); margin-left:10px;" type="button" id="albumPlusBtn" name="albumPlusBtn" value="앨범 추가"/>
						<input style="float:right; border:none; background-color:rgb(0,0,0,0);" type="button" id="albumDeleteBtn" name="albumDeleteBtn" value="앨범 삭제"/>
					</div>
				<%} %>
				<hr>
				
			</td>
		</tr>
	</table>
	<form name="albumFolder" id="albumFolder">
		<table  style='margin-left:25%;'>
			<%if(list.size()!=0){ %>
				<%for(int i=0;i<list.size();i++){ %>
					<%if(count%2==1){%>
						<tr>
						</tr>
						<%albumCount=0; %>
						<td class="albumFolBox" style='width:185px; height:202px;'>
							<%for(int j=0;j<galList.size();j++){ %>
								<%if(list.get(i).getAlbumCode().equals(galList.get(j).getAlbumCode())){ %>
									<%if(albumCount<4){ %>
										<%if(galList.get(j).getAlbumCode()!=null){ %>
											<img class="alImg" src="<%=request.getContextPath() %>/upload/gallery/<%=galList.get(j).getGalFileNewPath()%>">
											<%empty++; %>
										<%}
										else{%>
											<img class="alImg" src="<%=request.getContextPath() %>/upload/gallery/white.png">
										<%} %>
										<%albumCount++;%>
									<%}else{continue;} %>
								<%}else{continue;} %>
							<%} %>
							<%if(empty==0){%><img class="alImg" src="<%=request.getContextPath() %>/upload/gallery/white.png"><%} %>
							<p class='alname'>[&nbsp;<%=list.get(i).getAlbumName()%>&nbsp;]</p>
							<input type='radio' name='delcheck' class='delcheck' value="<%=list.get(i).getAlbumName() %>" hidden="hidden">
							<input type="hidden" name="groupNo" id="groupNo" value="<%=list.get(i).getGroupNo()%>"/>
							<input type="hidden" name="albumCode" id="albumCode" value="<%=list.get(i).getAlbumCode()%>"/>
						</td>
						<%count++;%>
					<%} 
					else{%>
						<td class="albumFolBox"  style='width:185px; height:202px;'>
							<%albumCount=0; %>
							<%for(int j=0;j<galList.size();j++){ %>
								<%if(list.get(i).getAlbumCode().equals(galList.get(j).getAlbumCode())){ %>
									<%if(albumCount<4){ %>
										<%if(galList.get(j).getAlbumCode()!=null){ %>
											<img class="alImg" src="<%=request.getContextPath() %>/upload/gallery/<%=galList.get(j).getGalFileNewPath()%>">
											<%empty++;%>
										<%}
										else{%>
											<img class="alImg" src="<%=request.getContextPath() %>/upload/gallery/white.png">
										<%} %>
										<%albumCount++;%>
									<%}else{continue;} %>
								<%}else{continue;} %>
							<%} %>
							<%if(empty==0){%><img class="alImg" src="<%=request.getContextPath() %>/upload/gallery/white.png"><%} %>
							<p class='alname'>[&nbsp;<%=list.get(i).getAlbumName()%>&nbsp;]</p>
							<input type='radio' name='delcheck' class='delcheck' value="<%=list.get(i).getAlbumName() %>" hidden="hidden">
							<input type="hidden" name="groupNo" id="groupNo" value="<%=list.get(i).getGroupNo()%>"/>
							<input type="hidden" name="albumCode" id="albumCode" value="<%=list.get(i).getAlbumCode()%>"/>
						</td>
						<%count++;%> 
					<%} %>
				<%} %>
			<%}
			else{%>
				<div style='padding-left:90px;'>
					<h3>등록된 앨범이 없습니다.</h3>
				</div>
			<%} %>
			<tr>
				<td >
					<input type='button' class='delsubmit' value='삭제' hidden='hidden'>
				</td>
			</tr>
		</table>
	</form>
</div>
