<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.board.model.vo.*,java.util.*,com.dongle.member.model.vo.Member,com.dongle.group.model.vo.Group"%>
    
<% 
	Board b=(Board)request.getAttribute("board");
	Group g = (Group)request.getAttribute("group");
	BoardPath bp=(BoardPath)request.getAttribute("boardPath");
	Member loginMember = (Member)request.getSession().getAttribute("loginMember");
	int groupNo=(int)request.getAttribute("groupNo");
	List<BoardComment> bclist=(List)request.getAttribute("bclist");
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
	.view-btn
	{
		text-align : center;
	}
	#title
	{
		background-color: #F2CB61;
	}
	/* 댓글테이블 */
	table#tbl-comment
	{
		width:580px; 
		margin:0 auto; 
		border-collapse:collapse; 
		clear:both;
		background-color : #EAEAEA;
	} 
    table#tbl-comment tr td
    {
		border-bottom:1px solid; 
		border-top:1px solid; 
		padding:5px; 
		text-align:left; 
    	line-height:120%;
    }
    table#tbl-comment tr td:first-of-type
    {
    padding: 5px 5px 5px 50px;
    }
    table#tbl-comment tr td:last-of-type 
    {
    	text-align:center; 
    	width: 60px;
    }
    table#tbl-comment button.btn-reply
    {
    	display:none;
    }
    table#tbl-comment button.btn-delete
    {
    	display:none;
    }
    table#tbl-comment tr.lebel1
    {
		background-color:gray;    
    }
    table#tbl-comment tr.level2
    {
		color:gray; 
    	font-size: 14px;
    }
    table#tbl-comment sub.board-comment-writer 
    {
    	color:navy; 
    	font-size:14px;
    }
    table#tbl-comment sub.board-comment-date 
    {
		color:tomato; 
    	font-size:10px;
    }
    table#tbl-comment tr.level2 td:first-of-type
    {
    	padding-left:100px;
    }
    table#tbl-comment tr.level2 sub.comment-writer 
    {
    	color:#8e8eff; 
    	font-size:14px
    }
    table#tbl-comment tr.level2 sub.comment-date 
    {
    	color:#ff9c8a; 
    	font-size:10px
    } 
    #report
    {
    	float:right;
    }
	
	
</style>
	<section id="board-container">
		<form name="deleteFrm" method="post" enctype="multipart/form-data">
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
						<td>
							<%
							if(bp.getBoardFileNewPath()!=null){%> 
								<a href="javascript:fn_fileDownLoad('<%=bp.getBoardFileNewPath() %>','<%=bp.getBoardFileOldPath()%>');"> 
								<img alt="첨부파일" src="<%= request.getContextPath() %>/images/board_images/file.png"width='16px'/>
								</a> 
									<%=bp.getBoardFileOldPath() %>						
							<%} %> 
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="2"><%=b.getBoardContent()%></td>
					</tr>
				</tbody> 
			</table>
		</form>
		<div class="view-btn">
			<button id='view-list-btn'>목록으로</button>
			<%if(loginMember.getMemberId().equals(b.getBoardWriter())||loginMember.getMemberId().equals("admin")) {%>
			<button id='view-update-btn'>수정하기</button>
			<input type="hidden" value="<%=groupNo%>" name="groupNo" name="groupNo"/>
			<input type="button" id="view-delete-btn" value="삭제하기" />
			<%} %>
		</div>
		<br><br>
		
		
		<div class="comment-editor">
			<input type="hidden" name="boardNo" id="boardNo" 
			value="<%=b.getBoardNo() %>"/>
			<input type="hidden" name="boardCommentWriter" id="boardCommentWriter"
			value="<%=loginMember.getMemberId() %>"/>
			<input type="hidden" name="boardCommentLevel" id="boardCommentLevel"
			value="1"/>
			<input type="hidden" name="boardCommentRef" id="boardCommentRef"
			value="0"/>
			<textarea cols='75' rows='3' name="boardCommentContent" id="boardCommentContent" placeholder="댓글을 입력하세요."></textarea>
			<button id="comment-insert-btn">등록</button>
		</div>
		<!-- 댓글목록 테이블 -->
		<table id="tbl-comment">
			<%if(bclist!=null) {
				for(BoardComment c : bclist){
					if(c.getBoCommentLevel()==1){
			%>
					<tr class='level1'>
						<td>
							<sub class="board-comment-writer">
							<%=c.getGroupMemberNickname()%></sub>
							<sub class="board-comment-date">
							<%=c.getBoCommentDate()%></sub>
							<br/><br/>
							<%=c.getBoCommentContent() %>
							<a id="report" href='*' >신고</a>
						</td>
						 <td>
							<button class="comment-reply-btn" value="<%=c.getBoCommentNo()%>">답글</button>
							<%if(loginMember.getMemberId()!=null&&(loginMember.getMemberId().equals(c.getGroupMemberNickname())||loginMember.getMemberId().equals("admin"))){%>
							<button class="comment-delete-btn" value="<%=c.getBoCommentNo() %>">삭제</button>
							<%} %>
						</td> 
					</tr>		
					<%} else {%>
					<tr class='level2'>
						<td>
							<sub class="board-comment-writer">
							<%=c.getGroupMemberNickname()%></sub>
							<sub class="board-comment-date">
							<%=c.getBoCommentDate()%></sub>
							<br/></br>
							<%=c.getBoCommentContent() %>
						</td>
						<td>
							<%if(loginMember.getMemberId()!=null&&(loginMember.getMemberId().equals(c.getGroupMemberNickname())||loginMember.getMemberId().equals("admin"))){%>
							<button class="reply-delete-btn" value="<%=c.getBoCommentNo() %>">삭제</button>
							<%} %>
						</td>
					</tr>
					<%}	
				} 
			}%>
		</table>
