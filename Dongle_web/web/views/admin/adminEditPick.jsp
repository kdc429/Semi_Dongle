<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@ page import='com.dongle.group.model.vo.EditPickGroup,java.util.*' %>
<%
	List<EditPickGroup> editList = (List)request.getAttribute("editList");
%>

<div>
<%for(int i=0;i<editList.size();i++){ %>
	<input type='text' size='50' value='<%=editList.get(i).getEditContent() %>' readonly><br>
<%} %>
</div>
<div>

</div>