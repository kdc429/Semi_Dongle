<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="header.jsp"%>

<%	
	List<Group> list=(List)request.getAttribute("list");
	List<Member> memberList = (List)request.getAttribute("memberList");
	
	String flag = "false";
	String rowValue = "";
	if(request.getParameter("flag") != null && request.getParameter("rowValue") != null)
	{
		flag = request.getParameter("flag");
		rowValue = request.getParameter("rowValue");
		
	}
%>
<Style>
	div#blacklist-container{width:1000px;}
	table#tbl-member-nonblack
	{
		margin-left:50px;
		float:left
	}
	button#add-btn
	{
		
		float:left;
		margin-left:50px;
		margin-top:50px;
	}
	button#remove-btn
	{
		float:left;
		margin-right:50px;
		margin-top:50px;
	}
	table#tbl-member-black
	{
		float:right;
		margin-right:50px;
	}
	
</Style>
<section>
	<div class="bar"></div>
	<ul class="nav nav-tabs" id="admin-menu" style="margin-top:10px">
    	<li><a href="<%=request.getContextPath()%>/admin/memberList">회원 리스트 관리</a></li>
    	<li><a href="<%=request.getContextPath()%>/admin/dongleList">동글 리스트 관리</a></li>
    	<li><a href="#">메인 관리</a></li>
   		<li class="active"><a href="#">블랙 리스트 관리</a></li>
 	</ul>
	<h2>블랙 리스트 관리</h2>
	
	<div id="blacklist-container">
	<table id="tbl-member-nonblack">
		<thead>
			<tr>
				<th>
									
				</th>
				<th>
					아이디
				</th>
				<th>
					이름
				</th>
			</tr>
		</thead>
		<tbody>
			<%-- <%for(Member m : memberList) {
				%>
			<tr>
				<td><input type="checkbox" name="member-nonblack" value="<%=m.getMemberNo() %>"></td>
				<td><%=m.getMemberId() %></td>
				<td><%=m.getMemberName() %></td>
			</tr>
			<%} %> --%>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
		</tbody>
	</table>	
	<button id="add-btn">블랙 추가</button>
	<button id="remove-btn">블랙 삭제</button>
	<table id="tbl-member-black">
		<thead>
			<tr>
				<th>
									
				</th>
				<th>
					아이디
				</th>
				<th>
					이름
				</th>
			</tr>
		</thead>
		<tbody>
			<%-- <%for(Member m : memberList) {
				%>
			<tr>
				<td><input type="checkbox" name="member-black" value="<%=m.getMemberNo() %>"></td>
				<td><%=m.getMemberId() %></td>
				<td><%=m.getMemberName() %></td>
			</tr>
			<%} %> --%>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>asdfas</td>
				<td>asdfsdfds</td>
			</tr>
		</tbody>
	</table>	
	</div>
</body>
</html>