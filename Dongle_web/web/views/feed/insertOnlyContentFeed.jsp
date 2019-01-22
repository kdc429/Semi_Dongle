<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*,com.dongle.member.model.vo.*,com.dongle.feed.model.service.*,com.dongle.group.model.service.*" %>
<%

Member loginMember=(Member)request.getAttribute("loginMember");
GroupMember groupMember=(GroupMember)request.getAttribute("groupMember");
Group g=(Group)request.getAttribute("group");
Feed feed=(Feed)request.getAttribute("feed");
%>


<meta charset="UTF-8">

	<div class="feed">
            		<div class="feed-header">
                		<img src="<%=request.getContextPath() %>/images/feed-images/한효주.png" class="member-profile">
                		
                					<a><%=groupMember.getGroupMemberNickname() %></a>
                			
                		<span class="write-date"><%=feed.getFeedWriteDate() %></span>
            		</div>
            	<div class="feed-body">
            		<textarea type="text" cols="60" class="feed-content" readonly><%=feed.getFeedContent() %></textarea>
            		
        		</div><hr>
            	
            	<div class="feed-footer">
            		<div class="comment-back">
            			<ul>

            			</ul>
            			<input type="hidden" name="groupNo" class='groupNo' value="<%=g.getGroupNo()%>"/>
            			<input type="hidden" name="feedNo" class="feedNo" value="<%=feed.getFeedNo() %>"/>
            			<input type="hidden" name="feedCommentWriterNo" class='feedCommentWriterNo' value="<%=loginMember.getMemberNo() %>"/>
            			<input type="hidden" name="feedCommentLevel" class='feedCommentLevel' value="1"/>
            			<input type="hidden" name="feedCommentRef" class='feedCommentRef' value="0"/>
            			<fieldset class='modal_comment'>
                			<legend class='screen_out'>댓글쓰기 폼</legend>
                			<div class='comment_write'>
                    			<label for='comment' class='lab_write screen_out'>내용</label>
                    			<textarea name="feedCommentContent" class='feedCommentContent' placeholder="소중한 댓글을 입력해주세요" tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>
                			</div>
                			<div class='comment_btn'>
                    			<button type="button" class='btn-insert'>입력</button>
                			</div>
            			</fieldset>
        			
        			</div>
            	</div>
            </div>
            <hr>
