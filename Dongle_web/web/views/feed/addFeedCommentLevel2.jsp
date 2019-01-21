<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.*,com.dongle.feed.model.vo.*" %>
<%
	GroupMember commentGroupMember=(GroupMember)request.getAttribute("groupMember");
	FeedComment fcl2=(FeedComment)request.getAttribute("feedComment");
%>
<html>

<meta charset="UTF-8">

						<li class="level2" style="list-style:none;">
                   			<span class="profile-back">
                       			<img class="profile" src="<%=request.getContextPath()%>/images/member_img/<%=commentGroupMember.getGroupMemberImageNewPath()%>">
                   			</span>
                    		<span class="comment-info">
                       			<span class="comment-writer"><%=commentGroupMember.getGroupMemberNickname() %></span>
                       			<span class="comment-date"><%=fcl2.getFeCommentDate() %></span>
                       			<button class="report-button">신고</button>
                       			
                   			</span>
                   			<span class="comment-content-back">
                       			<span class="comment-content"><%=fcl2.getFeCommentContent() %></span>
                       			
                   			</span>
               			</li>
</html>