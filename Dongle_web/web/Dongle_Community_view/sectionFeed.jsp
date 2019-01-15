<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*,com.dongle.member.model.vo.*" %>
<%
	List<Feed> feedList=(List)request.getAttribute("feedList");
	Group g=(Group)request.getAttribute("group");
	Member loginMember=(Member)request.getAttribute("loginMember");
	
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
			
			var lengthFeed=$('.feed').length;
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
								feedHtml+="<div class='feed'>feed<div class='feed-header'><span>"+memberNo+"</span><span>"+feedWriteDate+"</span></div>";
								feedHtml+="<div class='feed-body'><p>"+feedContent+"</p></div>";
								feedHtml+="<div class='feed-footer'><a class='comment'>댓글달기</a><a class='repleview'>댓글보기</a></div></div><hr>";
							
						}
						
						$('.newsfeed').append(feedHtml);
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
	
	</script>
	<div class="newsfeed">
		<br><br>
		<div class="news">
			<div class="feednew">
				<div class="feednew-logo">NEWSFEED</div>
				<div>
					<form>
						<label>
							<textarea name="feedup" cols='55' rows="6"></textarea>
							<button class="filecup" >사진업로드</button>
							<button class="filecup" >동영상업로드</button>
							<button class="filecup" >문서업로드</button>
						</label>
					</form>
				
                    <div class="fileup" >
                       	<button>등록</button>
                    </div>
                </div>
			</div>
			<hr>
            <% if(feedList!=null){ 
            	for(Feed f:feedList){
            %>       <!-- 피드 한칸 -->
			<div class="feed" style=" height:auto; left:10%; right:10%;">
			feed
				<div class="feed-header">
					<span><%=f.getMemberNo()%></span>
					<span><%=f.getFeedWriteDate() %></span>
                </div>
                <div class="feed-body">
				<p><%=f.getFeedContent() %></p>
				</div>
				<div class="feed-footer">
                    <a class="comment">∥댓글달기</a>
                    <a class="repleview">∥댓글보기</a>
				</div>
			</div>
			<hr>
			<%	}
            }	%>
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