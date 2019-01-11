<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동글</title>
	<script src='js/jquery-3.3.1.js'></script>
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
	.table input
	{
		vertical-align: right;
	}
</style>
</head>
<body>
	<div class="container">
		<form action="<%=request.getContextPath() %>/board/boardFormEnd" method="post" enctype="multipart/form-data">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th colspan="3">공지사항</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th style="width: 20%;">글 제목</th>
						<input type="text"  name='title' required="required"/>
					</tr>
					<tr>
						<th>작성자</th>
						<input type="text" name='writer' <%-- value="<%=logginMember.getMemberId() %>" --%>readonly="readonly"/>
					</tr>					
					<tr>
						<th>첨부파일</th>
						<td><%-- <%if(b.getFilePath()!=null){%>
							<img alt="첨부파일" src="<%= request.getContextPath() %>/images/file.png"width='16px'>
							<%} %> --%>	
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<textarea rows="5" cols="50" name='content'>
						</textarea>
					</tr>
					<tr>
						<th colspan="2">
							<input type="submit" value="등록하기" 
							onclick="return validate();"/>
						</th>
					</tr>			
				</tbody> 
			</table>
		</form>
	</div>
</body>
</html>
		