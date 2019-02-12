<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.*,com.dongle.feed.model.vo.*" %>
<%
	GroupMember commentGroupMember=(GroupMember)request.getAttribute("groupMember");
	FeedComment fc=(FeedComment)request.getAttribute("feedComment");
%>
<html>

<meta charset="UTF-8">

		<li class='level1' style="list-style:none;">
			<input type="hidden" class="feedCommentNo" value="<%=fc.getFeCommentNo() %>"/>
			<span class="profile-back">
				<img class="profile-img" src="<%=request.getContextPath() %>/images/member_img/<%=commentGroupMember.getGroupMemberImageNewPath()%>">
			</span>
			<span class="comment-info">
				<span class="comment-writer"><%=commentGroupMember.getGroupMemberNickname() %></span>
				<span class="comment-date"><%=fc.getFeCommentDate() %></span>
				<%if(fc.getMemberNo()!=commentGroupMember.getMemberNo()){ %>
                <button class="report-comment-button">
					<input type="hidden" class="comment-writer-nick" value="<%=commentGroupMember.getGroupMemberNickname() %>"/>
					<input type="hidden" class="comment-no" value="<%=fc.getFeCommentNo() %>"/>
					<input type="hidden" class="comment-writer-no" value="<%=fc.getMemberNo() %>">
					<img class="report-icon" src="<%=request.getContextPath()%>/images/button-images/report-solid.png">
				</button>
				<%}else if(fc.getMemberNo()==commentGroupMember.getMemberNo()){ %>
				<button class="delete-comment-button">
					<input type="hidden" class="comment-no" value="<%=fc.getFeCommentNo() %>"/>
					<img class="delete-icon" src="<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png">
				</button>
				<%} %>				
				<button class="comment-reple">
					<img class="reple-icon" src="<%=request.getContextPath()%>/images/button-images/comments-solid.png">답글
				</button>
			</span>
			<span class="comment-content-back">
				<span class="comment-content"><%=fc.getFeCommentContent() %></span>
                        			
			</span>
			<div class='recomment_content'></div>
		</li>
</html>