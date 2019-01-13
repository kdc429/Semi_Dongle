<%@page import="com.dongle.gallery.model.vo.AlbumCategory,java.util.*,com.dongle.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	List<AlbumCategory> list = (List)request.getAttribute("list");
 	int groupNo=(int)request.getAttribute("groupNo");
 	Member loginMember = (Member)session.getAttribute("loginMember");
 	String memberId=(String)request.getAttribute("memberId");
 	int count=1;
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/gallery_style.css"> 
<script>
function galClick(obj){
	var frm=$('#albumFolder');
	var groupNo =$(obj).nextAll('#groupNo');
	var albumCode = $(obj).nextAll('#albumCode');
	var memberId = $(obj).nextAll('#memberId');
	console.log(albumCode[0].value);
	url="<%=request.getContextPath()%>/galleryGet?groupNo="+groupNo[0].value+"&albumCode="+albumCode[0].value+"&memberId="+memberId[0].value;
 	/* location.href 는 입력된 url을 바로 띄워주는 것으로 galleryGet? 을 통해 서블릿과 연동됨 */
	location.href=url;
}
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
	var url="<%=request.getContextPath()%>/albumPlus?groupNo=<%=groupNo%>&memberId<%=memberId%>"; 
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

</head>
<body>
<hr>
<section id="album-container">
	<table border="1" width="370px" id="albumPlus-tbl">
		<tr>
			<td>
				<%if(loginMember.getMemberId()!=null&loginMember.getMemberId().equals("admin")){ %>
					<form action="" method="post" name="albumPlus" id="albumPlus" onsubmit="return fn_validateFrm()">
						<input style="float:right;" type="button" id="albumPlusBtn" name="albumPlusBtn" value="앨범 추가" onclick="albumPlusClick()"/>
						<input style="float:right;" type="button" id="albumDeleteBtn" name="albumDeleteBtn" value="앨범 삭제" onclick="albumDeleteClick()"/>
						<input type="hidden" name="memberId" id="memberId" value="<%=memberId%>"/>
					</form>
				<%} %>
			</td>
		</tr>
	</table>
	<form name="albumFolder" id="albumFolder">
		<table border='1'>
			<%if(list.size()!=0){ %>
				<%for(AlbumCategory t : list){ %>
					<%if(count%2==1){%>
						<tr>
						</tr>
						<td class="albumFolBox">
							<img class="alImg" src="<%=request.getContextPath() %>/images/gallery/defaultimg.png" onclick="galClick(this);">
							<p>[&nbsp;<%=t.getAlbumName()%>&nbsp;]</p>
							<input type="hidden" name="groupNo" id="groupNo" value="<%=t.getGroupNo()%>"/>
							<input type="hidden" name="albumCode" id="albumCode" value="<%=t.getAlbumCode()%>"/>
							<input type="hidden" name="memberId" id="memberId" value="<%=memberId%>"/>
						</td>
						<%count++;%>
					<%} 
					else{%>
						<td class="albumFolBox">
							<img class="alImg" src="<%=request.getContextPath() %>/images/gallery/defaultimg.png" onclick="galClick(this);">
							<p>[&nbsp;<%=t.getAlbumName()%>&nbsp;]</p>
							<input type="hidden" name="groupNo" id="groupNo" value="<%=t.getGroupNo()%>"/>
							<input type="hidden" name="albumCode" id="albumCode" value="<%=t.getAlbumCode()%>"/>
							<input type="hidden" name="memberId" id="memberId" value="<%=memberId%>"/>
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
</section>
</body>
</html>