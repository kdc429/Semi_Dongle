<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="
java.util.*" %>

<%
	List<String> areaCodeList = (List)request.getAttribute("areaCodeList");
	List<String> areaNameList = (List)request.getAttribute("areaNameList");
%>
<meta charset="UTF-8">

<%
	for(int i = 0; i < areaCodeList.size(); i++)
	{
	%>
		<%-- <option value="<%=(String)areaCodeList.indexOf(i)%>">"</option> --%>
	<%	
	}
%>