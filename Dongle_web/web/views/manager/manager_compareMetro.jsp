<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	List<String> areaCodeList = (List)request.getAttribute("areaCodeList");
	List<String> areaNameList = (List)request.getAttribute("areaNameList");
%>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<option disabled selected>==중분류==</option>
<%
	if(areaNameList.get(0) != null)
	{
		for(int i = 0; i < areaCodeList.size(); i++)
		{
		%>
			<option value="<%=areaCodeList.get(i)%>" ><%=areaNameList.get(i)%></option>
		<%
		}
	}
%>