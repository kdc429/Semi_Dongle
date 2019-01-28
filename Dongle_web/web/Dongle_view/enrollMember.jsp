<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당신을 위한-DONGLE</title>
<link rel='icon' href='https://i.imgur.com/8k8yVjE.png'>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
    
        #emp{
            margin: 150px auto;
            padding: 30px;
            left: 30%;
            width: 550px;
        }
        .logo-text {
        font-family: 'Do Hyeon', sans-serif; 
        color: white;
        font-size: 50px;
        margin-left: 110px;
        margin-bottom: 50px;
        margin-top: 40px;
        letter-spacing: 40px;
    }
    #line{
        border:6px solid #EAEAEA;
        width: 500px; 
        border-radius: 10px;
        padding: 20px;
    }
    </style>
</head>
<body>   
<section id='enroll-container'>
 <div id="emp">
 <div id="line">
 <div class="form-group">
    <form name='memberEnrollFrm' action="<%=request.getContextPath()%>/memberEnrollEnd" method="post" onsubmit="return fn_enroll_validate();">
   <h2 style="text-align: center; font-family:'netmarble Medium'; font-weight: bold;">회원가입 화면</h2>
   <br/> 
   <div class="form-group">
       <input type="text"  class="form-control" placeholder="아이디(영문과 숫자:4~12자리)" name="userId" id="userId_" style="display: inline-block; font-family:'나눔스퀘어라운드 Regular'; "required>
<!--        <button type="button" class="btn btn-default" id='id_ck_btn'style="display: inline-block; float: right; font-family:'나눔스퀘어라운드 Regular';" onclick="fn_checkduplicate();">중복검사</button> -->
       <input type='hidden' name="idValid" value="0" style="font-family:'나눔스퀘어라운드 Regular';"/> 
   </div>
          <div id="id_check" style='width:auto; height:auto;'></div>
   <div class="form-group">
        <input type="password" class="form-control" name="password" id="password_" placeholder="패스워드(영문과 숫자:4~12자리)" style="font-family:'나눔스퀘어라운s드 Regular';" required>
   </div>
   <div>
        <input type="password" class="form-control" id="password_2" placeholder="패스워드확인" style="font-family:'나눔스퀘어라운드 Regular';" required>
   </div>
   <br/>
 <div class="form-group">                   
    <select id='pwdHint' name='pwdHint' class="form-control" style="font-family:'나눔스퀘어라운드 Regular';" required/>
       <!-- <option value='나의 고향은?' style="font-family:'나눔스퀘어라운드 Regular';" >비밀번호 힌트</option> -->
       <option value="" disabled selected hidden style="font-family:'나눔스퀘어라운드 Regular';">비밀번호 힌트</option>
        <option value='나의 고향은?' style="font-family:'나눔스퀘어라운드 Regular';" >나의 고향은?</option>
        <option value="나의 보물1호는?" style="font-family:'나눔스퀘어라운드 Regular';" >나의 보물1호는?</option>
        <option value='나의 어머니 이름은?' style="font-family:'나눔스퀘어라운드 Regular';" >나의 어머니 이름은?</option>
        <option value="나의 아버지 이름은?" style="font-family:'나눔스퀘어라운드 Regular';" >나의 아버지 이름은?</option>
        <option value='어릴 적 친했던 친구는?' style="font-family:'나눔스퀘어라운드 Regular';" >어릴 적 친했던 친구는?</option>
        <option value="가장 좋아했던 캐릭터는?" style="font-family:'나눔스퀘어라운드 Regular';" >가장 좋아했던 캐릭터는?</option>
     </select>
  </div>
  <div class="form-group">
       <input type="text" class="form-control" id="hintAnswer" name='hintAnswer' placeholder="힌트답변" style="font-family:'나눔스퀘어라운드 Regular';" required/>
 </div> 
 <div class="form-group">
       <input type="text" class="form-control" id="userName" name="userName" placeholder="이름" style="font-family:'나눔스퀘어라운드 Regular';"  required>
    </div>
    <div class="form-group" style="text-align: center;">
     <div class="btn-group" data-toggle="buttons">          
         <label class="btn btn-default active" style="width:224px; font-family:'나눔스퀘어라운드 Regular';">
          <input type="radio" id="gender0" name="gender" value='M' checked>남
       </label>
       <label class="btn btn-default active" style="width:224px; font-family:'나눔스퀘어라운드 Regular';">
          <input type="radio"  id="gender1" name="gender" value='W' checked>여
       </label>
    </div>   
   </div>
  <div class="form-group">
        <input type="text"  class="form-control" id="age" name="age" placeholder="생년월일(190124)" style="font-family:'나눔스퀘어라운드 Regular';" required>
  </div>
  <div class="form-group">
        <input type="tel"  class="form-control"id="phone" name="phone" maxlength="11" placeholder="전화번호(-없이)" style="font-family:'나눔스퀘어라운드 Regular';" required>
  </div>
  <div class="form-group">
        <input type="text"  class="form-control"id="address" name="address" placeholder="주소" style="font-family:'나눔스퀘어라운드 Regular';" required>
  </div>
  <div class="form-group">
     <input type="email"   class="form-control"id="email" name="email" placeholder="이메일(db123@naver.com)" style="font-family:'나눔스퀘어라운드 Regular';" required>
  </div>
 <div class="btn">
    <button type="submit" style="font-family:'나눔스퀘어라운드 Regular'; margin-left:150px" class="btn btn-primary" >가입</button>
 </div>
 <button style="font-family: '나눔스퀘어라운드 Regular';" class="btn btn-primary" id='enroll-reset-btn' onclick="location.href='<%=request.getContextPath()%>/index.jsp'">취소</button>

   </form>
   </div>
   <form action="" name="checkIdDuplicateFrm">
      <input type="hidden" name="userId"/>
   </form>
   </div>
   </div>   

