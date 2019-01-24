<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.vo.ListGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="header.jsp"%>

<%	
	List<Group> list=(List)request.getAttribute("list");
	List<ListGroup> dongleList = (List)request.getAttribute("dongleList");
	String searchType = request.getParameter("dongle-searchType");
	String searchKeyword=request.getParameter("searchKeyword");
	String flag = "false";
	String rowValue = "";
	if(request.getParameter("flag") != null && request.getParameter("rowValue") != null)
	{
		flag = request.getParameter("flag");
		rowValue = request.getParameter("rowValue");
		
	}
%>
<style>
	    div#search-dongleName{display:<%="dongleName".equals(searchType)||searchType==null?"inline-block":"none"%>;}
	    div#search-managerId{display:<%="managerId".equals(searchType)?"inline-block":"none"%>;}
	    div#search-dongleEnDate{display:<%="dongleEnDate".equals(searchType)?"inline-block":"none"%>;}
	    div#search-metro{display:<%="metro".equals(searchType)?"inline-block":"none"%>;}
	    
	   	
</style>

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
    	<li><a href="#">메인 관리</a></li>
   		<li><a href="<%=request.getContextPath()%>/admin/blackMemberList">블랙 리스트 관리</a></li>
 	</ul>
	<h2>동글 리스트 관리</h2>
	<div id="dongle-list-container">
		<div id="dongle-search-container">
			<select id="dongle-searchType">
				<option value="dongleName" <%=searchType.equals("dongleName")?"selected":"" %>>동글명</option>
				<option value="managerId" <%=searchType.equals("managerId")?"selected":"" %>>매니저 아이디</option>
				<option value="metro" <%=searchType.equals("metro")?"selected":"" %>>지역</option>
				<option value="dongleEnDate" <%=searchType.equals("dongleEnDate")?"selected":"" %>>생성일</option>
				
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
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("dongleName")&&flag.equals("true")?"false":"true"%>&rowValue=dongleName&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						동글명				
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("managerId")&&flag.equals("true")?"false":"true"%>&rowValue=managerId&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						창설자 아이디
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("topic")&&flag.equals("true")?"false":"true"%>&rowValue=topic&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						주제
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("metro")&&flag.equals("true")?"false":"true"%>&rowValue=metro&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						지역
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("date")&&flag.equals("true")?"false":"true"%>&rowValue=date&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						주중/주말
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("minAge")&&flag.equals("true")?"false":"true"%>&rowValue=minAge&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						최소 가입 연령
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("maxAge")&&flag.equals("true")?"false":"true"%>&rowValue=maxAge&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						최대 가입 연령
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("enrollDate")&&flag.equals("true")?"false":"true"%>&rowValue=enrollDate&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						생성일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortDongleList?flag=<%=rowValue.equals("reportCnt")&&flag.equals("true")?"false":"true"%>&rowValue=reportCnt&dongle-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						신고 횟수
					</th>
				</tr>
			</thead>
			<tbody>
				<% if(dongleList==null || dongleList.isEmpty()) {%>
				<tr>
					<td colspan="9" align="center">
						검색결과가 없습니다.
					</td>
				</tr>
				<%} else { 
					for(ListGroup g : dongleList) {
				%>
				<tr>
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