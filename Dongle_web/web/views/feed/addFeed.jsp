<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*,com.dongle.member.model.vo.*" %>
<%
List<Feed> feedList=(List)request.getAttribute("feedList");
Group g=(Group)request.getAttribute("group");
Member loginMember=(Member)request.getAttribute("loginMember");
List<GroupMember> memberList=(List)request.getAttribute("memberList");
GroupMember groupMember=(GroupMember)request.getAttribute("groupMember");
List<FeedFile> feedFileList=(List)request.getAttribute("feedFileList");
List<FeedComment> feedCommentList=(List)request.getAttribute("feedCommentList");
List<FeedComment> feedLevel2CommentList=(List)request.getAttribute("level2FeedCommentList");
%>
<html>
         <% if(feedList!=null){ 
               for(Feed f:feedList){%>
                   <!-- 피드 한칸 -->
                   <%if(f.getFeedReportStatus().equals("Y")) {
                   
                   }else{
                   %>
           <div class="feed">
               <div class="feed-header">
      
              <% for(GroupMember gm:memberList){      
                   if(gm.getMemberNo()==f.getMemberNo()){%>
                   <img style="padding:10px 10px;" src="<%=request.getContextPath() %>/images/member_img/<%=gm.getGroupMemberImageNewPath() %>" class="member-profile">
                   <a><%=gm.getGroupMemberNickname() %></a>
                <%      break;
                      }
                   }%>
                   <span class="write-date"><%=f.getFeedWriteDate() %></span>
                   <div class="update-back">
                  <%if(f.getMemberNo()==loginMember.getMemberNo()){ %>
                     <input type="hidden" class="feed-no-update" value="<%=f.getFeedNo() %>"/>
                     <button class="delete-btn">
                        <img class="delete-icon" src="<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png">   
                     </button>
                     <button class="update-btn">
                        <img class="update-icon" src="<%=request.getContextPath()%>/images/button-images/edit-solid.png">
                     </button>
                  <%} %>
                  </div>
                  <% if(feedFileList!=null){%>
                <div class="download-back">
                     <ul class="file-download">
                
                  <%for(FeedFile ff:feedFileList){
                     if(ff.getFeedNo()==f.getFeedNo()){ %>
                        <li class="file-down-list" ><a href="<%=request.getContextPath()%>/feed/fileDownLoad?rName=<%=ff.getFeedNewFilePath()%>"><%=ff.getFeedOldFilePath()%></a></li>
                  <%      }
                     }%>

                     </ul>
                  </div>
                  <% }%>
               </div>
               <div class="feed-body">
                  
                  <textarea type="text" cols="73" class="feed-content" readonly><%=f.getFeedContent() %></textarea>
                  
                  <% for(FeedFile ff:feedFileList){
                      if(ff.getFeedNo()==f.getFeedNo()){%>
                <div class="feed-pics">
                       <button class="prev">❮</button>
                       <ul class="media-carousel">
                <%      break;
                      }
                     }%>

                  <% for(FeedFile ff:feedFileList){ 
                       if(ff.getFeedNo()==f.getFeedNo()&&ff.getFeedNewFilePath()!=null&&feedFileList.size()>0){
                         if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")){
                           %>
                     
                          <li><img src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic"></li>
                       
                          <%}else if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){%>
                             
                          <li><video controls src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic" type="video/<%=ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1))%>"></video></li>
                          <%}else{%>
                          
                          <%}
                       }
                     }
                     %>
                        
                     <% for(FeedFile ff:feedFileList){
                      if(ff.getFeedNo()==f.getFeedNo()&&feedFileList.size()>0){%>   
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
                      <% for(FeedFile ff:feedFileList){ 
                        if(ff.getFeedNo()==f.getFeedNo()&&feedFileList.size()>0){%>
                        <li class="feed-pic-indi"></li>
                     
                       <%   }
                     }   %>
                     
                     <% for(FeedFile ff:feedFileList){
                      if(ff.getFeedNo()==f.getFeedNo()&&feedFileList.size()>0){%>   
                         
                      </ul>
                </div>
                      <%
                         break;
                      }
                     }
                     %>
              </div>
              <hr>
               
               <div class="feed-footer">
                  <div class="comment-back">
                     <ul>
                     <%for(FeedComment fc:feedCommentList){
                        if(fc.getFeedNo()==f.getFeedNo()&&fc.getFeedCommentReportStatus().equals("Y")){%>
                         <li class='level1' style="list-style:none;">
                            <input type="hidden" class="feedCommentNo" value="<%=fc.getFeCommentNo() %>"/>
                            <%for(GroupMember gm:memberList){
                               if(gm.getMemberNo()==fc.getMemberNo()&&fc.getFeCommentRef()==0){                           
                               %>
                             <span class="profile-back">
                                 <img class="profile-img" src="<%=request.getContextPath() %>/images/member_img/<%=gm.getGroupMemberImageNewPath()%>">
                             </span>
                             <span class="comment-info">
                                 <span class="comment-writer"><%=gm.getGroupMemberNickname() %></span>
                                 <span class="comment-date"><%=fc.getFeCommentDate() %></span>
                                 <button class="report-button">신고</button>
                                 <button class="comment-reple">답글</button>
                             </span>
                             <span class="comment-content-back">
                                 <span class="comment-content"><%=fc.getFeCommentContent() %></span>
                                 
                             </span>
                         <%            
                               }
                            }
                               %> 
                         </li>
                         <%for(FeedComment fcl2:feedLevel2CommentList) {
                            if(fcl2.getFeedCommentReportStatus().equals("Y")){
                            }else{
                            for(GroupMember gm:memberList){
                            if(fcl2.getMemberNo()==gm.getMemberNo()&&fc.getFeCommentNo()==fcl2.getFeCommentRef()){
                            
                         %>
                         <li class="level2" style="list-style:none;">
                             <span class="profile-back">
                                 <img class="profile" src="<%=request.getContextPath()%>/images/member_img/<%=gm.getGroupMemberImageNewPath()%>">
                             </span>
                             <span class="comment-info">
                                 <span class="comment-writer"><%=gm.getGroupMemberNickname() %></span>
                                 <span class="comment-date"><%=fcl2.getFeCommentDate() %></span>
                                 <button class="report-button">신고</button>
                                 
                             </span>
                             <span class="comment-content-back">
                                 <span class="comment-content"><%=fcl2.getFeCommentContent() %></span>
                                 
                             </span>
                         </li>
                         <%      }
                            }
                         }%>
                        
                     <%            }
                               }
                            }
                           %> 
                     </ul>
                     <input type="hidden" name="groupNo" class='groupNo' value="<%=g.getGroupNo()%>"/>
                     <input type="hidden" name="feedNo" class="feedNo" value="<%=f.getFeedNo() %>"/>
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
         <%       
                   }
                        }
                     }%>
   
</html>