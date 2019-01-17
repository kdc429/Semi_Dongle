<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.board.model.vo.Board" %>
    
<% 
	Board b=(Board)request.getAttribute("board");
	System.out.println(b);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동글</title>
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
</style>
</head>
<body>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th colspan="3">공지사항</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th style="width: 20%;">글 제목</th>
					<td colspan="2"><%=b.getBoardTitle()%></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td colspan="2"><%=b.getBoardWriter() %></td>
				</tr>					
				<tr>
					<th>작성일자</th>
					<td colspan="2"><%=b.getBoardWriteDate()%></td>
				</tr>					
				<tr>
					<th>조회수</th>
					<td colspan="2"><%=b.getBoardViewCount() %></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><%-- <%if(b.getFilePath()!=null){%>
						<img alt="첨부파일" src="<%= request.getContextPath() %>/images/board_images/file.png"width='16px'>
						<%} %> --%>	
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="5" cols="50" name="content"><%=b.getBoardContent() %></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan='2' id="inputbutton">
						<input type="submit" value="등록하기" onclick="return validate();">
					</td>
				</tr>
			</tbody> 
		</table>
	</div>

		