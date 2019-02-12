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
      						<%if(fcl2.getMemberNo()!=commentGroupMember.getMemberNo()){ %>
                                 <button class="report-comment-button">
                                    <input type="hidden" class="comment-writer-nick" value="<%=commentGroupMember.getGroupMemberNickname() %>"/>
                                    <input type="hidden" class="comment-no" value="<%=fcl2.getFeCommentNo() %>"/>
                                    <input type="hidden" class="comment-writer-no" value="<%=fcl2.getMemberNo() %>">
                                    <img class="report-icon" src="<%=request.getContextPath()%>/images/button-images/report-solid.png">
                                 </button>
                            <%}else if(fcl2.getMemberNo()==commentGroupMember.getMemberNo()){ %>
                                 <button class="delete-comment-button">
                                    <input type="hidden" class="comment-no" value="<%=fcl2.getFeCommentNo() %>"/>
                                    <img class="delete-icon" src="<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png">
                                 </button>
                                 <%} %>
                   			</span>
                   			<span class="comment-content-back">
                       			<span class="comment-content"><%=fcl2.getFeCommentContent() %></span>
                       			
                   			</span>
               			</li>
</html>