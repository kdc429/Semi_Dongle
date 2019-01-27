<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.ListGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="header.jsp"%>

<%	
	List<ListGroup> dongleList = (List)request.getAttribute("dongleList");

	String flag = "false";
	String rowValue = "";
	if(request.getParameter("flag") != null && request.getParameter("rowValue") != null)
	{
		flag = request.getParameter("flag");
		rowValue = request.getParameter("rowValue");
		
	}
%>
<script>
	window.onload=function(){
		var smanagerId=document.querySelector("#search-managerId");
		var sname=document.querySelector("#search-dongleName");
		var senDate=document.querySelector("#search-dongleEnDate");
		var smetro=document.querySelector("#search-metro");
		
		
		var searchType=document.querySelector("#dongle-searchType");
		searchType.addEventListener("change",function(){
			
			smanagerId.style.display="none";
			sname.style.display="none";
			senDate.style.display="none";
			smetro.style.display="none";
			
			document.querySelector("#search-"+this.value)
			.style.display="inline-block";
			
		});
	}
</script>
<section>
	<div class="bar"></div>
	<ul class="nav nav-tabs" id="admin-menu" style="margin-top:10px">
    	<li><a href="<%=request.getContextPath() %>/admin/memberList">회원 리스트 관리</a></li>
    	<li class="active"><a href="<%=request.getContextPath()%>/admin/dongleList">동글 리스트 관리</a></li>
    	<li><a href="<%=request.getContextPath()%>/admin/editpickForm">메인 관리</a></li>
   		<li><a href="<%=request.getContextPath()%>/admin/blackMemberList">블랙 리스트 관리</a></li>
 	</ul>
	<h2>동글 리스트 관리</h2>
	<div id="dongle-list-container">
		<div id="dongle-search-container">
			<select id="dongle-searchType">
				<option value="dongleName">동글명</option>
				<option value="managerId">매니저 아이디</option>
				<option value="metro">지역</option>
				<option value="dongleEnDate">생성일</option>
				
			</select>
			<div id="search-dongleName">
				<form action="<%=request.getContextPath() %>/admin/dongleSearch">
					<input type="hidden" name="dongle-searchType" value="dongleName"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-managerId">
				<form action="<%=request.getContextPath() %>/admin/dongleSearch">
					<input type="hidden" name="dongle-searchType" value="managerId"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-metro">
				<form action="<%=request.getContextPath() %>/admin/dongleSearch">
					<input type="hidden" name="dongle-searchType" value="metro"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 지역을 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-dongleEnDate">
				<form action="<%=request.getContextPath() %>/admin/dongleSearch">
					<input type="hidden" name="dongle-searchType" value="dongleEnDate"/>
					<input type="date" name="searchKeyword"/>
					<button type="submit">검색</button>
				</form>
			</div>
			
		</div>
		<br>
		<table id="tbl-dongle">
			<thead>
				<tr>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("dongleName")&&flag.equals("true")?"false":"true"%>&rowValue=dongleName'">
						동글명				
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("managerId")&&flag.equals("true")?"false":"true"%>&rowValue=managerId'">
						창설자 아이디
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("topic")&&flag.equals("true")?"false":"true"%>&rowValue=topic'">
						주제
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("metro")&&flag.equals("true")?"false":"true"%>&rowValue=metro'">
						지역
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("date")&&flag.equals("true")?"false":"true"%>&rowValue=date'">
						주중/주말
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("minAge")&&flag.equals("true")?"false":"true"%>&rowValue=minAge'">
						최소 가입 연령
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("maxAge")&&flag.equals("true")?"false":"true"%>&rowValue=maxAge'">
						최대 가입 연령
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("enrollDate")&&flag.equals("true")?"false":"true"%>&rowValue=enrollDate'">
						생성일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("reportCnt")&&flag.equals("true")?"false":"true"%>&rowValue=reportCnt'">
						신고 횟수
					</th>
				</tr>
			</thead>
			<tbody>
				<% if(dongleList==null || dongleList.isEmpty()) {%>
				<tr>
					<td colspan="10" align="center">
						검색결과가 없습니다.
					</td>
				</tr>
				<%} else { 
					for(ListGroup g : dongleList) {
				%>
				<tr >
					<td><%=g.getDongleName() %></td>
					<td><%=g.getManagerId() %></td>
					<td><%=g.getTopic() %></td>
					<td><%=g.getAddress() %></td>
					<td><%=g.getActiveDate() %></td>
					<td><%=g.getMinAge() %></td>
					<td><%=g.getMaxAge() %></td>
					<td><%=g.getEnrollDate() %></td>
					<td><%=g.getReportCnt() %></td>
					
				</tr>
				<%}
				}%>
			</tbody>
		</table>
	</div>
</section>
	
	
</body>
</html>