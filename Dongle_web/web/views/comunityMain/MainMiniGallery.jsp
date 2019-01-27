<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dongle.gallery.model.vo.AlbumCategory,java.util.*,com.dongle.member.model.vo.Member,com.dongle.gallery.model.vo.GalleryPath"%>
<meta charset="UTF-8">
<% 	
	int groupNo=(int)request.getAttribute("groupNo");
	List<GalleryPath> galList = (List)request.getAttribute("galList");
	Member loginMember = (Member)session.getAttribute("loginMember");
%>
<script type="text/javascript">
/* $(document).ready(function() {
	$("#content-slider").lightSlider({
        loop:true,
        keyPress:true
    });
}); */
</script>
<style type="text/css">
   .item{
   		margin-bottom: 60px;
    }
	.content-slider li{
		background-color: lightgray;
		ext-align: center;
	 	color: #FFF;
	}
</style>
<div>
	<h5><b><mark style='background-color:yellow;color:red;'><i>&nbsp;New&nbsp;</i></mark>&nbsp; 갤러리</b></h5>
	<div>
		<div style='white-space: nowrap;overflow: hidden;margin-left:10px;'>
			<%if(galList.size()!=0){ %>
				<%for(int j=0;j<6;j++){ %>
					<%if(j<galList.size()){ %>
						<img style='width:80px;height:70px;display:inline-block;padding:2px;' class="main-alImg" src="<%=request.getContextPath() %>/upload/gallery/<%=galList.get(j).getGalFileNewPath()%>">
						<%-- <img style='width:80px;height:70px;display:inline-block;padding:2px;' class="alImg" src="<%=request.getContextPath() %>/images/gallery/imgplus.png"> --%>
					<%}
					else{continue;}%>
				<%} %>
			<%} %>
		</div>
	</div>
</div>

<script>
$(function(){
	$('.main-alImg').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/gallery/albumGet?groupNo=<%=groupNo%>&memberNo=<%=loginMember.getMemberNo()%>",
			type:"post",
			dataType:"html",
			success:function(data){
				$('#content-div').html(data);
			},
			error:function(request){},
			complete:function(){console.log("ok");}
		})
	});
	
})
</script>