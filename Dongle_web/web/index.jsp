<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당신을 위한-DONGLE</title>
<link href="<%=request.getContextPath()%>/css/Login.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Bungee" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Sunflower:300" rel="stylesheet">
<style>
#btn_find{
	width: 173px;
	height: 24px;
	
}
</style>
</head>
<body>
	<div class="logo-text">당신을 위한 맞춤형 동호회</div>
    <div class="back">
        <div class="login-box">
            <div class="logo">DONGLE</div>
            <form id="loginFrm" action="<%=request.getContextPath()%>/login" method="post" onsubmit="return validate();">
                <div class="login">
                    <label for="">
                        <input type="text" name="userId" id="userId" placeholder="아이디">
                    </label><br>
                    <label for="">
                        <input type="password" name="password" id="password" placeholder="비밀번호">
                    </label><br>
                    <label for="">
                        <input type="checkbox" name="saveId" id="saveId">아이디 저장
                    </label><br>
                    <button id="btn_login" onclick='login()'>로그인</button><br>
                    <input type="button" value="회원가입"  
                     onclick="location.href='<%=request.getContextPath() %>/memberEnroll'"/>
              	</form>
               </div>
               		<button id="btn_find" onclick="location.href='<%=request.getContextPath()%>/Dongle_view/idPwdFind.jsp'">아이디/비밀번호 찾기</button><br>
              </div>            
        </div>
     </div>
  
</body>
</html>