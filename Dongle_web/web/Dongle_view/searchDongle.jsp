<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*,com.dongle.main.model.vo.*" %>

<%
	List<Group> groupList = (List)request.getAttribute("groupList");
	String pageBar=(String)request.getAttribute("pageBar");
	List<MultiLocationName> locationList=(List)request.getAttribute("locationList");
	List<MultiTopicName> topicList=(List)request.getAttribute("topicList2");
	List<GroupMemberCount> memberCountList=(List)request.getAttribute("memberCountList");
%>
<html>
<style>
	.dongle-back{
		vertical-align: middle;
		height:auto;
	}
	#dongle-container-back{
		height:auto;
		max-height:800px;
	}
	.page-bar{
		cursor: pointer;
	}
	
</style>

<meta charset="UTF-8">
	<div id="dongle-container-back">
		<ul>
		<%if(groupList!=null){
		for(Group g:groupList){%>
			<li class="dongle-back">
				<div class='search_dongle_main_img'>
					<input type="hidden" value="<%=g.getGroupNo() %>">
					<img class="group-img" src="<%=request.getContextPath()%>/images/dongle_main_img/<%=g.getGroupMainNewImgPath()%>"'>
				</div>
				<div class='search_dongle_info'>
					<p>동글 명: <%=g.getGroupName() %></p>
					<%for(GroupMemberCount gmc:memberCountList){
						if(gmc.getGroupNo()==g.getGroupNo()){	
						%>
					<p>동글 회원수: <%=gmc.getMemberCount() %></p>
					<%}} %>
					<p>동글 창설 날짜: <%=g.getGroupEnrollDate() %></p>
					<ul>동글 활동 지역:
						<%for(MultiLocationName mln:locationList){ 
							if(mln.getGroupNo()==g.getGroupNo()&&mln!=null){
						%>
						<li><%=mln.getLocCtgName() %></li>
						<%}} %>
					</ul>
					<br>
					<ul>동글 활동 주제:
						<%for(MultiTopicName mtn:topicList){ 
							if(mtn.getGroupNo()==g.getGroupNo()&&mtn!=null){
						%>
						<li><%=mtn.getTopicCtgName() %></li>
						<%}} %>
					</ul>
				</div>
			</li>
		<%} %>
		</ul>
		<%}else if(groupList==null){ %>
		<div style="vertical-align: middle;">
			<h2>검색결과가 없습니다!</h2>
		</div>
	
		<%}%>
	</div>
	
	<div id='page-bar-back'>
		<ul id='page-bar'>
			<%if(pageBar!=null){ %>
			<%=pageBar %>
			<%} %>
		</ul>
	</div>
</html>