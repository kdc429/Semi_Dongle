<%@page import="com.dongle.gallery.model.vo.AlbumCategory,java.util.*,com.dongle.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	List<AlbumCategory> list = (List)request.getAttribute("list");
 	int groupNo=(int)request.getAttribute("groupNo");
 	Member loginMember = (Member)session.getAttribute("loginMember");
 	int count=1;
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
})
function fn_validateFrm(){
	return true;
}
function albumPlusClick(){
	if(<%=list.size()%>==6){
		alert('앨범은 최대 6개까지 만들 수 있습니다.');
		return;
	}
	//팝업창에 대한 설정해주기@ : window.open(url,title,shape)로 열 수 있음
	// 서블릿으로 넘겨줄 매핑주소
	var url="<%=request.getContextPath()%>/albumPlus?groupNo=<%=groupNo%>"; 
	/* 내부에서 체크함 */
	var title="albumPlus";
	var shape="left=500px, top=300px, width=500px, height=200px";
	var popup=open("",title,shape);
	albumPlus.target=title;
	albumPlus.action=url;
	albumPlus.method="post";
	//입력한 값을 제출하는 것
	albumPlus.submit();
}
</script>

<div id="album-container">
	<table width="370px" id="albumPlus-tbl" style='margin-left:25%;'>
		<tr>
			<td>
				<%if(loginMember.getMemberId()!=null&loginMember.getMemberId().equals("admin")){ %>
				<div><br></div>
					<form action="" method="post" name="albumPlus" id="albumPlus" onsubmit="return fn_validateFrm()">
						<input style="float:right; border:none; background-color:rgb(0,0,0,0); margin-left:10px;" type="button" id="albumPlusBtn" name="albumPlusBtn" value="앨범 추가" onclick="albumPlusClick()"/>
						<input style="float:right; border:none; background-color:rgb(0,0,0,0);" type="button" id="albumDeleteBtn" name="albumDeleteBtn" value="앨범 삭제" onclick="albumDeleteClick()"/>
					</form>
				<%} %>
			</td>
		</tr>
	</table>
	<form name="albumFolder" id="albumFolder">
		<table  style='margin-left:25%;'>
			<%if(list.size()!=0){ %>
				<%for(AlbumCategory t : list){ %>
					<%if(count%2==1){%>
						<tr>
						</tr>
						<td class="albumFolBox">
							<img class="alImg" src="<%=request.getContextPath() %>/images/gallery/defaultimg.png">
							<p>[&nbsp;<%=t.getAlbumName()%>&nbsp;]</p>
							<input type="hidden" name="groupNo" id="groupNo" value="<%=t.getGroupNo()%>"/>
							<input type="hidden" name="albumCode" id="albumCode" value="<%=t.getAlbumCode()%>"/>
						</td>
						<%count++;%>
					<%} 
					else{%>
						<td class="albumFolBox">
							<img class="alImg" src="<%=request.getContextPath() %>/images/gallery/defaultimg.png">
							<p>[&nbsp;<%=t.getAlbumName()%>&nbsp;]</p>
							<input type="hidden" name="groupNo" id="groupNo" value="<%=t.getGroupNo()%>"/>
							<input type="hidden" name="albumCode" id="albumCode" value="<%=t.getAlbumCode()%>"/>
						</td>
						<%count++;%> 
					<%} %>
				<%} %>
			<%}
			else{%>
				<div>
					<h3>등록된 앨범이 없습니다.</h3>
				</div>
			<%} %>
		</table>
	</form>
</div>
