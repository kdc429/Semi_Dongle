<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="back">

	<%@ include file="header.jsp" %>
	<%@ include file="asideLeft.jsp" %>
	<%@ include file="sectionMain.jsp" %>
	<div>
		<%=group.getGroupNo() %>
		<%=group.getGroupName() %>
	</div>
	<%@ include file="nav.jsp" %>
	
	

</div>

</body>
</html>