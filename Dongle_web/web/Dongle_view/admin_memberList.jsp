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
<style>
div#member-search-container  #member-searchType{
	display: block;
	width: 85px;
	height: 30px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	border-radius: 4px;
	float: left;
}
#search-user-txt{
	display: block;
	width: 200px;
	height: 30px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	border-radius: 4px;
	float: left;
	margin-left: 5px;
}
.search_list_btn{
	display: inline-block;
	margin-bottom: 0;
	font-weight: 400;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
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
	<ul class="nav nav-tabs" id="admin-menu" style="margin-top:10px">
    	<li class="active"><a href="<%=request.getContextPath()%>/admin/memberList">회원 리스트 관리</a></li>
    	<li><a href="<%=request.getContextPath()%>/admin/dongleList">동글 리스트 관리</a></li>
    	<li><a href="<%=request.getContextPath()%>/admin/editpickForm">메인 관리</a></li>
   		<li><a href="<%=request.getContextPath()%>/admin/blackMemberList">블랙 리스트 관리</a></li>
 	</ul>
	<h2 style="margin-left: 30px;">회원 리스트 관리</h2>
	<div style="margin-left: 30px;" id="member-list-container">
		<div id="member-search-container">
			<select id="member-searchType">
				<option value="memberId">회원ID</option>
				<option value="memberName">회원 이름</option>
				<option value="phone">전화번호</option>
				<option value="email">이메일</option>
			
			</select>
			<div id="search-memberId">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="memberId"/>
					<input type="text" id='search-user-txt' name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요."/>
					<button type="submit" class='search_list_btn'>검색</button>
				</form>
			</div>
			<div id="search-memberName">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="memberName"/>
					<input type="text" name="searchKeyword" id='search-user-txt' size="25" placeholder="검색할 이름을 입력하세요."/>
					<button type="submit" class='search_list_btn'>검색</button>
				</form>
			</div>
			<div id="search-phone">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="phone"/>
					<input type="text" name="searchKeyword" id='search-user-txt' size="25" placeholder="검색할 전화번호를 입력하세요."/>
					<button type="submit" class='search_list_btn'>검색</button>
				</form>
			</div>
			<div id="search-email">
				<form action="<%=request.getContextPath() %>/admin/memberSearch">
					<input type="hidden" name="member-searchType" value="email"/>
					<input type="text" name="searchKeyword" id='search-user-txt' size="25" placeholder="검색할 이메일을 입력하세요."/>
					<button type="submit" class='search_list_btn'>검색</button>
				</form>
			</div>
			
		</div>
		<br>
		<table id="tbl-member">
			<thead>
				<tr>
					<th style="width:102px;" onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("memberId")&&flag.equals("true")?"false":"true"%>&rowValue=memberId'">
						아이디				
					</th>
					<th style="width:69px;"onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("memberName")&&flag.equals("true")?"false":"true"%>&rowValue=memberName'">
						이름
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("gender")&&flag.equals("true")?"false":"true"%>&rowValue=gender'">
						성별
					</th>
					<th style="width:79px;"onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("ssn")&&flag.equals("true")?"false":"true"%>&rowValue=ssn'">
						생년월일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("phone")&&flag.equals("true")?"false":"true"%>&rowValue=phone'">
						전화번호
					</th>
					<th style="width:147px;" onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("address")&&flag.equals("true")?"false":"true"%>&rowValue=address'">
						주소
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("email")&&flag.equals("true")?"false":"true"%>&rowValue=email'">
						이메일
					</th>
					<th style="width:99px;"onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("enrollDate")&&flag.equals("true")?"false":"true"%>&rowValue=enrollDate'">
						가입일
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("blackList")&&flag.equals("true")?"false":"true"%>&rowValue=blackList'">
						&nbsp;&nbsp;블랙 &nbsp;&nbsp; 여부
					</th>
					<th onclick="location.href='<%=request.getContextPath() %>/admin/sortMemberList?flag=<%=rowValue.equals("reportCount")&&flag.equals("true")?"false":"true"%>&rowValue=reportCount'">
						&nbsp;&nbsp;신고  &nbsp;&nbsp; 횟수
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
					<td style="width:150px;"><%=m.getAddress() %></td>
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