</section>
		
<script>
$(function(){
	$('#comment-insert-btn').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/board/commentInsert?groupNo=<%=groupNo%>",
			data:{"boardNo":$('#boardNo').val(),
				"boardCommentContent":$('#boardCommentContent').val(),
				"boardCommentLevel":$('#boardCommentLevel').val(),
				"boardCommentRef":$('#boardCommentRef').val(),
			},
			type:"post",
			success:function(data){
				console.log(data);
				alert(data);
				$.ajax({
					url:"<%=request.getContextPath()%>/board/boardView?groupNo=<%=b.getGroupNo()%>&boardNo=<%=b.getBoardNo()%>",
					type:"post",
					dataType:"html",
					success:function(data){
						$('#board-container').html(data);
					}
				});
			}
		});
	});
});
				
	 $('.comment-reply-btn').on('click',function(e){
		 console.log($(this).val());
		<%if(loginMember!=null){%>
			var tr=$("<tr></tr>");
			var html="<td style='display:none;text-align:left;' colspan='2'>";
			html+="<div>";
			html+="<input type='hidden' name='boardNo' id='boardNo' value='<%=b.getBoardNo()%>'/>";
			html+="<input type='hidden' name='boardCommentLevel' id='boardCommentLevel' value='2'/>";
			html+="<input type='hidden' name='boardCommentRef' id='boardCommentRef2' value='"+$(this).val()+"'/>";
			html+="<textarea name='boardCommentContent' id='boardCommentContent2' cols='60' rows='1'></textarea>";
			html+="<button value='"+$(this).val()+"' id='comment-insert-btn' style='height:20px'>등록</button>";
			html+="</div></td>";
			tr.html(html);
			tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
			$(this).off('click');
			
			tr.find('div').click(function(e){
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
				$.ajax({
					url:"<%=request.getContextPath()%>/board/commentInsert",
					data:{"groupNo":<%=groupNo%>,
						"boardNo":$('#boardNo').val(),
						"boardCommentLevel":2,
						"boardCommentContent":$('#boardCommentContent2').val(),
						"boardCommentRef":$('#boardCommentRef2').val()
					},
					type:"post",
					success:function(data){
						alert("Message: "+data);
						$.ajax({
							url:"<%=request.getContextPath()%>/board/boardView?groupNo=<%=b.getGroupNo()%>&boardNo=<%=b.getBoardNo()%>",
							type:"post",
							dataType:"html",
							success:function(data){
								$('#board-container').html(data);
							}
						});
					},
					error:function(request){console.log(request);}
				})
				});
				tr.find("textarea").focus();
			
		<%}%>
		
	});  
	 
	
	
	
	
	
	<%-- $(function(){
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
	});	 --%>

	function fn_loginAlert()
	{
		alert("로그인 후 이용할 수 있습니다.");
		$('#userId').focus();
	}
	//댓글 삭제
	$(function(){
		$('.reply-delete-btn').click(function(){
			$.ajax({
				url:"<%=request.getContextPath() %>/board/boardView?groupNo=<%=groupNo%>",
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
<script>
	//다운로드!
	function fn_fileDownLoad(rName, oName)
	{
		var url="<%=request.getContextPath()%>/board/boardFileDownLoad";
		oName=encodeURIComponent(oName);
		location.href=url+"?oName="+oName+"&rName="+rName;
	}
	//목록으로
	$(function(){
		$('#view-list-btn').click(function(){
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
	//글 수정
	$(function(){
		$('#view-update-btn').click(function(){		
			$.ajax({
				url:"<%=request.getContextPath() %>/board/boardUpdate?groupNo=<%=groupNo%>&boardNo=<%=b.getBoardNo()%>",
				type:"post",
				dataType:"html",
				success:function(data){
					$('#board-container').html(data);
				}
			});
		});
	});
	//글 삭제
	$(function(){
		$('#view-delete-btn').click(function(){
			if(confirm('정말로 삭제하시겠습니까?')==true)
			{
				$.ajax({
					url:"<%=request.getContextPath() %>/board/boardDelete?boardNo=<%=b.getBoardNo()%>&groupNo=<%=b.getGroupNo()%>",
					type:"post",
					dataType:"html",
					processData:false,
					contentType:false,
					success:function(data){
						alert(data);
						$.ajax({
							url:"<%=request.getContextPath()%>/board/boardList?groupNo=<%=groupNo%>",
							type:"post",
							dataType:"html",
							success:function(data){
								$('#board-container').html(data);
							}
						});
					}
				});
			}
			else
			{
				return;
			}
			
		});
	});
	
	<%-- //댓글 삭제
	$(function(){
		$('#comment-delete-btn').click(function(){
			if(confirm('정말로 삭제하시겠습니까?')==true)
			{
				$.ajax({
					url:"<%=request.getContextPath() %>/board/boardDelete?boardNo=<%=b.getBoardNo()%>&groupNo=<%=b.getGroupNo()%>",
					type:"post",
					dataType:"html",
					processData:false,
					contentType:false,
					success:function(data){
						alert(data);
						$.ajax({
							url:"<%=request.getContextPath()%>/board/boardList?groupNo=<%=groupNo%>",
							type:"post",
							dataType:"html",
							success:function(data){
								$('#board-container').html(data);
							}
						});
					}
				});
			}
			else
			{
				return;
			}
			
		});
	});
	 --%>

	
</script>		