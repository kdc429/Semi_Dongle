<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*,com.dongle.member.model.vo.*,com.dongle.feed.model.service.*,com.dongle.group.model.service.*" %>
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

<meta charset="UTF-8">

	<link href="<%=request.getContextPath()%>/css/feed.css" rel="stylesheet"/>
	<script>
	$(document).ready(function() {
		// 스크롤 발생 이벤트 처리
		$(document).on("scroll",document,function() {
			
			var lengthFeed=$('.feed').length; //현재 피드 개수
			var scrollT = $(document).scrollTop(); // 스크롤바의 상단위치
			var scrollH = $(document).height();
			var maxHeight = $('.main').height(); // 스크롤을 갖는 태그의 최하단
			var scrollMax=$(document).height() - $(window).height() - $(window).scrollTop();
			console.log(lengthFeed);
			console.log(maxHeight);
			console.log(scrollMax);
			
			// 스크롤바가 맨 아래에 위치할 때	
			if (scrollMax<1&&lengthFeed%10==0) {
				console.log("이벤트");
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedAdd?groupNo=<%=g.getGroupNo()%>&currentFeed="+lengthFeed,
					type:"get",
					dataType:"html",
					success:function(data){
						
						
						$('.news').append(data);
						console.log(lengthFeed);
						if(lengthFeed%10==0){

							$('.newsfeed').append($('#fountainG'));
						}else{
							console.log("sdj");
							$('#fountainG').remove();
						}
						
					}
					
				});
			}
			//var refreshFeedLength=$('.feed').length;
				
			
			
			
		});
	});
	
	$('#pic-up-btn').click(function(){
		$('#feed-pic-up').click()
		
	});
	$('#video-up-btn').click(function(){
		$('#feed-video-up').click()
		
	});
	$('#file-up-btn').click(function(){
		$('#feed-file-up').click()
		
	});
	
	$('#feedup').click(function(){
		
		var fd=new FormData();
		var imgBtn=$('#feed-pic-up'); //이미지파일 추가 버튼
		var videoBtn=$('#feed-video-up'); //영상 파일 추가 버튼
		var fileBtn=$('#feed-file-up'); //일반 텍스트 파일 추가 버튼
		var feedContentUp=document.feedFrm.feedContentUp.value; //입력받은 피드 컨텐트 내용
		var groupNo=<%=g.getGroupNo()%>;
		var memberNo=<%=loginMember.getMemberNo()%>;
		var resultFeedNo=0;
	   
	    
	    var feedFd=new FormData();
		feedFd.append('groupNo',groupNo);
		feedFd.append('memberNo',memberNo);
	    
	    
	    if(document.feedFrm.feedContentUp.value){
	    	feedFd.append('content',feedContentUp);
	    }
	    
		if(document.feedFrm.feedPicUp.value){
			console.log('zxc');
			for(var i=0;i<document.feedFrm.feedPicUp.files.length;i++){
				feedFd.append('image'+i,feedFrm.feedPicUp.files[i]);
			}
		}
		if(document.feedFrm.feedVideoUp.value){
			for(var i=0;i<feedFrm.feedVideoUp.files.length;i++){
				feedFd.append('video'+i,feedFrm.feedPicUp.files[i]);
			}
		}
		if(document.feedFrm.feedFileUp.value){
			for(var i=0;i<feedFrm.feedFileUp.files.length;i++){
				feedFd.append('files'+i,feedFrm.feedPicUp.files[i]);
			}
		}
		/* strFeedFd= JSON.stringify(feedFd);
		jQuery.ajaxSettings.traditional = true;
 		*/		
 		$.ajax({
			url:"<%=request.getContextPath()%>/feed/feedContentUpload",
			data:feedFd,
			type:"post",
			processData:false,
			contentType:false,
			success:function(data){ //컨텐트 업로드 성공시 파일 업로드로 넘어감
				if(data>0){
					alert('성공');
				}else{
					alert('실패');
				}
				
			}
			
		});
		
		
	});
	$(document).ready(function(){
		
		$(document).on('click','.btn-insert',function(event){
			
			
			var groupNo=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.groupNo').val();//그룹넘버
			var feedNo=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedNo').val();//피드넘버
			var feedCommentWriterNo=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedCommentWriterNo').val();
			//피드 작성자 넘버
			var feedCommentLevel=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedCommentLevel').val();
			//피드 댓글 레벨
			var feedCommentRef=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedCommentRef').val();
			//피드 댓글 참조 변수
			var feedCommentContent=$(this).parent('.comment_btn').siblings('.comment_write').children('.feedCommentContent').val();
			//피드 댓글 내용
			var commentLevel1=$(this).parent().parent().siblings('ul');
			//댓글 레벨 1 태그
			var commentLevel2=$(this).parent().parent().parent().parent();
			//댓글 레벨 2 태그
			var commentRepleTag=$(this).parent().parent().parent('.recomment_content');
			console.log(commentLevel2);
			if(feedCommentContent==""){
				alert("댓글 내용을 입력해주세요!");
				return;
			}
			if(feedCommentLevel==1){
				//일반 댓글 입력시
				$.ajax({
					
					url:"<%=request.getContextPath()%>/feed/feedInsertComment",
					type:"post",
					data:{
							"groupNo":groupNo,
							"feedNo":feedNo,
							"feedCommentWriterNo":feedCommentWriterNo,
							"feedCommentLevel":feedCommentLevel,
							"feedCommentContent":feedCommentContent,
							"feedCommentRef":feedCommentRef
						},
					dataType:"html",
					contentType: "application/x-www-form-urlencoded",
					success:function(data){
						
						commentLevel1.append(data);
						
							
					}
				})
			}else if(feedCommentLevel==2){
				//댓글 밑 댓글 입력시
				console.log(feedCommentRef);
				$.ajax({
					
					url:"<%=request.getContextPath()%>/feed/feedInsertComment",
					type:"post",
					data:{
							"groupNo":groupNo,
							"feedNo":feedNo,
							"feedCommentWriterNo":feedCommentWriterNo,
							"feedCommentLevel":feedCommentLevel,
							"feedCommentContent":feedCommentContent,
							"feedCommentRef":feedCommentRef
						},
					dataType:"html",
					contentType: "application/x-www-form-urlencoded",
					success:function(data){
						
						commentLevel2.after(data);
						commentRepleTag.remove();
					}
				})
			}
			
		});
	})
	
	
	$(function(){
		var eventflag;
		$(document).off().on('click','.comment-reple',function(e){
			var feedNoReple=$(this).parent().parent().children('.feedCommentNo').val();
			var feedNo=$(this).parent().parent().parent().siblings('.feedNo').val();
			console.log(feedNo);
			<%if(loginMember!=null){%>
				eventflag=true;
				var div=$("<div class='recomment_content'></div>");
				var html="";
				html+="<input type='hidden' class='groupNo' value='<%=g.getGroupNo()%>'/>"
				html+="<input type='hidden' class='feedNo' value='"+feedNo+"'/>";
				html+="<input type='hidden' class='feedCommentWriterNo' value='<%=loginMember.getMemberNo()%>'/>";
				html+="<input type='hidden' class='feedCommentLevel' value='2'/>";
				html+="<input type='hidden' class='feedCommentRef' value='"+feedNoReple+"'/>";
				html+="<fieldset class='modal_comment'>";
				html+="<div class='comment_write'>";
				html+="<textarea name='feedCommentContent' class='feedCommentContent' placeholder='소중한 댓글을 입력해주세요' tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>";
				html+="</div>";
				html+="<div class='comment_btn'>";
				html+="<button type='button' class='btn-insert'> Send</button>";
				html+="</div>"
				html+="</fieldset>"
				div.html(html);
				div.insertAfter($(this).parent().siblings('.comment-content-back')).slideDown(800);
				/* 연결된 이벤트 삭제 */
				$(this).off();
				e.stopPropagation();
				console.log($(this));
				
				
				div.find('.btn-insert').click(function(e){
					if(<%=loginMember==null%>)
					{
					 	fn_loginAlert();
						e.preventDefault();
						return;
					}
				});	

			<%}%>
			})
		}); 
		function fn_loginAlert()
		{
			alert('로그인 후 이용할 수 있습니다.');
		}

		
		/* 댓글 등록 */
		
	
	
	
	
	</script>
	<div class="newsfeed">
		<br><br>
		<div class="news">
			<div class="feednew">
				<div class="feednew-logo">NEWSFEED</div>
					<div>
						<form name="feedFrm" id="feedFrm" method="post" enctype="multipart/form-data">
							<textarea name="feedContentUp" id="feed-content-up" cols='70' rows="6"></textarea>
							<button type="button" class="up-btn" id="pic-up-btn">사진업</button>
							<button type="button" class="up-btn" id="video-up-btn">영상업</button>
							<button type="button" class="up-btn" id="file-up-btn">텍스트업</button>
							<input type="file" id='feed-pic-up' name='feedPicUp' class="fileup" multiple="multiple" accept="image/*" style='display: none;'/>
							<input type="file" id='feed-video-up' name='feedVideoUp' class="fileup" multiple="multiple" accept="video/*" style='display: none;'/>
							<input type="file" id='feed-file-up' name='feedFileUp' class="fileup" multiple="multiple" style='display: none;'/>
                    		
                    		<div class="fileup" >
                       			<button type="button" id="feedup">등록</button>
                    		</div>
						</form>
				
                </div>
			</div>
			<hr>
            <% if(feedList!=null){ 
            	for(Feed f:feedList){
            %>       <!-- 피드 한칸 -->
        		<div class="feed">
            	feed
            		<div class="feed-header">
                		<img src="<%=request.getContextPath() %>/images/feed-images/한효주.png" class="member-profile">
                		<% for(GroupMember gm:memberList){
                				
                				if(gm.getMemberNo()==f.getMemberNo()){%>
                					<a><%=gm.getGroupMemberNickname() %></a>
                			<%		break;
                			 	}
                			}	%>
                			
                		
                		
                		<span class="write-date"><%=f.getFeedWriteDate() %></span>
            		</div>
            	<div class="feed-body">
            		<textarea type="text" cols="60" class="feed-content" readonly><%=f.getFeedContent() %></textarea>
            		<button class="file-download">파일명</button>
            		<% for(FeedFile ff:feedFileList){
    						if(ff.getFeedNo()==f.getFeedNo()){%>
    						<div class="feed-pics">
    	                		<button class="prev">❮</button>
    	                		<ul class="media-carousel">
    						<%
    							break;
    						}
	            		}
            			%>
            			
            			<% for(FeedFile ff:feedFileList){ 
            				if(ff.getFeedNo()==f.getFeedNo()){%>
            			
            			
                    		<li><img src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic"></li>
                    	
                    	<%	}
            			}	%>
            			
            			<% for(FeedFile ff:feedFileList){
    						if(ff.getFeedNo()==f.getFeedNo()){%>	
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
            				if(ff.getFeedNo()==f.getFeedNo()){%>
            					<li class="feed-pic-indi"></li>
            			
                    	<%	}
            			}	%>
            			
            			<% for(FeedFile ff:feedFileList){
    						if(ff.getFeedNo()==f.getFeedNo()){%>	
                			
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

            			
            			<%for(FeedComment fc:feedCommentList){
            				if(fc.getFeedNo()==f.getFeedNo()){ %>
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
                			<%	}
                				}
                				}%>
            				
            			<%			
                					
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
        			
        			<% for(FeedComment fc:feedCommentList){
            				if(fc.getFeedNo()==f.getFeedNo()){
            			
            			%>
            				
            				
            			<% 		
            					break;
            				}
            			}%>
        			</div>
            	</div>
            </div>
            <hr>
			<% 		
            					
            				}
            			}%>
			
		</div>
			<hr>
            <div id="fountainG">
				<div id="fountainG_1" class="fountainG"></div>
				<div id="fountainG_2" class="fountainG"></div>
				<div id="fountainG_3" class="fountainG"></div>
				<div id="fountainG_4" class="fountainG"></div>
				<div id="fountainG_5" class="fountainG"></div>
				<div id="fountainG_6" class="fountainG"></div>
				<div id="fountainG_7" class="fountainG"></div>
				<div id="fountainG_8" class="fountainG"></div>
			</div>
		</div>
    <script src="<%=request.getContextPath()%>/Dongle_js/feed.js"></script>

</html>