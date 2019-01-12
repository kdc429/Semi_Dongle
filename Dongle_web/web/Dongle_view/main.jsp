<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.vo.EditPickGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="header.jsp"%>

<%	
	List<Group> list=new GroupService().selectGroup(loginMember.getMemberId());
	List<EditPickGroup> editList=new GroupService().selectEditGr();
%>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Dongle_Main.css" />
	

<section>
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
                    	<form action="<%=request.getContextPath()%>/communityJoin" name="join" method="post">
	                    	<li class="dongle-icon">
	                    		<div class="icon-back">
	                    			<button class="join-btn" type="submit" onclick="document.forms['join'].submit();"> 
	                    			<!-- onclick:form태그 사용 구문 -->
	                    			<!-- 여기서 그룹 넘버 전송 -->
	                    				<img class="icon" src="<%=request.getContextPath()%><%=g.getImgPath()%>"/>
	                    				<input type="hidden" name="gNo" value="<%=g.getGroupNo()%>"/>
										<input type="hidden" name="mNo" value="<%=loginMember.getMemberNo() %>"/>
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
	<div class="editorPick">
		<h2 class="item-logo">Editor Pick's</h2>
		<div id="carousel_section">
			<ul>
				<%for(EditPickGroup epg : editList){ %>
				<li>
					<form action="<%=request.getContextPath()%>/communityJoin" method="get" name=edit-pick">

						<div class="editor-img-back">
							<div class="editor-img" onclick="document.forms['edit-pick'].submit();">
								<button class="join-btn" >
								<img class="eImg" src="<%=request.getContextPath() %><%=epg.getEditFilePath()%>">
								<input type="hidden" name="gNo" value="<%=epg.getGroupNo()%>"/>
								<input type="hidden" name="mNo" value="<%=loginMember.getMemberNo() %>"/>
								</button>
							</div>
						</div>
					</form>
					<div class="editor-content">
						<p class="content"><%=epg.getEditContent()%></p>
					</div>
				</li>
				<%} %>
			</ul>
		</div>
		<div class="bar">BAR</div>
	</div>
	<hr>
	<!-- 분야별 랭킹 컨텐츠 -->
	<h2 class="item-logo">DONGLE'S RANKING</h2>
	<div class="dongle-rank">
	
		<div class="dongle-ranker">
			
			<div class="ranker-img-back">
				<div class="ranker-img">
					<button class="join-btn">
					<img class="rImg" src=""/>
					
					</button>
				</div>
			</div>
			<div class="ranker-intro"></div>
		</div>
		
	</div>
	
</section>
<script src="<%=request.getContextPath()%>/Dongle_js/Dongle_main.js">
</script>

</body>
</html>
