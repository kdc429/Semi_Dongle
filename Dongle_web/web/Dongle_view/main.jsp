<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.vo.EditPickGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="header.jsp"%>

<%	
	List<Group> list=(List)request.getAttribute("list");
	List<EditPickGroup> editList=(List)request.getAttribute("editList");
	List<Group> rankList=(List)request.getAttribute("rankList");
%>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Dongle_Main.css" />
	

<section>
	<div class="bar">
		<!-- 동글 개설하기 버튼! -->
		<div class="cre-icon-back">
			<button class="img-icon">
				<span class="sub-icon">동글개설하기</span>
				<img class="create-img" src="<%=request.getContextPath()%>/images/button-images/addDongle.png">		
			</button>
			
		</div>
	</div>
	<h2 class="item-logo">MY DONGLE</h2>
	<!-- 가입한 동글 캐러셀 -->
	<div class="carousel-back">
		<!-- 이전 버튼 -->
        <button class="left"><</button>
        <!--캐러셀 아이템   -->
        <div class="carousel-box">
                <ol class="item">
                    <% if(list==null||list.isEmpty()||loginMember.getMemberId().equals("admin")){%>
                    	<!-- 동글에 가입하지 않았을 경우 혹은 관리자 일 경우-->
                    	<li>DONGLE 에 가입하세요!</li>
                    <%}else{ 
                    	for(Group g:list){
                    %>
                    	<!-- 가입한 동글 리스트 -->
                    	<form action="<%=request.getContextPath()%>/communityJoin" name="join" method='post'>
                    	<li class="dongle-icon">
                    		<div class="icon-back">
                    			<button class="join-btn" type="submit"> 
                    			
                    			<!-- 여기서 그룹 넘버 전송 -->
                    				<span class="group-name"><%=g.getGroupName() %></span>
                    				<img class="icon" src="<%=request.getContextPath()%><%=g.getImgPath()%>"/>
                    				<input type="hidden" name="groupNo" value="<%=g.getGroupNo()%>"/>
									<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo() %>"/>
                    			</button>
                    		</div>
                    	</li>
                    	</form>
                    	
                    <%} 
                    }
                    %>
	
                </ol>
			
    	</div>
		<!-- 다음 버튼 -->
    	<button class="right">></button>
    </div>
	<hr>
	<!-- 에디터 픽 캐러셀 -->
	<div class="bar">
		<% if(loginMember.getMemberId().equals("admin")){ %>
			<div class="set-back">
				<button class="img-icon">
					<img class="set-img" src="<%=request.getContextPath() %>/images/button-images/userEdit.png">
				</button>
				<span class="sub-icon">설정</span>
			</div>
		<%} %>
	</div>
	<h3 style="font-family:'YanoljaYacheR'; text-align:center;">당신을 위한 에디터의 추천!</h3>
	<div class="editorPick">
		<h2 class="item-logo">Editor Pick's</h2>
		<div id="carousel_section">
			<ul>
				<%if(editList!=null){
					for(EditPickGroup epg : editList){ %>
				<li>
					<!-- 에디터 픽 선정 동글 리스트 -->
					<form action="<%=request.getContextPath()%>/communityJoin" method="post" name="edit-pick">
						<!-- 여기서 그룹 넘버 전송 -->
						<div class="editor-img-back">
							<div class="editor-img">
								<button class="join-btn" >
								<img class="eImg" src="<%=request.getContextPath() %><%=epg.getEditFilePath()%>">
								<input type="hidden" name="groupNo" value="<%=epg.getGroupNo()%>"/>
								<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo() %>"/>
								</button>
							</div>
						</div>
					</form>
					
					<div class="editor-content">
					<!-- 에디터픽 소개 글 컨텐트 -->
						<p class="eContent"><%=epg.getEditContent()%></p>
					</div>
				</li>
				<%	} 
				}%>
			</ul>
		</div>
		
	</div>
	<hr>
	<!-- 분야별 랭킹 컨텐츠 -->
	<div class="bar"></div>
	<h3 style="font-family:'YanoljaYacheR'; text-align:center";>당신을 위한 가장 인기있는 동글!</h3>
	<h2 class="item-logo">DONGLE'S RANKING</h2>
	<div class="dongle-rank">
		
		<% if(rankList!=null) {
			for(Group g :rankList){%>
		<div class="dongle-ranker">
			
			<div class="ranker-img-back">
				<form action="<%=request.getContextPath()%>/communityJoin?=<%=g.getGroupNo()%>&<%=loginMember.getMemberNo() %>">
				<!-- 여기서 그룹 넘버 전송 -->
					<div class="ranker-img"> 
						<!-- 순위권 동글 리스트 -->
						<button class="join-btn" type="submit">
						<img class="rImg" src="<%=request.getContextPath()%>/images/group_images/back1.jpg"/>
						<input type="hidden" name="groupNo" value="<%=g.getGroupNo()%>"/>
						<input type="hidden" name="groupNo" value="<%=loginMember.getMemberNo()%>"/>
						</button>
					</div>
				</form>
			</div>
			<div class="ranker-intro">
					<!-- 동글 소개 컨텐트 -->			
					<p class="intro-content">
						<%=g.getGroupName() %><br>
						동글 소개:<%=g.getGroupIntro() %><br>
					</p>
			</div>
		</div>
		<%	}
		}%>
		
	</div>
	
</section>
<script src="<%=request.getContextPath()%>/Dongle_js/Dongle_main.js">
</script>

</body>
</html>
