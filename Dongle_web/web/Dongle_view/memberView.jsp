<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당신을 위한-DONGLE</title>
<link href="<%=request.getContextPath()%>/Dongle_css/Login.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Bungee" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Sunflower:300" rel="stylesheet">


<script src="http://code.jquery.com/jquery-latest.js"></script>
<%@ page import="com.dongle.member.model.vo.Member" %>


<%
	Member m=(Member)request.getAttribute("member");
	String id=m.getMemberId();
	String name=m.getMemberName();
	String ssn=m.getSsn();
	String phone=m.getPhone();
	String address=m.getAddress();
	String email=m.getEmail();	
%>
</head>
<body>

	<section id="enroll-container">
		<h1>회원정보수정페이지</h1>
		<form id="memberFrm" method="post" 
		onsubmit="return fn_update_validate();">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" placeholder="4글자이상" 
						name="userId" id="userId_" value='<%=id %>' readonly> 
					</td>
				</tr>
				<%-- <tr>
					<th>패스워드</th>
					<td>
						<input type="password"  
						name="password" id="password_" value='<%=pw %>' required>
					</td>
				</tr>
				<tr>
					<th>패스워드확인</th>
					<td>
						<input type="password"  
						id="password_2" required>
					</td>
				</tr> --%>
				<tr>
					<th>이름</th>
					<td>
						<input type="text"  
						id="userName" name="userName" value='<%=name %>' required>
					</td>
				</tr>
<%-- 			<tr>
					<th>성별</th>
					<td>
						<%if(gender.equals("M")) {%>
							<input type="radio"  
							id="gender0" name="gender" value='M' checked>
							<label for="gender0">남</label>
							<input type="radio"  
							id="gender1" name="gender" value='F' >
							<label for="gender1">여</label>
						<% } else {%>
							<input type="radio"  
							id="gender0" name="gender" value='M' >
							<label for="gender0">남</label>
							<input type="radio"  
							id="gender1" name="gender" value='F' checked >
							<label for="gender1">여</label>
						<%} %>
					</td>
				</tr> --%>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="text"  
						id="age" name="age"  
						placeholder="931230" value='<%=ssn %>' required>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>
						<input type="tel"  
						id="phone" name="phone" maxlength="11"
						placeholder="(-없이)01012345678" value='<%=phone %>' required>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text"  
						id="address" name="address" value='<%=address %>' required>
					</td>
				</tr>
				<tr>			
					<th>이메일</th>
					<td>
						<input type="email"  
						id="email" name="email" 
						placeholder="db123@dfd.com" value='<%=email %>' required>
					</td>
				</tr>			


						
			</table>
			<input type="button" onclick="fn_update_member();" value="정보수정"/>
			<input type="button" onclick="fn_update_password();" value="비밀번호 변경"/>
			<input type="button" onclick="confirmDelete();" value="탈퇴"/>
		</form>	
	
	</section>	
	<script>
		function fn_update_password(){
			var url="<%=request.getContextPath()%>/updatePassword?userId=<%=id%>";
			var title="updatePassword";
			var status="left=200px, top=200px, width=400px, height=210px";
			var popUp=open(url,title,status);
		}
		
	
		function fn_update_validate(){
			
			return true;
		}
		//회원정보수정했을때 동작
		function fn_update_member(){
			
			var frm=$('#memberFrm');
			
			var url="<%=request.getContextPath()%>/memberUpdate";
			frm.attr('action',url);
			
			frm.submit();
		}
	
	
	</script>

</body>
</html>
