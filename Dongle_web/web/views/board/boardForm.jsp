<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="com.dongle.board.model.vo.Board,com.dongle.member.model.vo.Member"%>
<%
   int groupNo = (int)request.getAttribute("groupNo");
   Member loginMember = (Member)request.getSession().getAttribute("loginMember");
%>
   <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
<style>
   .table
   {
       text-align : center;
   }
   .table thead th
   {
      background-color : rgba(245,245,245);
       text-align: center; 
   }
   .table tbody tr th 
   {
      background-color : rgba(245,245,245);
       text-align : center; 
   }
   .table tbody td
   {
      text-align: left;
      
   } 
   .form-btn
   {
      text-align : center;
   }
   #form-writer
   {
      width : 250px;
   }
   #form-title
   {
      width : 250px;
   }
</style>
</head>
<body>
   <div class="board-container">
      <form name="insertFrm" method="post" enctype="multipart/form-data">
         <table class="table">
            <thead>
               <br><br>
               <tr>
                  <th colspan="3" style="border-top:1px solid #dddddd; background-color: rgb(20,150,200); font-family: 'netmarble Medium';">공지사항</th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <th style="vertical-align:middle; width: 20%; background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">글 제목</th>
                  <td id="title">
                     <input type="text" name='title' id='form-title' required="required"/>
                  </td>
               </tr>
               <tr>
                  <th style="vertical-align:middle; background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">작성자</th>
                  <td id="writer" >
                     <input style="background-color:#EAEAEA" type="text" name='writer' id='form-writer' value="admin" readonly="readonly"/>
                  </td>
               </tr>               
               <tr>
                  <th style="vertical-align:middle; background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">첨부파일</th>
                  <td>
                     <input type="file" name="upfile"/>
                  </td>
               </tr>
               <tr>
                  <th style="border-bottom:1px solid #dddddd; vertical-align:middle;; background-color:rgba(245,245,245); color:black; font-family: '나눔스퀘어라운드 Regular';">내용</th>
                  <td>
                     <textarea style="width: 540px" rows="5" cols="50" id='form-content' name='content'></textarea>
                  </td>
               </tr>
            </tbody>    
         </table>
      </form>
      <div class="form-btn">
      <button type="button" class="btn btn-default" id='form-list-btn'>목록으로</button>
      <input type="hidden" value="<%=groupNo%>" name="groupNo" name="groupNo"/>
      <button type="button" class="btn btn-default" id='form-submit-btn'>등록하기</button>
      </div>
   </div>
<script>
$(function(){
   $('#form-list-btn').click(function(){
      $.ajax({
         url:"<%=request.getContextPath() %>/board/boardList?groupNo=<%=groupNo%>",
         type:"post",
         dataType:"html",
         success:function(data){
            $('#board-container').html(data);
         },
         error:function(error,msg){console.log("---"+error+msg);}
      });
   });
});

$(function(){
   $('#form-submit-btn').click(function(){
      var content=$('[name=content]').val();
      if(content.trim().length==0)
      {
         alert("내용을 입력하세요!");
         return false;
      }
      var data = new FormData();
      data.append('upfile',insertFrm.upfile.files[0]);
      data.append('groupNo',<%=groupNo%>);
      data.append('writer',$('#form-writer').val());
      data.append('content',$('#form-content').val());
      data.append('title',$('#form-title').val());
      data.append('loginMember','<%=loginMember.getMemberId()%>');
      $.ajax({
         url:"<%=request.getContextPath() %>/board/boardFormEnd",
         data:data,
         type:"post",
         dataType:"html",
         processData:false,
         contentType:false,
         success:function(data){
            alert(data);
            $.ajax({
               url:"<%=request.getContextPath()%>/board/boardList?groupNo=<%=groupNo%>",
               type:"post",
               dataType:"html",
               success:function(data){
                  $('#content-div').html(data);
               }
            });
         }
      });
   });
});


</script>
      