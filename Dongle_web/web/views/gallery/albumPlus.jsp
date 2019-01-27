<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.gallery.model.vo.AlbumCategory" %>
<%
	int groupNo=(int)request.getAttribute("groupNo");
	String memberId=(String)request.getAttribute("memberId");
	List<AlbumCategory> list = (List)request.getAttribute("list");
	int count=0;
	int listSize = list.size();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앨범 추가하기</title>
<style>
div#albumPlus-container{padding:0 auto;margin-left:200px; margin-top:30px;}
div#albumPlus-container input{font-size:18px;font-family:'a흑진주L';}
div#albumPlus-container button{color:gray;border:none;font-family:'a흑진주L';}
</style>
</head>
<script>

$(function(){
	
	$('.fn_albumPlus').click(function(){
		console.log($('#albumNameP').val()+" : "+$('#albumNameP').val().trim().length);
		if($('#albumNameP').val().trim().length==0)
		{
			alert('앨범명을 입력해주세요.');
			return;
		}
		for(var i=0;i< <%=list.size()%>; i++)
		{
			if($('#albumNameP').val()=='<%=list.get(count).getAlbumName()%>')
			{
				alert('이미 존재하는 앨범입니다. 다시 입력해주세요.');
				return;
			}
			<%count++;%>
		}
		$.ajax({
			url:"<%=request.getContextPath()%>/gallery/albumInsert",
			data:{'albumNameP':$('#albumNameP').val(),
				'groupNo':<%=groupNo%>
			},
			type:'post',
			dataType:'html',
			success:function(data){
				alert('Message: '+data);
				
				$.ajax({
					url:"<%=request.getContextPath()%>/gallery/albumGet?groupNo=<%=groupNo%>",
					dataType:"html",
					type:"post",
					success:function(data){
						$('#album-container').html(data);
					}
				});
			}
		});
	});
});
	
</script>
<body>
	<div id="albumPlus-container">
		<table>
			<tr>
				<td>
					<h4>현재 사용 중인 앨범 명</h4>
					<%for(int i=0;i<list.size();i++){ %>
						<p class='alname'> &nbsp;[&nbsp;<%=list.get(i).getAlbumName()%>&nbsp;]&nbsp; </p>
					<%} %>
				</td>
			</tr>
			<tr>
				<td>앨범 명:&nbsp;<input type="text" name="albumNameP" id="albumNameP" size="20"/></td>
				<td>
					<button class="fn_albumPlus">확인</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>