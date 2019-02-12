<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%
    String id=request.getParameter("userId"); 
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

   <style>
      div#updatePassword-container
      {
         border-radius: 20px;   
          background:#EAEAEA;
          font-family: '나눔스퀘어라운드 Regular';
      }
      div#updatePassword-container table 
      {
          margin:0 auto;
          border-spacing: 20px;
          font-family: '나눔스퀘어라운드 Regular';
      }
      div#updatePassword-container table tr:last-of-type td 
      {
          text-align:center;
          
      }
   </style>

</head>

<body>
   <div id="updatePassword-container">
      <form name="updatePwdFrm" action="<%=request.getContextPath()%>/updatePasswordEnd" method="post">
         <table>
            <tr>
               <th style="font-family: '나눔스퀘어라운드 Regular';">현재비밀번호</th>
               <td>
                  <input type="password" name="password" id="password" required/>
               </td>
            </tr>         
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
                  <input type="submit" onsubmit="return password_validate();" value="변경"/>
                  &nbsp;&nbsp;&nbsp;
                  <input type="button" onclick="self.close();" value="닫기" />
               </td>
            </tr>
         </table>
         <input type="hidden" name="userId" value='<%=id %>'/>
      </form>
   </div>
   
   <script>
   var re = /^(?=.*[a-zA-Z])(?=.*[0-9]).{4,12}$/

       //비밀번호 정규식
       $('#password_new').on('change paste', (function () {
           if (re.test($(this).val())) {
              console.log('pw성공');
              return true;
           }
           else {
               alert("비밀번호를 확인해주세요. 영문자/숫자 포함 4~12글자  ");
               /* $('#pw_check').html('비밀번호를 확인해주세요.<br><sub>- 영어 대,소문자 그리고 숫자, 특수기호를 <br>- 각각 한글자이상 포함하여 8 ~ 20 글자 </sub>'); */   
              return false;
           }
       }));

       //비밀번호 재확인
       $('#password_ck').on('change paste', (function () {
           if ($('#password_new').val() != $(this).val())
           {
              alert("입력하신 비밀번호가 서로 다릅니다.");
              $('#password_ck').val('');
              $('#password_ck').focus();
           }

       }));
   </script>
</body>
</html>
