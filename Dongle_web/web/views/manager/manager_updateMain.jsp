<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int groupNo = (int)request.getAttribute("groupNo");
%>

<form>
	<p>변경할 파일</p>
	<input type="file" name="upfile"/>
</form>