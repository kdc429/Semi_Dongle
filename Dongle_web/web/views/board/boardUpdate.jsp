<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.board.model.vo.Board,com.dongle.board.model.vo.BoardPath"%>
    
<% 
	Board b=(Board)request.getAttribute("board");
	BoardPath bp=(BoardPath)request.getAttribute("boardPath");
	int groupNo = (int)request.getAttribute("groupNo");
	
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
		boder : 1px solid #dddddd;
	}
	.table thead th
	{
		background-color : #F2CB61;
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
	#inputbutton
	{
		text-align : center;
	}
	#update-btn-add
	{
		text-align : center;
	}
	#update-add
	{
		text-align : center;
	}
</style>
<script>
	function validate(){
		var content=$('[name=content]').val();
		if(content.trim().length==0)
		{
			alert("내용을 입력하세요!");
			return false;
		}
		return true;
	}
</script>
	<div class="board-container">
		<table class="table table-bordered">
			<thead>
				<br><br>
				<tr>
					<th colspan="3">공지사항</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th style="vertical-align:middle">번호</th>
					<td>
						<input style="background-color:#EAEAEA" type="text" name="no" value="<%=bp.getBoardNo()%>" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th style="vertical-align:middle" style="width:20%;">글 제목</th>
					<td id="title">
						<input type="text" name='title' value="<%=bp.getBoardTitle()%>" required="required"/>
					</td>
				</tr>
				<tr>
					<th style="vertical-align:middle">작성자</th>
					<td id="writer" >
						<input  style="background-color:#EAEAEA" type="text" name='writer' value="<%=bp.getBoardWriter() %>" readonly="readonly"/>
					</td>
				</tr>					
				<tr>
					<th style="vertical-align:middle">첨부파일</th>
					<td>
						<%if(bp.getBoardFileOldPath()!=null){%>
							<input type="file" name="upfile" value="<%=bp.getBoardFileNewPath()%>"/>
							<span id="fname"><%=bp.getBoardFileOldPath()%></span>
							<input type="hidden" name="old_file"value="<%=bp.getBoardFileOldPath()%>"/>
						<%} else {%>
							<input type="file" name="upfile"/>
						<%} %>
					</td>
				</tr>
				<tr>
					<th style="vertical-align:middle">내용</th>
					<td>
						<textarea rows="5" cols="50" name='content' id='content'><%=bp.getBoardContent()%></textarea>
					</td>
				</tr>
			</tbody> 
				<tr>
					<td colspan="2" id="update-add">
						<button id='update-btn-list'>목록으로</button>
						<input type="hidden" value="<%=groupNo%>" name="groupNo" />
						<input type="button" value="등록하기" id="update-btn-add" onclick="return validate();"/>
					</td>
				</tr>			
		</table>
	</div> 
<script>
$(function(){
	$('#update-btn-list').click(function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/board/boardList?groupNo=<%=groupNo%>",
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
	$('#update-btn-add').click(function(){
		console.log($(this).children('input').val());
		var num=$(this).children('input').val();
		$.ajax({
			url:"<%=request.getContextPath()%>/board/boardUpdateEnd?groupNo=<%=groupNo%>",
			type:"get",
			dataType:"html",
			success:function(data){
				$('#board-container').html(data);
			}
		});
	});
});
</script>
		