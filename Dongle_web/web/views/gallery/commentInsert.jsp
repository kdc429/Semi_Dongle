<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ page import="com.dongle.gallery.model.vo.GalleryCommentJoin,java.util.*,com.dongle.member.model.vo.Member,com.dongle.gallery.model.vo.GalleryPath" %>
<%
	List<GalleryPath> gplist=(List)request.getAttribute("gplist");
	List<GalleryCommentJoin> gclist=(List)request.getAttribute("gclist");
	int groupNo = (int)request.getAttribute("groupNo");
	Member loginMember = (Member)session.getAttribute("loginMember");
%>
	<link href="<%=request.getContextPath() %>/css/gallery_style.css" rel="stylesheet">
	<!-- 댓글창 시작 -->
	<div class="comment-editor">
		<ul>
			<%if(gclist!=null){ %>
				<%for(GalleryCommentJoin g:gclist){ %>
					<%if(g.getGalCommentLevel()==1){ %>
						<li class='level1' style="list-style:none;">
							<span class='ico_skin thumb_profile'>
								<img class='img_profile' src='<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupMemberImageNewPath() %>'>
							</span>
							<span class='comment_box'>
								<span class='comment-writer'><%=g.getGroupMemberNickname()%></span>
								<span class='comment-date'>
									<%=g.getGalCommentDate() %>
									<a href='*' >신고</a>
								</span>
								<br/>
								<span class='comment_content'>
									<%=g.getGalCommentContent() %>
									<button class='btn-reply' value=''>답글</button>
								</span>
							</span>
						</li>
					<%} 
					else{%>
						<li class="level2" style="list-style:none;">
							<span class='ico_skin thumb_profile'>
								<img class='img_profile' src='<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupMemberImageNewPath() %>'>
							</span>
							<span class='comment_box'>
								<span class='comment-writer'><%=g.getGroupMemberNickname()%></span>
								<span class='comment-date'>
									<%=g.getGalCommentDate() %>
									<a href='*' >신고</a>
								</span>
								<br/>
								<span class='comment_content'>
									<%=g.getGalCommentContent() %>
								</span>
							</span>
						</li>
					<%} %>
				<%} %>
			<%}%> 
		</ul>
		<input type="hidden" name="groupNo" id='groupNo' value="<%=gplist.get(0).getGroupNo() %>"/>
		<input type="hidden" name="galNo" id="galNo" value="<%=gplist.get(0).getGalNo() %>"/>
		<input type="hidden" name="galCommentWriterNo" id='galCommentWriterNo' value="<%=loginMember.getMemberNo() %>"/>
		<input type="hidden" name="galCommentLevel" id='galCommentLevel' value="1"/>
		<input type="hidden" name="galCommentRef" id='galCommentRef' value="0"/>
		<input type="hidden" name="albumCode" id='albumCode' value="<%=gplist.get(0).getAlbumCode()%>"/>
		<input type="hidden" name="galFileNo" id='galFileNo' value="<%=gplist.get(0).getGalFileNo()%>"/>
		<fieldset class='modal_comment'>
			<legend class='screen_out'>댓글쓰기 폼</legend>
			<div class='comment_write'>
				<label for='comment' class='lab_write screen_out'>내용</label>
				<textarea name="galCommentContent" id='galCommentContent' placeholder="소중한 댓글을 입력해주세요" tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>
			</div>
			<div class='comment_btn'>
				<button type="button" id='btn-insert'>입력</button>
			</div>
		</fieldset>
	</div>