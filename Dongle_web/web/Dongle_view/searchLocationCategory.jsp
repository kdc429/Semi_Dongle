<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.main.model.vo.*,java.util.List" %>
<%
	List<LocationCategory> locationList=(List)request.getAttribute("locationList");
%>
<html>

<meta charset="UTF-8">
	<%if(locationList!=null) {%>
	<label><input type="checkbox" class="location-city" value="<%=locationList.get(0).getMetroCode()%>"/>전체</label>
	<%for(LocationCategory lc:locationList){ %>
	<label><input type="checkbox" class="location-city" value="<%=lc.getLocCtgCode()%>"/><%=lc.getLocAreaName() %></label>
	<%}
	} %>

</html>