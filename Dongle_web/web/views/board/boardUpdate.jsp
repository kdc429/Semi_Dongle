<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.board.model.vo.Board,com.dongle.board.model.vo.BoardPath,com.dongle.member.model.vo.Member"%>
    
<% 
	Board b=(Board)request.getAttribute("board");
	BoardPath bp=(BoardPath)request.getAttribute("boardPath");
	int groupNo=(int)request.getAttribute("groupNo");
	Member loginMember = (Member)request.getSession().getAttribute("loginMember");
	
%>
<meta charset="UTF-8">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	.table
	{
		text-align : center;
	}
	.table thead th
	{
		background-color : rgba(245,245,245);
		text-align: center;
	}
	.table tbody tr th 
	{
		background-color : #EAEAEA;
		text-align : center;
	}
	.table td
	{
		min-height : 200px;
		text-align : left;
	}
	span#fname
	{
    	position:absolute;
    	left:181px;
    	top:244px;
    	width: 440px;
    	background : white;
    }
    #update-no
    {
    	width : 250px;
    }
    #update-title
    {
    	width : 250px;
    }    
    #update-writer
    {
    	width : 250px;
    }
    #update-list-btn
    {
    	text-align:"center";
    }
</style>
		<form name="updateFrm" method="post" enctype="multipart/form-data">
			<table class="table">
				<thead>
					<br/><br/>
					<tr>
						<th colspan="3" style="border-top:1px solid #dddddd; background-color: rgba(112,136,172); font-family: 'netmarble Medium';">공지사항</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th style="vertical-align:middle; background-color:rgba(245,245,245); color:black;">번호</th>
						<td>
							<input style="background-color:#EAEAEA" type="text" name="no" id='update-no' value="<%=bp.getBoardNo()%>" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th style="vertical-align:middle; width:20%; background-color:rgba(245,245,245); color:black;">글 제목</th>
						<td id="title">
							<input type="text" name='title' id='update-title' value="<%=bp.getBoardTitle()%>" required="required"/>
						</td>
					</tr>
					<tr>
						<th style="vertical-align:middle; background-color:rgba(245,245,245); color:black;">작성자</th>
						<td id="writer" >
							<input  style="background-color:#EAEAEA" type="text" id='update-writer' name='writer' value="<%=bp.getBoardWriter() %>" readonly="readonly"/>
						</td>
					</tr>					
					<tr>
						<th style="vertical-align:middle; background-color:rgba(245,245,245); color:black;">첨부파일</th>
						<td>
							<%if(bp.getBoardFileOldPath()!=null){%>
								<input type="file" name="upfile" value="<%=bp.getBoardFileOldPath()%>"/>
								<span id="fname"><%=bp.getBoardFileOldPath()%></span> 
								<input type="hidden" name="old_file"value="<%=bp.getBoardFileOldPath()%>"/>
							<%} else {%>
								<input type="file" name="upfile"/>
							<%} %>
						</td>
					</tr>
					<tr>
						<th style="border-bottom:1px solid #dddddd; vertial-align:middle;background-color:rgba(245,245,245); color:black;">내용</th>
						<td>
							<textarea style="width: 540px" rows="5" cols="50" name='content' id='update-content'><%=bp.getBoardContent()%></textarea>
						</td>
					</tr>
				</tbody> 
			</table>
		</form>
		<div class="update-btn" style="float: left; margin-left: 220px;">
			<button type="button" class="btn btn-default" id='update-list-btn' style="text-align:center">목록으로</button>
			<input type="hidden" value="<%=groupNo%>" name="groupNo" />
			<button type="button" class="btn btn-default" id='update-submit-btn' style="text-align:center">수정하기</button>
			<br/><br/>
		</div>
<script>
$(function(){
	$("[name=upfile]").change(function(){
		if($(this).val()=="")
		{
			$("#fname").show();
		}
		else
		{
			$("#fname").hide();
		}
	});
});

$(function(){
	$('#update-list-btn').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/board/boardList?groupNo=<%=groupNo%>",
			type:"post",
			dataType:"html",
			success:function(data){
				$('#board-container').html(data);
			},
			error:function(error,msg){console.log("---"+error+msg);}
		});
	});
});


$(function(){
	$('#update-submit-btn').click(function(){
		var content=$('[name=content]').val();
		if(content.trim().length==0)
		{
			alert("내용을 입력하세요!");
			return false;
		}
		var data = new FormData();
		data.append('upfile',updateFrm.upfile.files[0]);
		data.append('oldfile',"<%=bp.getBoardFileOldPath()%>");
		data.append('groupNo',<%=groupNo%>);
		data.append('boardNo',<%=bp.getBoardNo()%>);
		data.append('writer',$('#update-writer').val());
		data.append('content',$('#update-content').val());
		data.append('title',$('#update-title').val());
		data.append('loginMember','<%=loginMember.getMemberId()%>');
		$.ajax({
			url:"<%=request.getContextPath() %>/board/boardUpdateEnd",
			data:data,
			type:"post",
			dataType:"html",
			processData:false,
			contentType:false,
			success:function(data){
				alert(data);
				$.ajax({
					url:"<%=request.getContextPath()%>/board/boardList?groupNo=<%=groupNo%>",
					type:"post",
					dataType:"html",
					success:function(data){
						$('#board-container').html(data);
					}
				});
			}
		});
	});
});
</script>
		