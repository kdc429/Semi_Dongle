<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int groupNo=(int)request.getAttribute("groupNo");
	String memberId=(String)request.getAttribute("memberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>앨범 추가하기</title>
<style>
section#albumPlus-container{padding:0 auto;margin-left:120px; margin-top:30px;}
section#albumPlus-container input{font-size:18px;font-family:'a흑진주L';}
section#albumPlus-container button{color:gray;border:none;font-family:'a흑진주L';}
</style>
</head>
<script>
	function fn_albumPlusFrm(){
		return true;
	} 
	function fn_albumPlus(){
		albumPlusFrm.submit();
	}
</script>
<body>
	<section id="albumPlus-container">
		<form action="<%=request.getContextPath()%>/albumNameCheck" name="albumPlusFrm" method="post" onsubmit="return fn_albumPlusFrm">
			<table>
				<tr>
					<td>앨범 명:&nbsp;<input type="text" name="albumNameP" id="albumNameP" size="20"/></td>
					<td>
						<button onclick="fn_albumPlus()">확인</button>
						<input type="hidden" name="groupNo" value=<%=groupNo%>>
						<input type="hidden" name="memberId" value=<%=memberId%>>
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>