<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*,com.dongle.main.model.vo.*" %>

<%
	List<Group> groupList = (List)request.getAttribute("groupList");
	String pageBar=(String)request.getAttribute("pageBar");
	List<MultiLocationName> locationList=(List)request.getAttribute("locationList");
	List<MultiTopicName> topicList=(List)request.getAttribute("topicList");
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
					<span><strong>동글 명:</strong> <%=g.getGroupName() %></span><br>
					<%if(memberCountList.size()>0){
						for(GroupMemberCount gmc:memberCountList){
							if(gmc.getGroupNo()==g.getGroupNo()){
						%>
					<span><Strong>동글 회원 수:</Strong>&nbsp;&nbsp;<%=gmc.getMemberCount() %>&nbsp;&nbsp;명</span><br>
					<%}}} %>
					<span><strong>동글 회원 연령대:</strong><%=g.getMinAge() %>대 ~ <%=g.getMaxAge() %>대</span><br>
					<span><strong>동글 활동 시간:</strong><%=g.getGroupDateCtg() %></span><br>
					
					<span><strong>동글 창설 날짜:</strong> <%=g.getGroupEnrollDate() %></span><br><br>
					
					<%if(locationList.size()>0){%>
					<ul><strong>동글 활동 지역-</strong>
						<%for(MultiLocationName mln:locationList){
							if(mln.getGroupNo()==g.getGroupNo()){
						%>
						<li><%=mln.getLocCtgName() %></li>
					<%}} %>
					</ul>
					<%} %>
					<br>
					<%if(topicList.size()>0){%>
					<ul><strong>동글 활동 분야-</strong>
						<%for(MultiTopicName mtn:topicList){
							if(mtn.getGroupNo()==g.getGroupNo()){
						%>
						<li><%=mtn.getTopicCtgName() %></li>
					<%}} %>
					</ul>
					<%} %>
				</div>
			</li>
		<%} %>
		</ul>
		<%}else if(groupList.size()==0){ %>
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