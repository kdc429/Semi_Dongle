<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//true면 사용할 수 있다. false면 사용할 수 없다.
	boolean isAble=(boolean)request.getAttribute("isAble");
	String nickname=(String)request.getAttribute("nickname");
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
			<h1 style="color:black;">사용가능합니다.</h1>
			<br><br>
			<button type="button" onclick="setnickname('<%=nickname%>');">닫기</button>
		<%}
		  else {
		%>
			<h1 style="color:red;">사용할 수 없습니다.</h1>
			<br><br>
			<form action="<%= request.getContextPath()%>/checkdongleDuplicate"
				name="checkDuplicateFrm" method="post">
				<input type="text" name="nickname" id="nickname" 
				placeholder="2글자이상 입력하세요"/>
				<button type="button" onclick="fn_checkdongleDuplicate();">
				중복검사</button>		
			</form>
		<%} %>
	</div>
	<script>
		function fn_checkdongleDuplicate(){
			var nickname=$('#nickname').val().trim();
			if(!nickname||nickname.length<2)
			{
				alert("닉네임을 2자이상 입력하세요!");
				$("#nickname").val("");
				$("#nickname").focus();
				return;
			}
			checkDuplicateFrm.submit();
		}
		
		function setnickname(nickname)
		{
			console.log("ffasdafds");
			var frm=opener.document.dongleMemberUpdate; //부모창을 호출
			console.log(nickname);
			console.log(frm.isValid);
			frm.nickname.value=nickname;
			frm.idValid.value='1';
		
			self.close();//현재 열려있는 창을 닫는 것	
		}
		
		
	</script>


</body>
</html>







