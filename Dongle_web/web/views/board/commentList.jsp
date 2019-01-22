<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
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
    	text-align:right; 
    	width: 100px;
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
    table#tbl-comment sub.comment-writer 
    {
    	color:navy; 
    	font-size:14px
    }
    table#tbl-comment sub.comment-date 
    {
		color:tomato; 
    	font-size:10px
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

		<!-- 댓글목록 테이블 -->
		<table id="tbl-comment">
			<%if(bclist!=null) {
				for(BoardComment c : bclist){
					if(c.getBoCommentLevel()==1){
			%>
					<tr class='level1'>
						<td>
							<sub class="comment-writer">
							<%=c.getGroupMemberNickname()%></sub>
							<sub class="comment-date">
							<%=c.getBoCommentDate()%></sub>
							<br/><br/>
							<%=c.getBoCommentContent() %>
							<a id="report" href='*' >신고</a>
						</td>
						 <td>
							<button class="comment-reply-btn" value="<%=c.getBoCommentNo() %>">답글</button>
							<%if(loginMember.getMemberId()!=null&&(loginMember.getMemberId().equals(c.getGroupMemberNickname())||loginMember.getMemberId().equals("admin"))){%>
							<button class="comment-delete-btn" value="<%=c.getBoCommentNo() %>">삭제</button>
							<%} %>
						</td> 
					</tr>		
					<%} else {%>
					<tr class='level2'>
						<td>
							<sub class="comment-writer">
							<%=c.getMemberNo()%></sub>
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
		<%if(loginMember!=null){%>
			var tr=$("<tr></tr>");
			var html="<td style='display:none;text-align:left;' colspan='2'>";
			html+="<input type='hidden' name='boardNo'value='<%=b.getBoardNo()%>'/>";
			html+="<input type='hidden' name='boCommentWriter' value='<%=c.getMemberNo()%>'/>";
			html+="<input type='hidden' name='boardCommentLevel' value='2'/>";
			html+="<input type='hidden' name='boardCommentRef' value='"+$(this).val()+"'/>";
			html+="<textarea name='boardCommentContent' cols='60'rows='1'></textarea>";
			html+="<button class='btn-insert2'>등록</button>";
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
					url:"<%=request.getContextPath()%>/gallery/commentInsert",
					data:{"groupNo":<%=groupNo%>,
						"boardNo":$('#boardNo').val(),
						"boCommentWriter":$('#boCommentWriter').val(),
						"boardCommentLevel":2,
						"boardCommentRef":$(this).val(),
						"boardCommentContent":$('#boardCommentContent').val(),
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