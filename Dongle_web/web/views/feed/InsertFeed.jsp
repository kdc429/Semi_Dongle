<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*,com.dongle.member.model.vo.*,com.dongle.feed.model.service.*,com.dongle.group.model.service.*" %>    
<%
	Member loginMember=(Member)request.getAttribute("loginMember");
	GroupMember groupMember=(GroupMember)request.getAttribute("groupMember");
	Group g=(Group)request.getAttribute("group");
	List<FeedFile> fileList=(List)request.getAttribute("fileList");
	Feed feed=(Feed)request.getAttribute("feed");
%>
<meta charset="UTF-8">

		<div class="feed">
            		<div class="feed-header">
                		<img src="<%=request.getContextPath() %>/images/member_img/<%=groupMember.getGroupMemberImageNewPath() %>" class="member-profile">
                		
                					<a><%=groupMember.getGroupMemberNickname() %></a>
                			
                		<span class="write-date"><%=feed.getFeedWriteDate() %></span>
                		<div class="update-back">
                  			<%if(feed.getMemberNo()==loginMember.getMemberNo()){ %>
                     		<input type="hidden" class="feed-no-update" value="<%=feed.getFeedNo() %>"/>
                     		<button class="delete-btn">
                        		<img class="delete-icon" src="<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png">   
                     		</button>
                     		<button class="update-btn">
                        		<img class="update-icon" src="<%=request.getContextPath()%>/images/button-images/edit-solid.png">
                     		</button>
                  			<%} %>
                  		</div>
                  		  <% if(fileList!=null){%>
                		<div class="download-back">
                     		<ul class="file-download">
                
                  <%for(FeedFile ff:fileList){
                     if(ff.getFeedNo()==feed.getFeedNo()){ %>
                        		<li class="file-down-list" ><a href="<%=request.getContextPath()%>/feed/fileDownLoad?rName=<%=ff.getFeedNewFilePath()%>"><%=ff.getFeedOldFilePath()%></a></li>
                  <%      }
                     }%>

                     		</ul>
                  		</div>
                  <% }%>
            		</div>
            	<div class="feed-body">
            		<textarea type="text" cols="60" class="feed-content" readonly><%=feed.getFeedContent() %></textarea>
            		<% for(FeedFile ff:fileList){
    						if(ff.getFeedNo()==feed.getFeedNo()){%>
    						<div class="feed-pics">
    	                		<button class="prev">❮</button>
    	                		<ul class="media-carousel">
    						<%
    							break;
    						}
	            		}
            			%>
            			
            			<% for(FeedFile ff:fileList){ 
            				if(ff.getFeedNo()==feed.getFeedNo()&&ff.getFeedNewFilePath()!=null){
            					if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")){
            					%>
            			
                    		<li>
                    			<input type="hidden" value="<%=ff.getFeedFileNo() %>"/>
                    			<img src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic">
                    		</li>
                    	
                    	<%		
                    			}else if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){%>
                    			
                    			<li>
                    				<input type="hidden" value="<%=ff.getFeedFileNo() %>"/>
                    				<video controls src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic" type="video/<%=ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1))%>"></video>
                    			</li>
                    	<%	}else{%>
                    		
                    	<%}
                    		}
            					
            			}	%>
            				
            			<% for(FeedFile ff:fileList){
    						if(ff.getFeedNo()==feed.getFeedNo()){%>	
                				</ul>
                				<button class="next">❯</button>
            				</div>
            				<div class="indi-wrap-div" style="text-align: center">
    
                			<ul class="indi-wrap">
                		<%
    							break;
    						}
	            		}
            			%>
                			
                		<% for(FeedFile ff:fileList){ 
            				if(ff.getFeedNo()==feed.getFeedNo()){%>
            					<li class="feed-pic-indi"></li>
            			
                    	<%	}
            			}	%>
            			
            			<% for(FeedFile ff:fileList){
    						if(ff.getFeedNo()==feed.getFeedNo()){%>	
                			
                			</ul>
    						</div>
                		<%
    							break;
    						}
	            		}
            			%>
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
			