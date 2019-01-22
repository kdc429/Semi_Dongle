<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%
 	String id=request.getParameter("userId"); 
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
	  div#updatePassword-container{
	      background:red;
	  }
	  div#updatePassword-container table {
	      margin:0 auto;
	      border-spacing: 20px;
	  }
	  div#updatePassword-container table tr:last-of-type td {
	      text-align:center;
	  }
  </style>
</head>
<body>
	<div id="updatePassword-container">
		<form name="updatePwdFrm" action="<%=request.getContextPath()%>/resetPasswordEnd" method="post">
			<table>		
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="password_new" id="password_new" required/>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input type="password" name="password_ck" id="password_ck" required/>
					</td>
				</tr>		
				<tr>
					<td colspan='2'>
						<input type="submit" onclick="return password_validate();" 
						value="변경"/>
						&nbsp;&nbsp;&nbsp;
						<input type="button" onclick="self.close();" 
						value="닫기" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="userId" value='<%=id %>'/>
			
		</form>
	</div>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		function password_validate(){
			var pw1=$('#password_new').val().trim();
			var pw2=$('#password_ck').val().trim();
			if(pw1!=pw2)
			{
				alert("입력한 비밀번호가 다릅니다.");
				$('#password_new').focus();
				$('#password_ck').val('');
				return false;
			}
			return true;			
		}
	</script>

</body>
</html>







