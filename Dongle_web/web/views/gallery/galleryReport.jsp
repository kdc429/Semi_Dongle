<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>동글 멤버</title>
</head>
<body>
<script>
   
      window.onload=function(){
         
      var reportFrm=window.opener.document.getElementById('reportFrm');
      console.log(reportFrm);
      var reNickName=reportFrm.reportNickName.value;
      var reCode1=reportFrm.report1.value;
      var reCode2=reportFrm.report2.value;
      var reCode3=reportFrm.report3.value;
      var reCode4=reportFrm.report4.value;
      var reCode5=reportFrm.report5.value;
      var reCode6=reportFrm.report6.value;
      var reCode7=reportFrm.report7.value;
      var reason1=reportFrm.reason1.value;
      var reason2=reportFrm.reason2.value;
      var reason3=reportFrm.reason3.value;
      var reason4=reportFrm.reason4.value;
      var reason5=reportFrm.reason5.value;
      var reason6=reportFrm.reason6.value;
      var reason7=reportFrm.reason7.value;
      console.log(reNickName);
      document.getElementById('reportMemberNick').innerHTML=reNickName;
      document.getElementById('reportNo1').value=reCode1;
      document.getElementById('reportNo1').innerHTML=reason1;
      document.getElementById('reportNo2').value=reCode2;
      document.getElementById('reportNo2').innerHTML=reason2;
      document.getElementById('reportNo3').value=reCode3;
      document.getElementById('reportNo3').innerHTML=reason3;
      document.getElementById('reportNo4').value=reCode4;
      document.getElementById('reportNo4').innerHTML=reason4;
      document.getElementById('reportNo5').value=reCode5;
      document.getElementById('reportNo5').innerHTML=reason5;
      document.getElementById('reportNo6').value=reCode6;
      document.getElementById('reportNo6').innerHTML=reason6;
      document.getElementById('reportNo7').value=reCode7;
      document.getElementById('reportNo7').innerHTML=reason7;
      
   }
   
    $(document).ready(function(){
  		$('#report-reason').on('change',function(){
  			console.log($(this).val());
  			var reportCode=$('#report-reason option:selected').val();
  			window.opener.document.getElementById('selectRecode').value=reportCode;
  			report();
  		});
  	})
   
   $(function(){
      
      $('.report-close').click(function(){
         var galNo=window.opener.document.getElementById('reportGalNo').value;
         var galCommentNo;
         if(window.opener.document.getElementById('reportCommentNo').value!=null){
         	galCommentNo=window.opener.document.getElementById('reportCommentNo').value;
         }
         var groupNo=window.opener.document.getElementById('reportGroupNo').value;
         var memberNo=window.opener.document.getElementById('reportMemberNo').value;
         var reportCode=$('#report-reason option:selected').val();
         var albumCode=window.opener.document.getElementById('reportAlbumCode').value;
         console.log(galCommentNo);
         if(!confirm('정말로 신고하시겠습니까?')){return;}
         {
        	 if(galCommentNo!=null){
 	            $.ajax({
 	               url:"<%=request.getContextPath()%>/gallery/galleryCommentReport",
 	               type:"post",
 	               data:{
 	            	  'galNo':galNo,
 	                  "groupNo":groupNo,
 	                  "memberNo":memberNo,
 	                  "reportCode":reportCode,
 	                  "galCommentNo":galCommentNo
 	               },
 	               success:function(data){
 	                	 alert("Message: "+data);
 	                     
 	                     $.ajax({
 	                        url:"<%=request.getContextPath()%>/gallery/galleryGet",
 	                        type:"post",
 	                        data:{
 	                           "groupNo":groupNo,
 	                           "memberNo":memberNo,
 	                           "albumCode":albumCode
 	                        },
 	                        
 	                        dataType:"html",
 	                       dataType:"html",
	                        success:function(data){
	                        	$(opener.document).find('#content-div').html(data);
	                        	$(opener.document).find('#modal-container').css('display','none');
	                           self.close();
	                        }
 	                        
 	                     });
 	               }
 	            })
        	 }
        	 else if(galCommentNo==null&&galNo!=0){
	            $.ajax({
	               url:"<%=request.getContextPath()%>/gallery/galleryReport",
	               type:"post",
	               data:{
	            	   'galNo':galNo,
	                  "groupNo":groupNo,
	                  "memberNo":memberNo,
	                  "reportCode":reportCode,
	                  "albumCode":albumCode
	               },
	               success:function(data){
	                     alert("Message: "+data);
	                     
	                     $.ajax({
	                        url:"<%=request.getContextPath()%>/gallery/galleryGet",
	                        type:"post",
	                        data:{
	                           "groupNo":groupNo,
	                           "memberNo":memberNo,
	                           "albumCode":albumCode
	                        },
	                        
	                        dataType:"html",
	                        success:function(data){
	                        	$(opener.document).find('#content-div').html(data);
	                        	$(opener.document).find('#modal-container').css('display','none');
	                           self.close();
	                        }
	                        
	                     });
	               }
	            })
	         }else{
	        	 alert("신고 실패 하였습니다. 다시 시도해주세요.")
	        	 self.close();
	         } 
         } 
      })
   })
   
</script>
   <div>
      <h2>신고 접수</h2>
   </div>
   <div>
      <div name="reportTarget">
         <span>신고대상</span>
         
         <span id="reportMemberNick" name="reportMemberNick"></span>
      </div>
      <div>
         <label>신고 사유
            <select id="report-reason" name="reportReason">
               
               <option id="reportNo1" name="reportNo1" value=""></option>
               <option id="reportNo2" name="reportNo2" value=""></option>
               <option id="reportNo3" name="reportNo3" value=""></option>
               <option id="reportNo4" name="reportNo4" value=""></option>
               <option id="reportNo5" name="reportNo5" value=""></option>
               <option id="reportNo6" name="reportNo6" value=""></option>
               <option id="reportNo7" name="reportNo7" value=""></option>
            </select>
         </label>
      </div>
   </div>
   <div>
      <button class="report-close">신고</button>
   </div>
</body>
</html>