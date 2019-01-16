<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//true면 사용할 수 있다. false면 사용할 수 없다.
	boolean isAble=(boolean)request.getAttribute("isAble");
	String userId=(String)request.getAttribute("userId");
%>    
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
	<div id="checkid-container">
		<%if(isAble) { %>
			<h1 style="color:green;">[<%=userId %>]는 사용가능합니다.</h1>
			<br><br>
			<button type="button" onclick="setUserId('<%=userId%>');">닫기</button>
		<%}
		  else {
		%>
			<h1 style="color:red;">[<%=userId %>]는 사용할 수 없습니다.</h1>
			<br><br>
			<form action="<%= request.getContextPath()%>/checkIdDuplicate"
				name="checkDuplicateFrm" method="post">
				<input type="text" name="userId" id="userId" 
				placeholder="4글자이상 입력하세요"/>
				<button type="button" onclick="fn_checkIdDuplicate();">
				중복검사</button>		
			</form>
		<%} %>
	</div>
	<script>
		function fn_checkIdDuplicate(){
			var userId=$('#userId').val().trim();
			if(!userId||userId.length<4)
			{
				alert("아이디를 4자이상 입력하세요!");
				$("#userId").val("");
				$("#userId").focus();
				return;
			}
			checkDuplicateFrm.submit();
		}
		function setUserId(userId)
		{
			var frm=opener.document.memberEnrollFrm;//부모창을 호출
			console.log(frm.isValid);
			frm.userId.value=userId;
			frm.idValid.value='1';
			frm.password.focus();
			
			self.close();//현재 열려있는 창을 닫는 것			
		}
		
		
	</script>


</body>
</html>







