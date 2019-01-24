<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*,com.dongle.board.model.vo.Board,com.dongle.group.model.vo.Group,
com.dongle.member.model.vo.Member,com.dongle.main.*" %>
<%
	List<Board> list = (List)request.getAttribute("list");
	Member loginMember = (Member)request.getSession().getAttribute("loginMember");
	int groupNo = Integer.parseInt(request.getParameter("groupNo"));
%>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

 
<style>
	section h3{
		font-family: '나눔스퀘어라운드 Regular';
		text-align : center;
		margin : 10px;
		color:black;
	}
	section table{
		width:298px;
	}
	#main-table-bordered-th1{
		
		background-color: #8EC7D0;
		text-align: center;
	}
	section table.main-table-bordered>th{
		border : 1px solid darkgray;
	}
	
	input.add-btn
	{
		float:right;
		margin:0 0 15px;
		background-color:#F2CB61;
	}
</style>
	<section id="main-board-container">
		<h3 style="font-family: '나눔스퀘어라운드 Regular';">공지사항</h3>
		<% if(loginMember!=null&&loginMember.getMemberId().equals("admin")){%> 
		 <%} %>  
		<table class="main-table-bordered">
			<tr id='main-table-bordered-th1'>
				<th style="text-align: center;">번호</th>
				<th id='mini_board_title'style="text-align: center;">제목</th>
				<th style="text-align: center;">작성일</th>
			</tr>
			<%for(Board b : list) {%>
				<tr style="border: 1px solid lightgray;">
					<td><%=b.getBoardNo() %></td>
					<td class="boardView-btn">
						<%-- <a id="boardView-btn" href ="<%=request.getContextPath() %>/board/boardView?boardNo=<%=b.getBoardNo()%>&groupNo=<%=b.getGroupNo()%>"> --%>
							<%=b.getBoardTitle()%>
							<input type="hidden" name="boardNo" id="boardNo" value="<%=b.getBoardNo() %>"/>
						<!-- </a> -->
					</td>
					<td>
						<%=b.getBoardWriteDate()%>
					</td>
				</tr>
				<%} %>
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
             $('#content-div').html(data);
          },
          error:function(error,msg){console.log("---"+error+msg);}
       });
    });
 });
</script>

		