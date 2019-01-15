<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    
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
						<th style="width: 20%;">글 제목</th>
						<td id="title">
							<input type="text" name='title' required="required"/>
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td id="writer" >
							<input type="text" name='writer' value="admin" readonly="readonly"/>
						</td>
					</tr>					
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="file" name="upfile"/>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="5" cols="50" name='content'></textarea>
						</td>
					</tr>
				</tbody> 
					<tr>
						<td colspan="2" id="board-add">
							<input type="button" value="등록하기" onclick="return validate();"/>
						</td>
					</tr>			
			</table>
		</form>
	</div>
		