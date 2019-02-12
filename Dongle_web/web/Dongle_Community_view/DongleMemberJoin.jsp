<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <meta charset="UTF-8">

<%@ page import="com.dongle.member.model.vo.Member" %>

<%
   int groupNo=(int)request.getAttribute("groupNo");
   Member loginMember = (Member)session.getAttribute("loginMember");
%>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
#dongle_title h4{color:rgb(20,150,200);float:left;margin-left:20px;margin-top:70px;position:relative;display: block;}
#dongle_join {
   width: 500px;
    height: 400px;
    margin-left: 100px;
   position:relative;
   margin-top:120px;
   display: block;
   font-family: "여기어때잘난서체";
}

.image_p {
   border: 1px solid rgb(230,230,230);
   width: 150px;
   height: 150px;
   border-radius: 100px;
    margin-left: 155px;
    margin-top: 50px;
}


#nickname {
   width: 180px;
     height: 30px;
     position: relative;
     left: 45px;
}

.subm {
   position: absolute;
   left: 315px;
   top: 500px;
}

#upfile {
    padding-top: 10px;
    font-family: "netmarble Medium";
    display: none;
}

#dongle_title {
   font-size: 40px;
    margin-left: 260px;
}
/* 등러가는 이미지 style 클래스명 */
.selProductFile {
   width: 145px;
    height: 145px;
    border-radius: 100px;
    margin-left:2px;
    margin-top:1px;
}

#nickbox {
   display: inline-block;
}

#id_check{
   padding-left: 150px;
}

.emm1{
   margin-top: 20px;
}

.emm2{
   margin-top: 20px;
}

#file-btn{
   margin-left: 195px;
   margin-top: 10px;
}



</style>


<script>    
       var re = /^[가-힣]{2,7}$/ // 닉네임 정규식 표현

        var id = document.getElementById("nickname");

        $(function(){
          $('#nickname').on('change keyup paste',(function(){
               console.log("dd");
               var idTest=re.test($('#nickname').val());
               console.log(idTest);
               console.log($('#nickname').val());
                if($('#nickname').val()==""){//아이디 빈칸일때
                     $('#id_check').html("");
                  }else{
                     
                     if(!idTest)
                     {//^[a-z]가 한 자리를 차지하기 때문에 3부터 11까지임
                        $('#id_check').html("한글로 2~7자를 입력해주세요.").css('color', 'red');
                        return;
                     }else{
                        
                        $.ajax({
                              url:'<%=request.getContextPath()%>/checkIdDuplicate',
                              type:"POST",
                              data:{"userId":$('#nickname').val()},
                              success:function(data){
                                 console.log(data);
                                 if(data){                                   
                                    $('#id_check').html("사용 가능한 닉네임입니다.").css('color', 'green');               
                                 }
                                 else if(!data){                                    
                                    $('#id_check').html("사용 불가능한 닉네임입니다.").css('color', 'red');         
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
        
        $(document).on('click','#file-btn',function(){
           
           $('#upfile').click();
        })
   
   </script>


<div id="dongle_title">
   <h4><b>동글 가입하기</b></h4>
</div>
   <form name='dongleMemberUpdate' action="<%=request.getContextPath()%>/donglememberjoin" method="post" enctype="multipart/form-data">

           <div id="dongle_join">
               <div class="image_p"></div>
               <button type="button" id="file-btn">사진등록</button>
               <input type="file" id="upfile" class="upfile" name="upfile" >
               <div class="form-group">
                   <input type="text" class="form-control emm1" name="nickname" id="nickname" placeholder="닉네임" style="font-family:'나눔스퀘어라운드 Regular'; display:inline; margin-left:100px;" required>&nbsp;&nbsp;&nbsp;&nbsp;
                   <!-- <button style="width:70px; height:30px;" class="btn btn-default" type="button" onclick="fn_checkduplicate();">중복검사</button><br/><br/> -->
                   <div id="id_check"style='width:auto; height:auto;'></div>
                   <button style="width:268px; height:30px; margin-left:100px;"class="btn btn-primary emm2" type="submit" onclick="return password_validate();">동글 가입</button>
                 </div>
                 <div class="form-group">
                    <input type='hidden' name="idValid" value="0"/> 
                    <input type='hidden' name="groupNo" value="<%=groupNo%>"/> 
                    <input type='hidden' name="memberNo" value="<%=loginMember.getMemberNo()%>"/>
                 </div>
                   <%-- <table id="nickbox">
                       <tr>
                           <th style="width:100px;">닉네임 </th>
                           <td>

                               <input type='hidden' name="idValid" value="0"/> 
                               <input type='hidden' name="groupNo" value="<%=groupNo%>"/> 
                               <input type='hidden' name="memberNo" value="<%=loginMember.getMemberNo()%>"/> 
                           </td>
                           <td colspan='2'>
                               <button class="btn btn-default" type="submit" onclick="return password_validate();">가입</button>
                           </td>
                       </tr>
                       <tr class="subm"> --%>
                         <!--   <td colspan='2'>
                               
                           </td>
                       </tr>
                   </table> -->
               </div>
   </form>
   <form action="" name="checkdongleDuplicateFrm">
         <input type="hidden" name="nickname"/>
      </form>   
   
   
<%--    <script>
   
   $('#upfile').click(function() {
   
      var upfile = $('#upfile').val();
      
       $.ajax({
         url:"<%=request.getContextPath()%>/donglememberjoin",
         type:"post",
         data:{
            "upfile":upfile,
            },
         dataType:"html",
         success:function(data){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
            $('#image_p').html(data);
         }
         })
      })
   </script> --%>
   
   
   <script type='text/javascript'>
      //이미지 정보들을 담을 배열 선언
      var sel_files = [];
      
      $(document).ready(function(){
         $('#upfile').on('change', handleImgFileSelect);
      });
      
      function fileUploadAction(){
         console.log('fileUploadAction');
         $('#upfile').trigger('click');
      }
      
      function handleImgFileSelect(e){
         //이미지 정보들을 초기화
      sel_files = [];
         $('.image_p').empty();
         var files=e.target.files;
         var filesArr=Array.prototype.slice.call(files);
         
         var index = 0;
         filesArr.forEach(function(f){
            if(!f.type.match("image.*")){
               alert('확장자는 이미지 확장자만 가능합니다.');
               return;
            }
            sel_files.push(f);
            var reader = new FileReader();
            reader.onload=function(e){
               var html = "<img src='"+e.target.result+"' data-file='"+f.name+"' class='selProductFile' name='selProductFile' title='프로필이미지'>";
               $(".image_p").append(html);
               index++;
            }
            reader.readAsDataURL(f);
            
            
         })
      }

   </script>