</section>
<script>
var re = /^[a-z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
var re22=/^[a-z][a-z\d]{3,11}$/;
    var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    // 이메일이 적합한지 검사할 정규식
    
    var id = document.getElementById("userId_");
    var pw = document.getElementById("password_");
    var pwf = document.getElementById("password_2");
    var email = document.getElementById("email");
/*     var name = document.getElementById("userName");
    var hint = document.getElementById("hintAnswer");
    var age = document.getElementById("age");
    var phone = document.getElementById("phone");
    var address = document.getElementById("address"); */
  

function check(re, what, message) {
    if(re.test(what.value)) {
         return true;
     }else{
        //alert(message);
        //what.value = "";
         //what.focus();
         return false;
     }  
} 
$(function(){    
   $("#password_2").blur(function(){
      if(pw.value!=pwf.value){
         alert("패스워드가 일치하지 않습니다.");
         $("#password_2").val('');
         $("#password_").focus();
      }
   });
});

function fn_enroll_validate(){

    if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
       alert("적합하지 않은 이메일 형식입니다.");
        return false;
    }
    
/*     if(name.value=="") {
        alert("이름을 입력해 주세요");
        name.focus();
        return false;
    } */
}

//아이디 중복검사하기 : 팝업창을 띄워서 해보자~! 
function fn_checkduplicate(){
   var userId=$("#userId_").val().trim();
   if(!userId || userId.length<4)
   {
      alert("아이디를 4글자 이상 입력하세요~!");
      return;   
   }
   if(!check(re22,id,'첫글자가 영문자 소문자이고 4~12자를 입력해주세요.'))
   {//^[a-z]가 한 자리를 차지하기 때문에 3부터 11까지임
       alert("첫글자가 영문자 소문자이고 4~12자를 입력해주세요.");
      return;
     } 
}
  $(function(){
    $('#userId_').on('change keyup paste',(function(){
         console.log("dd");
         var idTest=re.test($('#userId_').val());
         console.log(idTest);
         console.log($('#userId_').val());
          if($('#userId_').val()==""){//아이디 빈칸일때
               $('#id_check').html("");
            }else{
               
               if(!idTest)
               {//^[a-z]가 한 자리를 차지하기 때문에 3부터 11까지임
                  $('#id_check').html("첫글자가 영문자 소문자이고 4~12자를 입력해주세요.").css('color', 'red');
                  return;
               }else{
                  
                  $.ajax({
                        url:'<%=request.getContextPath()%>/checkIdDuplicate',
                        type:"POST",
                        data:{"userId":$('#userId_').val()},
                        success:function(data){
                           console.log(data);
                           if(data){
                              
                              $('#id_check').html("사용 가능한 아이디입니다.").css('color', 'green');               
                           }
                           else if(!data){
                              
                              $('#id_check').html("사용 불가능한 아이디입니다.").css('color', 'red');         
                           }
                        },
         /*                 error:function(xhr,status){
                           alert(xhr+" : "+status);
                      } */
                     
                   });
               } 

               
            }
    })); 
  });
 <%--   //팝업창에 대한 설정해주기!@
   var url="<%=request.getContextPath()%>/checkIdDuplicate";
   var title="checkIdDuplicate";
   var shape="left=200px, top=100px, width=300px, height=200px";
   
   var popup=open("",title,shape);
   
   //현재페이지에 있는값을 새창으로 옮기는 작업~!
   checkIdDuplicateFrm.userId.value=userId;
   //popup창에서 이 폼을 작동시키게 하는 구문!
   checkIdDuplicateFrm.target=title;
   checkIdDuplicateFrm.action=url;
   checkIdDuplicateFrm.method="post";
   checkIdDuplicateFrm.submit();       --%>

$(document).ready(function(){
   $('#enroll-reset-btn').click(function(){
      clearInput();
   })
});


/* $(function(){
   
   var idValue=id.value;
   $('#id_ck_btn').click(function(){
      console.log(idValue);
      console.log($(this));
       if(!check(re22,id,'첫글자가 영문자 소문자이고 4~12자를 입력해주세요.'))
          {//^[a-z]가 한 자리를 차지하기 때문에 3부터 11까지임
             
              return false;
          }else{
             //alert("사용가능!");
             
             
          }  
   });
     
})   */ 
    
/*     if(name.value=="") {
        alert("이름을 입력해 주세요");
        name.focus();
        return false;
    }
    
    var hint = document.getElementById("hintAnswer");
    var age = document.getElementById("age");
    var phone = document.getElementById("phone");
    var address = document.getElementById("address"); */




/*  $(function(){
   $('#email').blur(function(){
        if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
           email.value='nonono';
            return false;
        }
   })
     
}) */

/*      
    $("#userId_2").blur(function() {
       /* alert("ddddd"); */
  /*       console.log("fFF");
       if(re.test( $("#userId_2").value)) {
               return;
           }
           alert('dfdf');
           id.value = "";
           id.focus(); */
/*         if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")){
            break;
        }  
});
          */
</script>
</body>
</html>