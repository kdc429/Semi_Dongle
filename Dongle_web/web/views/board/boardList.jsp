<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.board.model.vo.Board,com.dongle.group.model.vo.Group,
				com.dongle.member.model.vo.Member"%>
<%
	List<Board> list=(List)request.getAttribute("list");
	Member loginMember = (Member)request.getSession().getAttribute("loginMember");
	int groupNo=Integer.parseInt(request.getParameter("groupNo"));
	Group g = (Group)request.getAttribute("group");
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
		text-align: center;
		boder: 1px solid #dddddd;
		padding : 10px;
		
	}
	.table th
	{
		background-color: #F2CB61;
		text-align: center;
		
	}
	#add-btn
	{
		float:right;
		margin:0 0 15px;
	}
	#board-container
	{
		padding: 25px;
	}
	
</style>
	<section id="board-container" >
		<h2>공지사항</h2>
		<% if(loginMember!=null&&loginMember.getMemberId().equals("admin")){%> 
			<input type="button" value="글쓰기" id="add-btn"/>
		 <%} %>  
		<table class="table table-bordered">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<%if(!list.isEmpty()) { %>
			<%for(Board b : list) {%>
				<tr>
					<td><%=b.getBoardNo() %></td>
					<td class="boardView-btn">
							<%=b.getBoardTitle()%>
							<input type="hidden" name="boardNo" id="boardNo" value="<%=b.getBoardNo() %>"/>
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
				<%} 
			}%>
		</table>
		
	</section>
<script>
	$(function(){
		$('.boardView-btn').click(function(){
			console.log($(this).children('input').val());
			var num=$(this).children('input').val();
			$.ajax({
				url:"<%=request.getContextPath() %>/board/boardView?groupNo=<%=groupNo%>&boardNo="+num,
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
		$('#add-btn').click(function(){
			console.log($(this).children('input').val());
			var num=$(this).children('input').val();
			$.ajax({
				url:"<%=request.getContextPath()%>/board/boardForm?groupNo=<%=groupNo%>",
				type:"get",
				dataType:"html",
				success:function(data){
					$('#board-container').html(data);
				}
			});
		});
	});
</script>

		