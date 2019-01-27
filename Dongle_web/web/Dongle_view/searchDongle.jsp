<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*,com.dongle.main.model.vo.*" %>

<%
	List<Group> groupList = (List)request.getAttribute("groupList");
	String pageBar=(String)request.getAttribute("pageBar");
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
	
</style>

<meta charset="UTF-8">
	<div id="dongle-container-back">
		<ul>
		<%if(groupList!=null){
		for(Group g:groupList){%>
			<li class="dongle-back">
				<div class='search_dongle_main_img'>
					<img class="group-img" src="<%=request.getContextPath()%>/images/dongle_main_img/<%=g.getGroupMainNewImgPath()%>"'>
				</div>
				<div class='search_dongle_info'>
					<p><%=g.getGroupIntro() %></p>
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