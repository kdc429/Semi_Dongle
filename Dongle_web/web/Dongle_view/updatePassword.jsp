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

  <style>
     div#updatePassword-container{
         font-family: '나눔스퀘어라운드 Regular';
     }
     div#updatePassword-container table {
         margin:0 auto;
         border-spacing: 20px;
     }
     div#updatePassword-container table tr:last-of-type td {
         text-align:center;
     }
     div#updatePassword-container table td{
     	padding-bottom: 5px;
     }
  </style>

</head>
<body>

   <div id="updatePassword-container">
      <form name="updatePwdFrm" action="<%=request.getContextPath()%>/updatePasswordEnd" method="post">
         <table style="margin-top: 30px;">
            <tr>
               <th>현재비밀번호</th>
               <td>
                  <input type="password" name="password" id="password" class="form-control" required/>
               </td>
            </tr>         
            <tr>
               <th style="padding-right: 20px;">변경할 비밀번호</th>
               <td>
                  <input type="password" name="password_new" id="password_new" class="form-control" required/>
               </td>
            </tr>
            <tr>
               <th>비밀번호 확인</th>
               <td>
                  <input type="password" name="password_ck" id="password_ck" class="form-control" required/>
               </td>
            </tr>      
            <tr>
               <td colspan='2' style="padding: 10px 0 0 30px;">
                  <input type="submit" onclick="return password_validate();" class="btn"
                  value="변경"/>
                  &nbsp;&nbsp;&nbsp;
                  <input type="button" onclick="self.close();" class="btn"
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






