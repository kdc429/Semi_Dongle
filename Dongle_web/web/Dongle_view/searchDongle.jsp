<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*,com.dongle.main.model.vo.*" %>

<%
	List<Group> groupList = (List)request.getAttribute("groupList");
%>
<html>

<meta charset="UTF-8">
	)
	<%if(groupList!=null){
		for(Group g:groupList){%>
	<div style="vertical-align: middle;">
		<div class='search_dongle_main_img'><img src="<%=request.getContextPath()%>/images/dongle_main_img/<%=g.getGroupMainNewImgPath()%>" style='width: 150px; height: 150px;'></div>
		<div class='search_dongle_info'><p><%=g.getGroupIntro() %></p></div>
	</div>
		<%} %>
		
	<%}else{ %>
	<div style="vertical-align: middle;">
		<h2>검색결과가 없습니다!</h2>
	</div>
	
	<%}%>
</html>