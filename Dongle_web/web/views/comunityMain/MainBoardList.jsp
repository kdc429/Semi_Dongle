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
	div h4{
		font-family: '나눔스퀘어라운드 Regular';
		/* text-align : center; */
		margin : 10px;
		color:black;
	}
	div table{
		width: 500px;
	}
	table#main-table-bordered tr td{
		
		background-color: #8EC7D0;
		text-align: center;
		margin-left:20px;
	}
	
	input.add-btn
	{
		float:right;
		margin:0 0 15px;
		background-color:#F2CB61;
	}
</style>
	<div id="main-board-container">
		<h4 style="font-family: '나눔스퀘어라운드 Regular';"><b>[ 공지사항 ]</b></h4>
		<hr>
		<table class="main-table-bordered" style='margin-left:17px;'>

			<%for(Board b : list) {%>
				<tr style="border-bottom: 1px solid lightgray;">
					<td><%=b.getBoardNo() %></td>
					<td class="boardView-btn">
						<%-- <a id="boardView-btn" href ="<%=request.getContextPath() %>/board/boardView?boardNo=<%=b.getBoardNo()%>&groupNo=<%=b.getGroupNo()%>"> --%>
							<b><%=b.getBoardTitle()%></b>
							<input type="hidden" name="boardNo" id="boardNo" value="<%=b.getBoardNo() %>"/>
						<!-- </a> -->
					</td>
					<td>
						<span><img src='<%=request.getContextPath()%>/images/board_images/adminstar.png' style='width:15px;height:15px;'></span>
						<span><%=b.getBoardWriter() %></span>
					</td>
					<td>
						<%=b.getBoardWriteDate()%>
					</td>
				</tr>
				<%} %>
		</table>
		
	</div>
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

		