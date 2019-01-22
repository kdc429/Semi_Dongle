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
                        			<button class="report-button">신고</button>
                        			<button class="comment-reple">답글</button>
                    			</span>
                    			<span class="comment-content-back">
                        			<span class="comment-content"><%=fc.getFeCommentContent() %></span>
                        			
                    			</span>
                		</li>
</html>