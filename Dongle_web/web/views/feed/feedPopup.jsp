<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.member.model.vo.*,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*" %>
<%
	Feed feed=(Feed)request.getAttribute("feed");
	List<FeedFile> feedFileList=(List)request.getAttribute("feedFileList");
	Member loginMember=(Member)request.getAttribute("loginMember");
	GroupMember groupMember=(GroupMember)request.getAttribute("groupMember");

%>
<html>

<meta charset="UTF-8">
<div id="close">
	<button id="close-btn">
		<img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/window-close-solid.png"/>
	</button>
</div>
<div id="popup-header">
	<img style="padding:10px 10px;" src="<%=request.getContextPath() %>/images/member_img/<%=groupMember.getGroupMemberImageNewPath() %>" class="member-profile">
	<span id="popupFeedWriter"><%=groupMember.getGroupMemberNickname()%></span> 
	<span id="popupFeedDate"><%=feed.getFeedWriteDate() %></span>
</div>
<div id="popup-body">
	<ul>
		<% for(FeedFile ff:feedFileList){
			if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){
			}else{
		%>
		<li>
			<input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">
			<%=ff.getFeedOldFilePath() %>
			<button class="delete-file">
				<img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/backspace-solid.png"/>
			</button>
		</li>
		<%}
		} %> 
	</ul>
	<textarea id="popupFeedContent" cols="75"><%=feed.getFeedContent()%></textarea>
	<ul id="file">
		<% for(FeedFile ff:feedFileList){ 
            if(ff.getFeedNewFilePath()!=null){
            	if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")){
            		%>
            			
                    <li>
                    	<input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">	
                    	<img src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic">
                    	<button class="delete-file">
                    		<img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/backspace-solid.png">
                    	</button>
                    </li>
                    	
                    <%		
                 }else if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){%>
                    			
                    <li>
                    	<input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">
                    	<video controls src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic" type="video/<%=ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1))%>"></video>
                    	<button class="delete-file">
                    		<img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/backspace-solid.png">
                    	</button>
                    </li>
               <%}else{%>
                    		
              	<%}
                    }		
            			}%>
	</ul>
</div>
<div id="popup-footer"></div>
<div id="submit">
	<button type="button" id="submit-btn">수정</button>
</div>

<script>
	var deleteFileList=[];
	var fileNoList={};
	var updateContent;
	var feedUpFd=new FormData();
	var feedNo=<%=feed.getFeedNo()%>;
	$(document).ready(function(){
		$('.delete-file').click(function(){
			var fileNo=$(this).siblings('.feedFileNo').val();
			deleteFileList.push(fileNo);
			$(this).parent().remove();
			return deleteFileList;
		})
	});
	jQuery.ajaxSettings.traditional = true;
	$(document).ready(function(){
		$('#submit-btn').click(function(){
			
			if(deleteFileList!=null){
				updateContent=document.getElementById("popupFeedContent").value;
				console.log(deleteFileList);
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedUpdateEnd",
					type:"post",
					data:{
						"feedNo":feedNo,
						"updateContent":updateContent,
						"fileNoList":deleteFileList	
					},
					success:function(data){
						if(data>0){
							alert('수정완료!');
							$.ajax({
								url:"<%=request.getContextPath()%>/feed/feedListView?groupNo=<%=groupMember.getGroupNo()%>&memberNo=<%=loginMember.getMemberNo()%>",
								type:"get",
								dataType:"html",
								success:function(dataHtml){
									$('#content-div').html(dataHtml);
									setImage();
									
								}
							})
						}else{
							alert("수정 실패!");
						}
					}
				});
			}
		})
	})
	
	$('#close-btn').on('click',function(){//레이어 창 닫기 이벤트
				console.log("눌리나?");
				var button="<button class='delete-btn'><img class='delete-icon' src='<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png'></button>";
				var feedPopup=document.getElementById("feed-popup");
				console.log(feedPopup);
				$('#feed-popup').removeAttr('style');
				
					
			})
	
</script>
</html>