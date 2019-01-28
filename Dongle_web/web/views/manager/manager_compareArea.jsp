<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	List<String> townCodeList = (List)request.getAttribute("townCodeList");
	List<String> townNameList = (List)request.getAttribute("townNameList");
%>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<option disabled selected>==소분류==</option>
<%
	if(townNameList.get(0) != null)
	{
		for(int i = 0; i < townCodeList.size(); i++)
		{
		%>
			<option value="<%=townCodeList.get(i)%>"><%=townNameList.get(i)%></option>
		<%
		}
	}
%>