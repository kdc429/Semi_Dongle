<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 

<%@ include file="header.jsp"%>
<%	
	List<Group> list=(List)request.getAttribute("list");
	List<Member> memberList = (List)request.getAttribute("memberList");
	String searchType = request.getParameter("member-searchType");
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
	    div#search-memberId{display:<%="memberId".equals(searchType)||searchType==null?"inline-block":"none"%>;}
	    div#search-memberName{display:<%="memberName".equals(searchType)?"inline-block":"none"%>;}
	    div#search-phone{display:<%="phone".equals(searchType)?"inline-block":"none"%>;}
	    div#search-email{display:<%="email".equals(searchType)?"inline-block":"none"%>;}
	    
	   	
</style>

<script>
	window.onload=function(){
		var sid=document.querySelector("#search-memberId");
		var sname=document.querySelector("#search-memberName");
		var sphone=document.querySelector("#search-phone");
		var semail=document.querySelector("#search-email");
		
		
		var searchType=document.querySelector("#member-searchType");
		
		searchType.addEventListener("change",function(){
			sid.style.display="none";
			sname.style.display="none";
			sphone.style.display="none";
			semail.style.display="none";
			
			
			document.querySelector("#search-"+this.value)
			.style.display="inline-block";
		});
		
		
	}
	function fn_memberView(memberNo)
	{
		var url="<%=request.getContextPath()%>/admin/memberPopUpView";
		var title="memberView";
		var shape="left=200px, top=100px, width=470px, height= 270px";
		
		var popup=open("",title,shape);
		
		memberViewFrm.memberNo.value=memberNo;
		memberViewFrm.target=title;
		memberViewFrm.action=url;
		memberViewFrm.method="post";
		memberViewFrm.submit();
		
	}
</script>
<section>
	<div class="bar"></div>
	<ul class="nav nav-tabs" id="admin-menu" style="margin-top:10px">
    	<li class="active"><a href="<%=request.getContextPath()%>/admin/memberList">회원 리스트 관리</a></li>
    	<li><a href="<%=request.getContextPath()%>/admin/dongleList">동글 리스트 관리</a></li>
    	<li><a href="#">메인 관리</a></li>
   		<li><a href="<%=request.getContextPath()%>/admin/blackMemberList">블랙 리스트 관리</a></li>
 	</ul>
	<h2>회원 리스트 관리</h2>
	<div id="member-list-container">
		
		<div id="member-search-container">
			<select id="member-searchType">
				<option value="memberId" <%=searchType.equals("memberId")?"selected":""%>>회원ID</option>
				<option value="memberName" <%=searchType.equals("memberName")?"selected":""%>>회원 이름</option>
				<option value="phone" <%=searchType.equals("phone")?"selected":""%>>전화번호</option>
				<option value="email" <%=searchType.equals("email")?"selected":""%>>이메일</option>
				<option value="blackList" <%=searchType.equals("blacklist")?"selected":""%>>블랙 여부</option>
			</select>
			<div id="search-memberId">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="memberId"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-memberName">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="memberName"/>
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."/>
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-phone">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="phone"/>
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
			
		</div>
		<br>
		<table id="tbl-member">
			<thead>
				<tr>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("memberId")&&flag.equals("true")?"false":"true"%>&rowValue=memberId&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						아이디				
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("memberName")&&flag.equals("true")?"false":"true"%>&rowValue=memberName&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						이름
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("gender")&&flag.equals("true")?"false":"true"%>&rowValue=gender&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						성별
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("ssn")&&flag.equals("true")?"false":"true"%>&rowValue=ssn&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						생년월일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("phone")&&flag.equals("true")?"false":"true"%>&rowValue=phone&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						전화번호
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("address")&&flag.equals("true")?"false":"true"%>&rowValue=address&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						주소
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("email")&&flag.equals("true")?"false":"true"%>&rowValue=email&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						이메일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("enrollDate")&&flag.equals("true")?"false":"true"%>&rowValue=enrollDate&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						가입일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("blackList")&&flag.equals("true")?"false":"true"%>&rowValue=blackList&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						블랙 여부
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("reportCount")&&flag.equals("true")?"false":"true"%>&rowValue=reportCount&member-searchType=<%=searchType %>&searchKeyword=<%=searchKeyword %>'">
						신고 횟수
					</th>
				</tr>
			
			</thead>
			<tbody>
				<% if(memberList==null || memberList.isEmpty()) {%>
				<tr>
					<td colspan="10" align="center" style="width:1000px">
						검색결과가 없습니다.
					</td>
				</tr>
				<%} else { 
					for(Member m : memberList) {
				%>
				<tr onclick="fn_memberView(<%=m.getMemberNo()%>);">
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
		<form action="" name="memberViewFrm">
			<input type="hidden" name="memberNo"/>
		</form>	
	</div>
</section>
	
	
</body>
</html>