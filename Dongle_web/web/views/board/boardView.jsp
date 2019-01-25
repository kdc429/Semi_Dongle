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
	/* int boCommentNo=(int)request.getAttribute("boCommentNo"); */
%>
    <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
div.view-btn
{
	text-align:center;
}
/* 댓글창 스타일 */
div.board-comment-editor fieldset.modal_comment{
padding:8px 10px 10px;
border-bottom:1px solid #efefef;
font-family:'a흑진주L';
border-top:1px solid #e8e8e8;
background-color:rgb(240,240,240);
position:relative;
margin-top:2px;
}
.screen_out{overfloa:hidden;width:0;height:0;line-height:0;text-indent:-9999px;}
.lab_write{top:8px;left:14px;}
/* 댓글테이블!! */
div.board-comment-editor{border-top: 1px solid rgb(240,240,240);margin-top:5%;}
div.board-comment-ediotr ul{list-style:none;}
div.board-comment-ediotr ul li{list-style:none; margin-bottom:20px;}
.ico_skin{display:block;overflow:hidden;font-size:0;line-height: 0;text-indent:-9999px;}
.thumb_profile{
   width: 33px;
   height: 33px;
   margin-right: 11px;
   margin-top: 2px;
   background-position: -120px -20px;
   float:left;
   border-radius:48px;
}
img.img_profile{display:block;width:100%;height:100%;border-radius:48px;}

