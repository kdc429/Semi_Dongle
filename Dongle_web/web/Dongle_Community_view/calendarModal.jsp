
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.*,com.dongle.calender.model.vo.*" %>
<%
	int groupNo=(int)request.getAttribute("groupNo");	

/* 	Calendar m =(Calendar)request.getAttribute("Calendar");
	String calcontent = m.getCalcontent();
	String caltitle = m.getCaltitle();
	int remaincost = m.getRemaincost();
	int totalcost =  m.getTotalcost();
	int usecost =  m.getUsecost(); */
	
	
%>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<script>
$(function(){
   //선택한 이미지로 모달띄우기
   var modal = document.getElementById('modal-container');
   var cal=$('#calendar');
   console.log(cal);
   var X=document.body.scrollLeft;
   var Y=cal.offset().top;
   $('#calendar_content #tt').click(function(obj){
      console.log(obj);
      modal.style.left=X+"px";
      modal.style.top=Y+"px";
      modal.style.display="block";
   });
   $('.close').click(function(){
      modal.style.display="none";
   });
   window.onclick = function(event){
      if(event.target==modal)
      {
         modal.style.display="none";
      }
   }
});
</script>
<style>
     .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
    .dialog{
      display:none;
      position:absolute;
      margin-top:-250px;
      z-index:20;
      left:0;                                                                                                   
      right:0;
      width:100%;
      /* height:100%; */
      overflow:hidden;
      background-color:rgb(0,0,0);
      background-color:rgba(0,0,0,0.4);
   }
   .modal-content {
        background-color: #fefefe;
        margin: 15% auto; 
        padding: 20px;
        border: 1px solid #888;
        width: 30%;  
        border-radius: 5px;
    }
    
    .selProductFile{
            width: 300px;
            height: 500px;
        }
</style>

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h3><span id="modal_result" class="modal-title" ></span></h3>
        </div>
          <div class="modal-body">
            
            
<!--             <h4>*회비 내역</h4>
            <span id="before_total"></span>
            <br>
                  모은 총 회비 : <span id="cast1" ></span>
            <br>
                  총 사용 금액 : <span id="cast2" ></span>
            <br>
                  남은 회비 : <span id="cast3" ></span> 
              <br>
              <h4>*일정 내용입니다.</h4>
              <br>
              <div></div>
              <br>
              <span id="cal_content_list"></span>
              <br>
              <button id="reciptview" >영수증보기</button>
              <div class="receipt">
              
              </div> -->
              

                                
         <!-- <img src="http://image.kmib.co.kr/online_image/2018/0114/611211110012048428_1.jpg"> -->

<%-- <form action="<%=request.getContextPath() %>/calendar/calendarFormEnd" method="post" enctype="multipart/form-data"> --%>
         <!-- manager 권한 -->
         
      
            <div id="manager">
                <hr>
            
                modal-title : <input type="text" id="caltitle" name="caltitle">
                <br>
                모은 총 회비 : <input type="text" name="totalcost" id="totalcost">
                <br>
               총 사용 금액 : <input type="text" name="usecost" id="usecost">
                <br>   
                <input type="date" id="today1" name="today1" min="2019-01-01"/>
                <input type="time" id="today2" name="today2">
                <br>
               일정에 대한 내용 입력 하십시오.
                <br>
                <textarea name="calcontent" id="calcontent" cols="30" rows="10"></textarea>
                <br>
                <input type="file" id="upfile" class="upfile" name="upfile">
            </div>
         </div>
         <div class="modal-footer">
            <input type="submit" id="insert-btn" value="등록하기" onclick="return validate();" />
            <input type="hidden" name="groupNo" value=1/> <!-- //groupNo받아오기 -->
    <!--         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
         </div>
         
	<script>
			
 	        //남은 회비
	        var betal = 1000;
	        $('#before_total').html('남은 회비(전) :' + betal);
	
	        //영수증 보기
	        function rec() {
	            $('#receipt').slideToggle();
	            
	            
	            
	        }
	
	        //영수증 업로드
	        /* function validate() {
	            var content = $('[name=content]').val();
	        }
	         */
	        
	        
	        
	        //form대신 ajax로 보내기! 
	        
	        $(document).ready(function(){
	        	
	        	 $('#insert-btn').click(function(){
	 	        	var caltitle = $('#caltitle').val();
	 	        	console.log(caltitle);
	 	        	var groupNo=<%=groupNo%>;
	 				var today1 = $('#today1').val();
	 				var today2 = $('#today2').val();
	 				var calcontent = $('#calcontent').val();
	 				var upfile = document.getElementById('upfile').files[0];
	 				var totalcost = $('#totalcost').val();
	 				var usecost = $('#usecost').val();
	 	        	var fd=new FormData();
	 	        	fd.append('caltitle',caltitle);
	 	        	fd.append('groupNo',groupNo);
	 	        	fd.append('today1',today1);
	 	        	fd.append('today2',today2);
	 	        	fd.append('calcontent',calcontent);
	 	        	fd.append('upfile',upfile);
	 	        	fd.append('totalcost',totalcost);
	 	        	fd.append('usecost',usecost);
	 	        	
	 	        	$.ajax({
	 	        		url:"<%=request.getContextPath()%>/calendar/calendarFormEnd",
	 	        		type:"post",
	 	        		data:fd,
	 	        		dataType:"html",
	 	        		processData:false,
	 	        		contentType:false,
	 	        		success:function(data){
	 	        			console.log(data);
	 	        		}
	 	        	})
	 	        	
	 	        }); 
	        })
	       
	         
	        
<%-- 	        $.ajax({
	        		url:"<%=request.getContextPath()%>/calendar/calendarFormEnd",
	        		type:"post",
	        		data:{
	        			"totalcost":totalcost,
	        			},
	        		dataType:"html",
	        		success:function(data){
	        			$('#cast1').html(data);
	        		}
	        		})
	        })  --%>
	        
	</script>