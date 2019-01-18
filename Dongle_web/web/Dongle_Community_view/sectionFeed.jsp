<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*,com.dongle.member.model.vo.*" %>
<%
	List<Feed> feedList=(List)request.getAttribute("feedList");
	Group g=(Group)request.getAttribute("group");
	Member loginMember=(Member)request.getAttribute("loginMember");
	List<GroupMember> memberList=(List)request.getAttribute("memberList");
	GroupMember groupMember=(GroupMember)request.getAttribute("groupMember");
			
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	$(document).ready(function() {
		// 스크롤 발생 이벤트 처리
		$(document).scroll(function() {
			
			var lengthFeed=$('.feed').length; //현재 피드 개수
			var scrollT = $(document).scrollTop(); // 스크롤바의 상단위치
			var scrollH = $(document).height();
			var maxHeight = $('.main').height(); // 스크롤을 갖는 태그의 최하단
			var scrollMax=$(document).height() - $(window).height() - $(window).scrollTop();
			console.log(lengthFeed);
			// 스크롤바가 맨 아래에 위치할 때	
			if (scrollMax<1&&lengthFeed%10==0) {
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedAdd?groupNo=<%=g.getGroupNo()%>&currentFeed="+lengthFeed,
					type:"get",
					success:function(data){
						var feedHtml="";
						var feedNo=0;
						var groupNo=0;
						var memberNo=0;
						var feedContent="";
						var feedWriteDate;
	
						for(var i=0;i<data.length;i++){
							for(var key in data[i]){
								console.log(i);
								switch(key){
									case "feedNo":
										feedNo=data[i][key];
										console.log("fNo:"+feedNo);break;
									case "groupNo":
										groupNo=data[i][key];
										console.log("gNo:"+groupNo);break;
									case "memberNo":
										memberNo=data[i][key];
										console.log("mNo:"+memberNo);break;
									case "feedContent":
										feedContent=data[i][key];
										console.log("fc:"+feedContent);break;
									case "feedWriteDate":
										feedWriteDate=data[i][key];
										console.log("fwd:"+feedWriteDate);break;
								}
								
								
							}
								feedHtml+="<div class='feed'>feed<div class='feed-header'><img src='<%=request.getContextPath()%>/images/feed-images/한효주.png' class='member-profile'><a>"
								feedHtml+=memberNo+"</a><span class='write-date'>"+feedWriteDate+"</span></div>";
								feedHtml+="<div class='feed-body'><textarea type='text' cols='60' class='feed-content' readonly>"+feedContent+"</textarea><button class='file-download'>파일명</button>";
								feedHtml+="<div class='feed-pics'><img src='<%=request.getContextPath()%>/images/feed-images/한효주.png' class='feed-pic'><img src='<%=request.getContextPath()%>/images/feed-images/한효주.png' class='feed-pic'><br>";
								feedHtml+="<img src='<%=request.getContextPath()%>/images/feed-images/한효주.png' class='feed-pic'><img src='<%=request.getContextPath()%>/images/feed-images/한효주.png' class='feed-pic'></div></div>";
								feedHtml+="<div class='feed-footer'><textarea name='' id='' cols='70' rows='1'></textarea><button class='comment'>댓글달기</button><button class='repleview'>댓글보기</button></div>";
								feedHtml+="<div class='reple'><img src='<%=request.getContextPath()%>/images/feed-images/한효주.png' class='member-profile'><a>멤버네임</a><div class='reple-content'>asdfsdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfaasf</div>";
								feedHtml+="</div></div><hr>";
						}
						
						$('.news').append(feedHtml);
						console.log(lengthFeed);
						
					}
					
				});
			}
			//var refreshFeedLength=$('.feed').length;
			if(lengthFeed%10==0){
				$('.newsfeed').append($('#fountainG'));
			}else{
					$('#fountainG').remove();
			}
			
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
	    
	    console.log(typeof(feedData));
	    
	    var feedFd=new FormData();
		feedFd.append('groupNo',groupNo);
		feedFd.append('memberNo',memberNo);
	    
	    
	    if(document.feedFrm.feedContentUp.value){
	    	feedFd.append('content',feedContentUp);
	    }
	    
		if(document.feedFrm.feedPicUp.value){
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
		
		
	})
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
							<input type="file" id='feed-pic-up' name='feedPicUp' class="fileup" multiple="multiple" accept="image/*"/>
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
                		<a><%=f.getMemberNo() %></a>
                		<span class="write-date"><%=f.getFeedWriteDate() %></span>
            		</div>
            	<div class="feed-body">
                	<textarea type="text" cols="60" class="feed-content" readonly><%=f.getFeedContent() %></textarea>
                	<button class="file-download">파일명</button>    
                	<div class="feed-pics">
                    	<img src="<%=request.getContextPath() %>/images/feed-images/한효주.png" class="feed-pic">
                    	<img src="<%=request.getContextPath() %>/images/feed-images/한효주.png" class="feed-pic"><br>
                    	<img src="<%=request.getContextPath() %>/images/feed-images/한효주.png" class="feed-pic">
                    	<img src="<%=request.getContextPath() %>/images/feed-images/한효주.png" class="feed-pic">
                	</div>
            	</div>
            	<div class="feed-footer">
                	<textarea name="" id="" cols="70" rows="1"></textarea>
                	<button class="comment">∥댓글달기</a>
                	<button class="repleview">∥댓글보기</a>
            	</div>
            	<div class="reple">
                	<img src="<%=request.getContextPath() %>/images/feed-images/한효주.png" class="member-profile">
                	<a>멤버네임</a>
                	<div class="reple-content">asljdhfasgfkajsgdfkajsgdvkjasgdvkjsgadkvjsga<br>dkjhgasdfasdfasdfasdfasdfasdfasdfasdfas</div>
            	</div>
            </div>
            <hr>
			<%	}
            }	%>
			
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

</body>
</html>