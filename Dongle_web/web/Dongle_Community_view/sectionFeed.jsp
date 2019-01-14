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
		$('.document').scroll(function() {
			console.log('df');
			var scrollT = $(this).scrollTop(); // 스크롤바의 상단위치
			var scrollH = $(this).height(); // 스크롤바를 갖는 div의 높이
			var maxHeight = $(document).height(); // 스크롤을 갖는 태그의 최하단
			if (maxHeight==scrollT+100) { // 스크롤바가 맨 아래에 위치할 때
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedAdd?groupNo=<%=g.getGroupNo()%>",
					type:"get",
					dataType:"json",
					success:function(data){
						
					}
				});
			}
		});
	});
	
	</script>
	<div class="newsfeed">
		<br><br>
		<div class="news" style="height:100%; left:10%; right:10%;">
			<div class="feednew" style=" height:220px; left:10%; right:10%;">
				<div style="height:40px; background-color:rgba(58, 58, 58, 0.2); ">NEWSFEED</div>
				<form>
					<label>
						<textarea name="feedup" cols='53' rows="6" style='resize: none'></textarea>
						<button>등록</button>
					</label>
				</form>
				<div>
                    <div class="fileup" style="height: 40px;background-color:rgba(58, 58, 58, 0.2);">
                       	<a class="picup" style="bottom:0;">사진업로드</a>
                    </div>
                </div>
			</div>
			<hr>
            <% if(feedList!=null){ 
            	for(Feed f:feedList){
            %>       <!-- 피드 한칸 -->
			<div class="feed" style=" height:auto; left:10%; right:10%;">
			feed
				<div class="storyH" style="height: 40px;">
					<span><%=f.getMemberNo()%></span>
					<span><%=f.getFeedWriteDate() %></span>
                </div>
                <div class="storyB" style="height: 200px;">
				<p><%=f.getFeedContent() %></p>
				</div>
				<div class="storyF" style="height: 40px;">
                    <a class="comment">∥댓글달기</a>
                    <a class="repleview">∥댓글보기</a>
				</div>
			</div>
			<hr>
			<%	}
            }	%>
			<button id="more">더 보기</button>
		</div>
    <script src="<%=request.getContextPath()%>/Dongle_js/feed.js"></script>

</body>
</html>