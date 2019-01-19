<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="com.dongle.board.model.vo.Board"%>
<%
	int groupNo = (int)request.getAttribute("groupNo");
%>
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
		border : 1px solid #dddddd;
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
	.table tbody td
	{
		text-align: left;
	} 
	#board-add
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
</head>
<body>
	<div class="board-container">
		<form action="<%=request.getContextPath() %>/board/boardFormEnd" method="post" enctype="multipart/form-data">
			<table class="table table-bordered">
				<thead>
					<br><br>
					<tr>
						<th colspan="3">공지사항</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th style="vertical-align:middle" style="width: 20%;">글 제목</th>
						<td id="title">
							<input type="text" name='title' required="required"/>
						</td>
					</tr>
					<tr>
						<th style="vertical-align:middle">작성자</th>
						<td id="writer" >
							<input style="background-color:#EAEAEA"type="text" name='writer' value="admin" readonly="readonly"/>
						</td>
					</tr>					
					<tr>
						<th style="vertical-align:middle">첨부파일</th>
						<td>
							<input type="file" name="upfile"/>
						</td>
					</tr>
					<tr>
						<th style="vertical-align:middle">내용</th>
						<td>
							<textarea rows="5" cols="50" name='content'></textarea>
						</td>
					</tr>
				</tbody> 
					<tr>
						<td colspan="2" id="board-add">
							<button id='Form-btn-list'>목록으로</button>
							<input type="hidden" value="<%=groupNo%>" name="groupNo" name="groupNo"/>
							<input type="submit" value="등록하기" onclick="return validate();"/>
						</td>
					</tr>			
			</table>
		</form>
	</div>
<script>
$(function(){
	$('#Form-btn-list').click(function(){
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



</script>
		