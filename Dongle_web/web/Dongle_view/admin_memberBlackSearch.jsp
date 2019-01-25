<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*"%>
<%@ include file="header.jsp"%>

<%
	List<Group> list = (List) request.getAttribute("list");
	List<Member> blackList = (List) request.getAttribute("blackList");
	List<Member> nonBlackList = (List) request.getAttribute("nonBlackList");
	
	String searchBlack;
	if((String)request.getAttribute("search-black") == null)
	{
		searchBlack = "";
	}
	else
	{
		searchBlack = (String)request.getAttribute("search-black");
	}
	
	String searchNonBlack;
	if( (String)request.getAttribute("search-nonblack") == null)
	{
		searchNonBlack = "";
	}
	else
	{
		searchNonBlack = (String)request.getAttribute("search-nonblack");
	}
	
	
	
%>
<script>
	function validate1(){
		if(!$(".member-nonblack").prop("checked"))
		{
			return false;	
		}
		return true;
	}
	function validate2(){
		if(!$(".member-black").prop("checked"))
		{
			return false;	
		}
		return true;
	}

</script>
<style>
#search-black-txt{
	width: 200px;
	height: 30px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	border-radius: 4px;
	margin-left: 5px;
}
#add-btn{
	font-weight: 400;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	border : 1px solid transparent;
	font-size: 14px;
	line-height: 1.43;
	border-radius: 4px;
	width: 100px;
	height: 30px;
}
#remove-btn{
	font-weight: 400;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	border : 1px solid transparent;
	font-size: 14px;
	line-height: 1.43;
	border-radius: 4px;
	width: 100px;
	height: 30px;
}

.search_black_btn{
	display: inline-block;
	margin-bottom: 0;
	font-weight: 400;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	border : 1px solid transparent;
	font-size: 14px;
	line-height: 1.43;
	border-radius: 4px;
	width: 50px;
	height: 30px;
	margin-left: 5px;
}
</style>
<section>
	<div class="bar"></div>
	<ul class="nav nav-tabs" id="admin-menu" style="margin-top: 10px">
		<li><a href="<%=request.getContextPath()%>/admin/memberList">회원
				리스트 관리</a></li>
		<li><a href="<%=request.getContextPath()%>/admin/dongleList">동글
				리스트 관리</a></li>
		<li><a href="#">메인 관리</a></li>
		<li class="active"><a href="#">블랙 리스트 관리</a></li>
	</ul>
	<h2>블랙 리스트 관리</h2>
	<div id="blackSearch-container">
		<form id="nonblack-search-frm" method="post" action="<%=request.getContextPath() %>/admin/searchBlack?search-black=<%=searchBlack%>">
			아이디 : <input type="text" name="search-nonblack" id='search-black-txt' size="15"/>
			<input type="submit" class='search_black_btn' value="검색">
		</form>
		<form id="black-search-frm" method="post" action="<%=request.getContextPath() %>/admin/searchBlack?search-nonblack=<%=searchNonBlack%>">
			아이디 : <input type="text" name="search-black" id='search-black-txt' size="15"/>
			<input type="submit" class='search_black_btn' value="검색">
		</form>
	</div>
	<div id="blacklist-container">
		<form action="<%=request.getContextPath()%>/admin/addBlack" onsubmit="return validate1()">
			<table id="tbl-member-nonblack">
				<thead>
					<tr>
						<th></th>
						<th>아이디</th>
						<th>이름</th>
					</tr>
				</thead>
				<tbody>
					<%
						if (nonBlackList.size() < 6) {
					%>
					<%	
						for (Member n : nonBlackList) {
					%>
					<tr>
						<td><input type="checkbox" name="member-nonblack" class="member-nonblack"
							value="<%=n.getMemberNo()%>"></td>
						<td><%=n.getMemberId()%></td>
						<td><%=n.getMemberName()%></td>
					</tr>
					<%
						}
						for(int i = 0; i < 6-nonBlackList.size(); i++)
						{
					%>
							<tr>
								<td></td>
								<td></td>
								<td>&nbsp</td>
							</tr>
					<%
						}
						}else {
					%>

					<%
						for (Member n : nonBlackList) {
					%>
					<tr>
						<td><input type="checkbox" name="member-nonblack" class="member-nonblack"
							value="<%=n.getMemberNo()%>"></td>
						<td><%=n.getMemberId()%></td>
						<td><%=n.getMemberName()%></td>
					</tr>
					<%
						}
						}
					%>


				</tbody>

			</table>
			<button id="add-btn">블랙 추가&gt;&gt;</button>
		</form>


		<form action="<%=request.getContextPath()%>/admin/deleteBlack" onsubmit="return validate2()")>
			<table id="tbl-member-black">
				<thead>
					<tr>
						<th></th>
						<th>아이디</th>
						<th>이름</th>
					</tr>
				</thead>
				<tbody>
					<%
						if (blackList.size() < 6) {
					%>
					<%	
						for (Member b : blackList) {
					%>
					<tr>
						<td><input type="checkbox" name="member-black" class="member-black"
							value="<%=b.getMemberNo()%>"></td>
						<td><%=b.getMemberId()%></td>
						<td><%=b.getMemberName()%></td>
					</tr>
					<%
						}
						for(int i = 0; i < 6-blackList.size(); i++)
						{
					%>
							<tr>
								<td></td>
								<td></td>
								<td>&nbsp</td>
							</tr>
					<%
						}
						}else {
					%>
					

					<%
						for (Member b : blackList) {
					%>
					<tr>
						<td><input type="checkbox" name="member-black" class="member-black"
							value="<%=b.getMemberNo()%>"></td>
						<td><%=b.getMemberId()%></td>
						<td><%=b.getMemberName()%></td>
					</tr>
					<%
						}
						}
					%>

				</tbody>
			</table>
			<button id="remove-btn">&lt;&lt;블랙 제외</button>
		</form>

	</div>
	</body>
	</html>