.comment_box{margin-top:4px;overflow:hidden;display:block;}
div.comment_box ul{}
li{padding:0;}
.comment_writer{float:left;overflow:hidden;color:rgb(250,250,250);text-overflow: ellipsis;white-space: nowrap;font-size:14px;margin-right:5px;max-width:120px;}
.comment_date{float:left;font-size:12px;color:#a7a7a7;margin-top:3px;}
.comment_content{display:block;font-size:13px;color:#5c5c5c;clear:both;line-height: 19px;padding-top:2px;}
div.tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; box-sizing: border-box;} 
li button.btn-reply{display:none; background-color:white;float:right;border:none;height:10px;}
li button.btn-delete{display:none;}
/* li:hover {background:lightgray;} */
li:hover button.btn-reply{display:inline;}
li:hover button.btn-delete{display:inline;}
li.level2{padding-left:50px;}
div.board-comment-editor fieldset.modal_comment div.comment-btn button#btn-insert{
float:right;
width:65px;height:28px;
font-size:14px;
line-height:15px;
border-radius: 20px;
border:none;
background-color:white;
}   
    
    
	
	
</style>
	<section id="board-container">
		<form name="deleteFrm" method="post" enctype="multipart/form-data">
			<table class="table">
				<thead>
					<br><br>
					<tr>
						<th colspan="3" id="title" style="border-top:1px solid #dddddd; ">공지사항</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th style="width: 20%; background-color:rgba(245,245,245); color:black">글 제목</th>
						<td colspan="2" style="text-align:left"><%=b.getBoardTitle()%></td>
					</tr>
					<tr>
						<th style="background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">작성자</th>
						<td colspan="2" style="text-align:left"><%=b.getBoardWriter() %></td>
					</tr>					
					<tr>
						<th style="background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">작성일자</th>
						<td colspan="2" style="text-align:left"><%=b.getBoardWriteDate()%></td>
					</tr>					
					<tr>
						<th style="background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">조회수</th>
						<td colspan="2" style="text-align:left"><%=b.getBoardViewCount()%></td>
					</tr>
					<tr>
						<th style="background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">첨부파일</th>
						<td style="text-align:left">
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
						<th style="border-bottom:1px solid #dddddd; background-color:rgba(245,245,245); color:black">내용</th>
						<td colspan="2" style="text-align:left"><%=b.getBoardContent()%></td>
					</tr>
				</tbody> 
			</table>
		</form>
		<div class="view-btn">
			<button type="button" class="btn btn-default" id='view-list-btn' style="text-align:center; font-family: '나눔스퀘어라운드 Regular';">목록으로</button>
			<%if(loginMember.getMemberId().equals(b.getBoardWriter())||loginMember.getMemberId().equals("admin")) {%>
			<button type="button" class="btn btn-default" id='view-update-btn' style="font-family: '나눔스퀘어라운드 Regular';">수정하기</button>
			<input type="hidden" value="<%=groupNo%>" name="groupNo" name="groupNo"/>
			<button type="button" class="btn btn-default" id="view-delete-btn" style="font-family: '나눔스퀘어라운드 Regular';">삭제하기</button>
			<%} %>
		</div>
		<br><br>
		
		
		<div class="board-comment-editor">
			<input type="hidden" name="boardNo" id="boardNo" 
			value="<%=b.getBoardNo() %>"/>
			<input type="hidden" name="boardCommentWriter" id="boardCommentWriter"
			value="<%=loginMember.getMemberId() %>"/>
			<input type="hidden" name="boardCommentLevel" id="boardCommentLevel"
			value="1"/>
			<%-- <input type="hidden" name="boCommentNo" id="boCommentNo"
			value="<%=b.getBoCommentNo()%>"/> --%>
			<input type="hidden" name="boardCommentRef" id="boardCommentRef"
			value="0"/>
			<textarea cols='70' rows='3' style='resize:none;' name="boardCommentContent" id="boardCommentContent" placeholder="댓글을 입력하세요."></textarea>
			<button type="button" class="btn btn-default" id="comment-insert-btn" style="float:right; width:60px; height:65px;">등록</button>
		</div>
		<!-- 댓글목록 테이블 -->
		<div class="board-comment-editor">
			<ul style='background-color:rgb(242,242,242)'>
			<%if(bclist!=null) {
				for(BoardComment c : bclist){
					if(c.getBoCommentLevel()==1){
			%>
						<li class='level1' style="list-style:none;">
							<span class='ico_skin thumb_profile'>
								<img class='img_profile' src='<%=request.getContextPath()%>/images/member_img/<%=c.getGroupMemberImageNewPath() %>'>
							</span>
							<span class='comment_box'>
								<span class='board-comment-writer'><%=c.getGroupMemberNickname()%></span>
								<span class='board-comment-date'>
									<%=c.getBoCommentDate()%>
									<a href='*' >신고</a>
								</span>
								<br/>
								<span class='board_comment_content'>
									<%=c.getBoCommentContent() %>
									<button style='border:none;background-color:none;' class='comment-reply-btn' value='<%=c.getBoCommentNo()%>'>답글</button>
									<%if(loginMember.getMemberId()!=null&&(loginMember.getMemberId().equals(c.getGroupMemberNickname())||loginMember.getMemberId().equals("admin"))){%>
										<button style='border:none;background-color:none;float:right;' class="comment-delete-btn" value="<%=c.getBoCommentNo() %>">삭제</button>
									<%} %>
								</span>
							</span>
						</li>
					<%} else {%>
					<li class="level2" style="list-style:none;">
						<span class='ico_skin thumb_profile'>
							<img class='img_profile' src='<%=request.getContextPath()%>/images/member_img/<%=c.getGroupMemberImageNewPath() %>'>
						</span>
						<span class='comment_box'>
							<span class="board-comment-writer"><%=c.getGroupMemberNickname()%></span>
							<span class="board-comment-date">
								<%=c.getBoCommentDate()%>
								<a href='*' >신고</a>
								<%if(loginMember.getMemberId()!=null&&(loginMember.getMemberId().equals(c.getGroupMemberNickname())||loginMember.getMemberId().equals("admin"))){%>
									<button style='border:none;background-color:none;float:right;' class="comment-delete-btn" value="<%=c.getBoCommentNo() %>">삭제</button>
								<%} %>
							</span>
							<br/>
							<span class='board_comment_content'>
								<%=c.getBoCommentContent() %>
							</span>
						</span>
					</li>
					<%}	
				} 
			}%>
			</ul>
		</div>
</section>
		
<script>
$(function(){
	$('#comment-insert-btn').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/board/boardCommentInsert?groupNo=<%=groupNo%>",
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
			html+="<textarea style='resize:none;' name='boardCommentContent' id='boardCommentContent2' cols='63' rows='1'></textarea>";
			html+="<button value='"+$(this).val()+"'class='btn btn-default' id='comment-insert-btn' style='float:right; width:60px; height:26px; margin-left:17px;'>등록</button>";
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
					url:"<%=request.getContextPath()%>/board/boardCommentInsert",
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

	function fn_loginAlert()
	{
		alert("로그인 후 이용할 수 있습니다.");
		$('#userId').focus();
	}
	//댓글 삭제
	$(function(){
		$('.comment-delete-btn').click(function(){
			if(!confirm('정말로 삭제하시겠습니까?')){return;}
			else{
				$.ajax({
					url:"<%=request.getContextPath()%>/board/boardCommentDelete?groupNo=<%=groupNo%>",
					data:{"bb":$(this).val()},
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
			}
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
	
</script>		