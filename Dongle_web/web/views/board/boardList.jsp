<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.board.model.vo.Board,com.dongle.board.model.vo.BoardPath"%>
<%
	List<Board> list=(List)request.getAttribute("list");
	int groupNo=Integer.parseInt(request.getParameter("groupNo"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동글</title>
	<script src='js/jquery-3.3.1.js'></script></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
<style>
	.table
	{
		text-align: center;
		boder: 1px solid #dddddd;
		border-radius: 10px;
		
	}
	.table th
	{
		background-color: #F2CB61;
		text-align: center;
	}
	input#btn-add
	{
		float:right;
		margin:0 0 15px;
		background-color:#F2CB61;
	}
</style>
<script>
	function fn_add()
	{
		location.href="<%=request.getContextPath()%>/board/boardForm?groupNo=<%=groupNo%>";
	}
</script>
</head>
<body>
	<section id="notice-container">
		<h2>공지사항</h2>
		<%-- <% if(LoginMember!=null&&LoginMember.getMemberId().equals("admin")){%> --%>
			<input type="button" value="글쓰기" id="btn-add" onclick="fn_add()"/>
		<%-- <%} %>  --%>  
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<%for(Board b : list) {%>
				<tr>
					<td><%=b.getBoardNo() %></td>
					<td>
						<a href="<%=request.getContextPath() %>/board/boardView?boardNo=<%=b.getBoardNo()%>&groupNo=<%=b.getGroupNo()%>">
							<%=b.getBoardTitle()%>
						</a>
					</td>
					<td>
						<%=b.getBoardWriter() %>
					</td>
					<td>
						<%=b.getBoardWriteDate()%>
					</td>
					<td>
						<%=b.getBoardViewCount() %>
					</td>
				</tr>
				<%} %>
		</table>
	</section>
</body>
</html>
		