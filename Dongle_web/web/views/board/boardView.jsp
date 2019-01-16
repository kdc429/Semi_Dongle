<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.board.model.vo.*,java.util.*,com.dongle.member.model.vo.Member"%>
    
<% 
	Board b=(Board)request.getAttribute("board");
	BoardPath bp=(BoardPath)request.getAttribute("boardPath");
	List<BoardComment> bclist=(List)request.getAttribute("bclist");
	Member loginMember = (Member)request.getSession().getAttribute("loginMember");
%>
    
<style>
	
	.table
	{
		text-align: center;
		boder: 1px solid #dddddd;
		padding : 10px;
		height: 200px;
	}
	.table th
	{
		background-color: #EAEAEA;
		text-align : center;
		height: 10px;
	}
	.table td
	{
		text-align : left;
	}
	#inputbutton
	{
		text-align : center;
	}
	#title
	{
		background-color: #F2CB61;
	}
	
	
</style>
	<section id="board-container">
		<table class="table table-bordered">
			<thead>
				<br><br>
				<tr>
					<th colspan="3" id="title">공지사항</th>
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
					<td colspan="2"><%=b.getBoardViewCount()%></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><%if(bp.getBoardFileNewPath!=null){%>
						<img alt="첨부파일" src="<%= request.getContextPath() %>/images/file.png"width='16px'>
						<%} %> 
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="2"><%=b.getBoardContent()%></td>
				</tr>
				
				<tr>
					<td colspan='2' id="inputbutton">
						<input type="button" value="목록으로" onclick="fn_boardList()">
						<input type="button" value="수정하기" onclick="fn_updateBoard()">
						<input type="button" value="삭제하기" onclick="fn_deleteBoard()">
					</td>
				</tr>
			</tbody> 
		</table>
		<div class="comment-editor">
				<form action="<%=request.getContextPath() %>/board/commentInsert" name="boardCommentFrm" 
				method="post">
					<input type="hidden" name="boardRef" 
					value="<%=b.getBoardNo() %>"/>
					<input type="hidden" name="boardCommentWriter"
					value="<%=b.getBoardWriter()%>"/>
					<input type="hidden" name="boardCommentLevel"
					value="1"/>
					<input type="hidden" name="boardCommentRef"
					value="0"/>
					<textarea cols='70' rows='2' name="boardCommentContent"></textarea>
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
			<!-- 댓글목록 테이블 -->
			<table id="tbl-comment">
				<%if(bclist!=null) {
					for(BoardComment c : bclist){
						if(c.getBoCommentLevel()==1){
				%>
						<tr class='level1'>
							<td>
								<sub class="comment-writer">
								<%=loginMember.getMemberId()%></sub>
								<sub class="comment-date">
								<%=c.getBoCommentDate()%></sub>
								<br/>
								<%=c.getBoCommentContent() %>
							</td>
							<td>
								<button class="btn-reply" 
								value="<%=c.getBoCommentNo() %>">답글</button>
								<%if(loginMember.getMemberId()!=null&&
								(loginMember.getMemberId().equals(c.getMemberNo())
								||loginMember.getMemberId().equals("admin"))){%>
								<button class="btn-delete" 
								value="<%=c.getBoCommentNo() %>">삭제</button>
								<%} %>
							</td>
						</tr>		
						<%} else {%>
						<tr class='level2'>
							<td>
								<sub class="comment-writer">
								<%=c.getGroupMemberNickname()%></sub>
								<sub class="comment-date">
								<%=c.getBoCommentDate()%></sub>
								<br/>
								<%=c.getBoCommentContent() %>
							</td>
							<td>
								
							</td>
						</tr>
						<%}	
					} 
				}%>
			</table>
			
</section>
			
			<script>
				<%-- $('.btn-reply').on('click',function(e){
					<%if(loginMember!=null){%>
						var tr=$("<tr></tr>");
						var html="<td style='display:none;text-align:left;' colspan='2'>";
						html+="<form action='<%=request.getContextPath()%>/board/commentInsert' method='post'>";
						html+="<input type='hidden' name='boardRef'value='<%=b.getBoardNo()%>'/>";
						html+="<input type='hidden' name='boardCommentWriter' value='<%=c.getGroupMemberNickname()%>'/>";
						html+="<input type='hidden' name='boardCommentLevel' value='2'/>";
						html+="<input type='hidden' name='boardCommentRef' value='"+$(this).val()+"'/>";
						html+="<textarea name='boardCommentContent' cols='60'rows='1'></textarea>";
						html+="<button type='submit' class='btn-insert2'>등록</button>";
						html+="</form></td>";
						tr.html(html);
						tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
						$(this).off('click');
						
						tr.find('form').submit(function(e){
							if(<%=loginMember==null%>)
							{
								fn_loginAlert();
								
								e.preventDefault();
								return;
							}
							var len=$(this).children('textarea').val().trim().length;
							if(len==0)
							{
								e.preventDefault();
							}
							});
							tr.find("textarea").focus();
						
					<%}%>
					
				}); --%>
				
				$(function(){
					$('[name=boardCommentContent]').focus(function(){
						if(<%=loginMember.getMemberId()==null%>)
						{
							fn_loginAlert();
						}
					});
					$('[name=boardCommentFrm]').submit(function(e){
						if(<%=loginMember.getMemberId()==null%>)
						{
							fn_loginAlert();
							e.preventDefault();
							return;
						}
						var len=$('textarea[name=boardCommentContent]').val().trim().length;
						if(len==0)
						{
							alert('내용을 입력하세요~!');
							$('textarea[name=boardCommentContent]').focus();
							e.preventDefault();	
						}
					})
				});	
			
				function fn_loginAlert()
				{
					alert("로그인 후 이용할 수 있습니다.");
					$('#userId').focus();
				}
				
			
			</script>
	<script>
	function fn_boardList()
	{
		location.href="<%=request.getContextPath()%>/board/boardList";
	}
	function fn_deleteBoard()
	{
		if(!confirm('정말로 삭제하시겠습니까?'))
		{
			return;
		}
		$('[name=boardDelFrm]').submit();
	}
	<%-- function fn_updateBoard()
	{
		location.href="<%=request.getContextPath()%>"/board/boardUpdate?no=<%=b.getBoardNo()%>";
	}  --%>
	
</script>

		