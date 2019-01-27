<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.main.model.vo.*,java.util.List" %>
<%
	List<LocationCategory> locationList=(List)request.getAttribute("locationList");
%>
<html>

<meta charset="UTF-8">
<script>



</script>
	<%if(locationList!=null){ %>
	<%for(LocationCategory lc:locationList){ %>	

	<li class="tab-link-area"><input class="area-code" type="hidden" value="<%=lc.getAreaCode()%>"><%=lc.getLocAreaName() %></li>
	
	<%}
	} %>
</html>