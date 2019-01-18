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
<script>
	window.onload=function(){
		var smanagerId=document.querySelector("#search-managerId");
		var sname=document.querySelector("#search-dongleName");
		var senDate=document.querySelector("#search-dongleEnDate");
		var sreportCnt=document.querySelector("#search-dongleReportCnt");
		
		
		var searchType=document.querySelector("#dongle-searchType");
		searchType.addEventListener("change",function(){
			smanagerId.style.display="none";
			sname.style.display="none";
			senDate.style.display="none";
			srePortCnt.style.display="none";
			
			document.querySelector("#search-"+this.value)
			.style.display="inline-block";
		});
	}
</script>
<section>
	<div class="bar"></div>
	<ul class="nav nav-tabs" id="admin-menu" style="margin-top:10px">
    	<li><a href="<%=request.getContextPath() %>/admin/memberList">회원 리스트 관리</a></li>
    	<li class="active"><a href="#">동글 리스트 관리</a></li>
    	<li><a href="#">메인 관리</a></li>
   		<li><a href="#">블랙 리스트 관리</a></li>
 	</ul>
	<h2>동글 리스트 관리</h2>
	<div id="dongle-list-container">
		<div id="dongle-search-container">
			<select id="dongle-searchType">
				<option value="managerId">매니저 아이디</option>
				<option value="dongleName">동글명</option>
				<option value="dongleEnDate">생성일</option>
				<option value="reportCnt">신고 횟수</option>
			</select>
			<div id="search-memberId">
				<form action="<%=request.getContextPath() %>/admin/dongleSearch">
					<input type="hidden" name="dongle-searchType" value="managerId"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-dongleName">
				<form action="<%=request.getContextPath() %>/admin/dongleSearch">
					<input type="hidden" name="dongle-searchType" value="dongleName"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-dongleEnDate">
				<form action="<%=request.getContextPath() %>/admin/dongleSearch">
					<input type="hidden" name="dongle-searchType" value="phone"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 전화번호를 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-email">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="email"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 이메일을 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-blackList">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="memberId"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
		</div>
		<br>
		<table id="tbl-member">
			<thead>
				<tr>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("memberId")&&flag.equals("true")?"false":"true"%>&rowValue=memberId'">
						아이디				
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("memberName")&&flag.equals("true")?"false":"true"%>&rowValue=memberName'">
						이름
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("gender")&&flag.equals("true")?"false":"true"%>&rowValue=gender'">
						성별
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("ssn")&&flag.equals("true")?"false":"true"%>&rowValue=ssn'">
						생년월일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("phone")&&flag.equals("true")?"false":"true"%>&rowValue=phone'">
						전화번호
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("address")&&flag.equals("true")?"false":"true"%>&rowValue=address'">
						주소
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("email")&&flag.equals("true")?"false":"true"%>&rowValue=email'">
						이메일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("enrollDate")&&flag.equals("true")?"false":"true"%>&rowValue=enrollDate'">
						가입일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("blackList")&&flag.equals("true")?"false":"true"%>&rowValue=blackList'">
						블랙 여부
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortList?flag=<%=rowValue.equals("reportCount")&&flag.equals("true")?"false":"true"%>&rowValue=reportCount'">
						신고 횟수
					</th>
				</tr>
			</thead>
			<tbody>
				<% if(memberList==null || memberList.isEmpty()) {%>
				<tr>
					<td colspan="10" align="center">
						검색결과가 없습니다.
					</td>
				</tr>
				<%} else { 
					for(Member m : memberList) {
				%>
				<tr>
					<td><%=m.getMemberId() %></td>
					<td><%=m.getMemberName() %></td>
					<td><%=m.getGender() %></td>
					<td><%=m.getSsn() %></td>
					<td><%=m.getPhone() %></td>
					<td><%=m.getAddress() %></td>
					<td><%=m.getEmail() %></td>
					<td><%=m.getEnrollDate() %></td>
					<td><%=m.getBlackList() %></td>
					<td><%=m.getReportCount() %></td>
				</tr>
				<%}
				}%>
			</tbody>
		</table>
	</div>
</section>
	
	
</body>
</html>