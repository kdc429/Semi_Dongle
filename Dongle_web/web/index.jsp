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
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
#btn_find{
	width: 215px;
	height: 34px;
}
.logo-text{
font-family: '나눔스퀘어라운드 Regular';
/* font-family: '여기어때잘난서체'; */
}
.lo}
</style>
</head>
<body>
	<div class="logo-text">당신을 위한 맞춤형 동호회</div>
    <div class="back">
        <div class="login-box">
            <div class="logo" style="font-family: '여기어때잘난서체';margin-top: 100px">DONGLE</div>
            
            <div>
            	<form id="loginFrm" action="<%=request.getContextPath()%>/login" method="post" onsubmit="return validate();">
                <div class="login">
                    <label for="">
                        <input type="text" name="userId" id="userId" class="form-control" placeholder="아이디">
                    </label><br>
                    <label for="">
                        <input type="password" name="password" id="password"  class="form-control"placeholder="비밀번호">
                    </label><br>
                    <label for="">
                        <input type="checkbox" name="saveId" id="saveId">아이디 저장
                    </label><br>

                    <button id="btn_login" onclick='login()' class="btn btn-primary" style="font-family: 'netmarble Medium';">로그인</button><br>
                    <input type="button" value="회원가입" class="btn btn-primary" style="font-family: 'netmarble Medium';"
                     onclick="location.href='<%=request.getContextPath() %>/memberEnroll'"/>
              	</form><br>
              	
               </div>
               		<button id="btn_find" class="btn btn-primary" style="font-family: 'netmarble Medium';" onclick="location.href='<%=request.getContextPath()%>/Dongle_view/idPwdFind.jsp'">아이디/비밀번호 찾기</button><br>
              </div>            
            </div>
            

        </div>
     </div>
  
</body>
</html>
