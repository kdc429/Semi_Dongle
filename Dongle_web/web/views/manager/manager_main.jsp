<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.Member, com.dongle.group.model.vo.Group,
com.dongle.group.model.vo.MultiLocationName, com.dongle.group.model.vo.MultiTopicName,
com.dongle.group.model.vo.TopicCtg, com.dongle.group.model.vo.LocalCtg,
java.util.*" %>

<%
   int j = 0;
   int groupNo=(int)request.getAttribute("groupNo");
   Member loginMember = (Member)session.getAttribute("loginMember");
   Group g = (Group)request.getAttribute("group");
   List<MultiLocationName> locList = (List)request.getAttribute("locList");
   List<MultiTopicName> topicList = (List)request.getAttribute("topicList");
   List<TopicCtg> topicCtg = (List)request.getAttribute("topicCtg");
   List<LocalCtg> localCtg = (List)request.getAttribute("localCtg");
   
   String address = g.getLocCtgCode();
%>
<meta charset="UTF-8">


<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- jQuery library -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<!-- <script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script>
   var topicCnt = 0;
   var topicArr = [];
   var topicFlag = false;
   
   var locCnt = 0;
   var locArr = [];
   var locFlag = false;
   
   //동글 정보 변경 -> 초기 토픽, 지역 표시
   $(function(){
      $("#change-dongle-btn").click(function(){
         topicArr = [];
         
         topicCnt = 0;
         
         //토픽
         for(var i = 0; i < <%=topicList.size()%>; i++)
         {
            var div = $("#topic-add-div");
            var topicName = "<%=topicList.get(j).getTopicCtgName()%>"
            var topicCode = <%=topicList.get(j).getTopicCtgCode()%>;
            var str = "<span id='topic-span"+topicCnt+"' onclick='fn_delTopic("+topicCnt+")'><a class='aTopic'>"
            +topicName+"&nbsp&times</a>"+"<input type='hidden' name='topicCode' value='"+ topicCode +"'>"
            +"<input type='hidden' name='topicName' value='"+topicName+"'></a></span>";
               
            div.html(str);
            
            topicArr.push(topicName);
            
            console.log("topicArr추가" + topicArr);
            topicCnt++; 
            <%j++;%>
         }
         <%j = 0;%>
         
      })
   })
   //토픽 추가
   $(function(){
      $("#addtopic-btn").click(function(){
         if(topicCnt > 2)
         {
            alert("토픽은 최대 3개만 가능합니다.")   
         }
         else
         {
            var topicCode = $("#selectTopic option:selected").val();
            var topicName = $("#selectTopic option:selected").text();
            
            for(var i = 0; i < topicArr.length; i++)
            {
               if(topicArr[i] == topicName)
               {
                  topicFlag = true;
               }
               
            }
            if(!topicFlag)
            {
               var div = $("#topic-add-div");
               var str = "<span id='topic-span"+topicCnt+"' onclick='fn_delTopic("+topicCnt+")'><a class='aTopic'>"
               +topicName+"&nbsp&times</a>"+"<input type='hidden' name='topicCode' value='"+topicCode+"'>"
               +"<input type='hidden' name='topicName' value='"+topicName+"'></a></span>";
                  
               div.append(str);
               
               topicArr.push(topicName);
               console.log("topicArr추가" + topicArr);
               topicCnt++; 
               
            }
            else
            {
               alert("같은 토픽은 선택할 수 없습니다.");
               topicFlag = false;
            }
            
         }
      });
   });
   
   //토픽 삭제
   function fn_delTopic(index)
   {
      var spanId = "#topic-span" + index;
      
      $(spanId).remove();
      topicCnt--;
      
      
      topicArr.splice(index, 1);
      console.log("topicArr삭제" + topicArr);
      
   }
   
   
   //지역 대분류 변경
   function fn_metroChange()
   {
      var metroCode = $("#selectMetro").val();
      var html = "";
   
         $.ajax({
         url:"<%=request.getContextPath()%>/manager/compareMetro",
         type:"post",
         data:{
           "metroCode":metroCode
         },
         dataType:"html",
         success:function(data){
            console.log(data);
               $("#selectArea").html(data);
               $("#selectTown").html("<option disabled selected>==소분류==</option>");
         }, 
         error:function(request){},
         complete:function(){console.log("ok");}
         })
   }
   
   //지역 중분류 변경
   function fn_areaChange()
   {
      var areaCode = $("#selectArea").val();
      var html = "";
   
         $.ajax({
         url:"<%=request.getContextPath()%>/manager/compareArea",
         type:"post",
         data:{
           "areaCode":areaCode
         },
         dataType:"html",
         success:function(data){
            console.log("데이타 : " + data);
               $("#selectTown").html(data);
         }, 
         error:function(request){},
         complete:function(){console.log("ok");}
         })
   }
   
   //지역 추가
   $(function(){
      $("#addLoc-btn").click(function(){
         if(locCnt > 2)
         {
            alert("지역은 최대 3개만 가능합니다.")   
         }
         else
         {
            var metroCode = $("#selectMetro option:selected").val();
            var metroName = $("#selectMetro option:selected").text();
            var areaCode = $("#selectArea option:selected").val();
            var areaName = $("#selectArea option:selected").text();
            var townCode = $("#selectTown option:selected").val();
            var townName = $("#selectTown option:selected").text();
            
            if(areaCode == "==중분류==")
            {
               areaCode = "";
               areaName = "";
            }
            if(townCode == "==소분류==")
            {
               townCode="";
               townName="";
            }
            
            for(var i = 0; i < locArr.length; i++)
            {
               if(townName == "")
               {
                  if(areaName == "")
                  {
                     if(locArr[i] == metroName)
                     {
                        locFlag = true;   
                        console.log("세종시");
                        
                     }
                     
                  }
                  else
                  {
                     if(locArr[i] == areaName)
                     {
                        locFlag = true;   
                     }
                  }
               }
               else
               {
                  if(locArr[i] == townName)
                  {
                     locFlag = true;
                  }
               }
               
               
            }
            
            if(!locFlag)
            {
               var name = metroName + " " + areaName + " " + townName;
               var div = $("#local-add-div");
               var str = 
               "<span id='local-span"+locCnt+"' onclick='fn_delLocal("+locCnt+")'>"
               +"<a class='aLocal'>"+name+ "&nbsp&times</a>"
               +"<input type='hidden' name='metroCode' value='"+metroCode+"'>"
               +"<input type='hidden' name='metroName' value='"+metroName+"'>"
               +"<input type='hidden' name='areaCode' value='"+areaCode+"'>"
               +"<input type='hidden' name='areaName' value='"+areaName+"'>"
               +"<input type='hidden' name='townCode' value='"+townCode+"'>"
               +"<input type='hidden' name='townName' value='"+townName+"'></a></span>";
                  
               div.append(str);
               
               if(townName == "")
               {
                  if(areaName == "")
                  {
                     locArr.push(metroName);
                  }
                  else
                  {
                     locArr.push(areaName);
                     
                  }
                  
               }
               else
               {
                  locArr.push(townName);
               }
               
               locCnt++; 
               
            }
            else
            {
               alert("같은 지역은 선택할 수 없습니다.");
               locFlag = false;
            }
            
         }
      });
   });
   
   //지역 삭제
   function fn_delLocal(index)
   {
      var spanId = "#local-span" + index;
      
      $(spanId).remove();
      locCnt--;
      console.log(locArr);
      locArr.splice(index,1);
      
   }
   
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
   
   function validate(){
      console.log($("#checkPwd").val()+" : <%=loginMember.getMemberPwd()%>");
      if($("#checkPwd").val() != '<%=loginMember.getMemberPwd()%>')
      {
         alert("비밀번호가 일치하지 않습니다.");
         return;   
      }
      modelFrm.submit();
   }
   
   
</script>
<style>
   div.manager-container{
      padding-left:45px;
   }
   input#address{display:"inline-block"}
   a.aTopic{padding-right:15px;}
   a.aLocal{padding-right:15px; font-size:9pt;}
   .image_p{
            border: 1px solid rgb(230,230,230);
            width: 120px;
            height: 120px;
            border-radius: 100px;
            margin-left: 180px;
           
    }
   #upfile{
          
           
            font-family: "netmarble Medium";
    }
    /*들어가는 이미지 style*/
    .selProductFile{
            width: 120px;
            height: 120px;
            border-radius: 100px;
           /*  margin-left:2px;
            margin-top:1px; */
    }
</style>
<div class="manager-container">
   
   <h2 id="set-head">동글 관리자 페이지</h2>

   <div class="panel panel-default" style="margin-top: 5%; width: 600px">
      <div class="panel-heading">동글 관리</div>
      <div class="panel-body">
         <a data-toggle="modal" data-target="#change-dongle-modal" id="change-dongle-btn">동글 정보
            수정</a>
      </div>
      <div class="modal fade"></div>
      <div class="panel-heading">동글 회원관리</div>
      <div class="panel-body"><a id="delete-member-btn">회원 탈퇴 관리</a></div>
      <div class="panel-body"><a id="report-member-btn">신고 회원 관리</a></div>
   </div>
   <div class="panel panel-default" style="width: 600px">
      <div class="panel-body">
         <a style="color: red; font-style: italic;" data-toggle="modal"
            data-target="#remove-dongle-modal">동글 삭제</a>
      </div>
   </div>


   <!-- 동글 정보 수정창(modal) -->
   <div class="modal fade" id="change-dongle-modal" role="dialog">
      <div class="modal-dialog">


         <div class="modal-content">
            <div class="modal-header" style="padding: 35px 50px;">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h3>
                  <span class="glyphicon glyphicon-cog"></span> 동글 정보 수정
               </h3>
            </div>
            <form action="<%=request.getContextPath() %>/manager/updateDongle?groupNo=<%=groupNo %>" method="post" enctype="multipart/form-data">
            <div class="modal-body" style="padding: 40px 50px;">
               
                  <div class="form-group">
                     <input type="hidden" name="groupNo" value="<%=groupNo %>"/>
                     <div class="image_p"></div>
                     <label>이미지</label>
                          <input type="file" id="upfile" class="upfile" name="upfile" ><br>
                     <label for="donglename">동호회 이름</label> 
                        <input type="text" class="form-control" name="dongleName"id="dongleName" value="<%=g.getGroupName()%>"> <br>
                     <label for="topic">토픽</label><br> 
                     <select   class="form-control" style="padding_bottm:2px; width:79%; display:inline" name="selectTopic" id="selectTopic" >
                        <option disabled selected>===선택===</option>
                        <%for(TopicCtg topic : topicCtg){ %>
                        <option value="<%=topic.getTopicCtgCode()%>"><%=topic.getTopicCtgName() %></option>
                        <%} %>
                        
                     </select>
                     <button type="button" class="btn btn-info" id="addtopic-btn" style="width:20%; height:34px;">토픽 추가</button>
                     <br>
                     <div id="topic-add-div" style="height:19px">
                           
                     </div>
                     <br>
                     <label>지역</label><br> 
                     
                     <select   class="form-control" style="padding_bottm:2px; width:25.6%; display:inline" name="selectMetro" id="selectMetro" onchange="fn_metroChange()" >
                        <option disabled selected>==대분류==</option>
                        <%
                        String[] metroArr = new String[18];
                        int metroCnt = 0;
                        boolean flag = false;
                        for(LocalCtg local : localCtg){ 
                           for(int i = 0; i < metroCnt; i++)
                           {
                              if(metroArr[i].equals(local.getMetroCode()))
                              {
                                 flag = true;
                                 break;
                              }
                           }
                           if(!flag)
                           {
                              metroArr[metroCnt] = local.getMetroCode();   
                              metroCnt++;
                              %>
                                 <option value="<%=local.getMetroCode()%>"><%=local.getLocMetroName() %></option>
                              <%
                           }
                           else
                           {
                              flag = false;
                           }
                        }
                        %>
                        
                     </select>
                     <select class="form-control" style="padding_bottm:2px; width:25.6%; display:inline" name="selectArea" id="selectArea" onchange="fn_areaChange()">
                        <option disabled selected>==중분류==</option>
                        
                     </select>
                     <select class="form-control" style="padding_bottm:2px; width:25.6%; display:inline" name="selectTown" id="selectTown" >
                        <option disabled selected>==소분류==</option>
                        
                     </select>
                     <button type="button" class="btn btn-info" id="addLoc-btn" style="width:20%; height:34px;">지역 추가</button>
                     <br> 
                     <div id="local-add-div" style="height:19px">
                           
                     </div>
                     <br>
                     
                     <label>활동 시간대</label><br>
                     <div class="radio">
                        <label class="radio-inline">
                           <input type="radio" name="activetime" value="주중" <%=g.getGroupDateCtg().equals("주중")?"checked":"" %>>주중
                        </label> 
                        <label class="radio-inline">
                           <input type="radio" name="activetime" value="주말" <%=g.getGroupDateCtg().equals("주말")?"checked":"" %>>주말
                        </label> 
                     </div>
                     <br>
                     <label for>연령대</label><br>
                     
                        <input type="number" class="form-control" style="width:20%; display:inline;"name="minAge" id="minAge" min="1" max="100" value=<%=g.getMinAge() %> placeholder="최소">
                     
                     &nbsp~&nbsp
                     
                        <input type="number" class="form-control" style="width:20%; display:inline;" name="maxAge" id="maxAge" min="1" max="100" value=<%=g.getMaxAge() %> placeholder="최대">
                     
                     <br><br><br>
                     
                     <label>소개글</label>
                     <textarea class="form-control" rows="5" name="intro" id="intro" wrap><%=g.getGroupIntro() %></textarea>
                  </div>


               
            </div>
            <div class="modal-footer">
               <button type="submit" class="btn btn-default" >완료</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

            </div>
            </form>
         </div>

      </div>
   </div>



   <!-- 동글 삭제 확인창(modal) -->
   <div class="modal fade" id="remove-dongle-modal" role="dialog">
      <div class="modal-dialog modal-sm">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title">동글 삭제</h4>
            </div>
            <div class="modal-body">
               <p>정말로 동글을 삭제하시겠습니까?</p>
               <p>삭제한 동글은 복구가 불가능합니다.</p>
               <br>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default"  data-toggle="modal" data-target="#check-modal"data-dismiss="modal">예</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">아니오</button>
            </div>
         </div>
      </div>
   </div>
   
   <!-- 매니저 비밀번호 확인창(modal) -->
   <div class="modal fade" id="check-modal" role="dialog">
      <div class="modal-dialog modal-sm">
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title">비밀번호 확인</h4>
            </div>
            <form name='modelFrm' action="<%=request.getContextPath() %>/manager/deleteDongle" method="post">
               <div class="modal-body">
                  <label for="checkPwd">비밀번호 입력</label><br>
                  <input type="password" class="form-control" name="checkPwd" id="checkPwd">
                  
                  <input type="hidden" name="groupNo" value="<%=groupNo%>"/>
                  <input type="hidden" name="memberPwd" value="<%=loginMember.getMemberPwd() %>"/>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-default" onclick="validate()">예</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">아니오</button>
               </div>
            </form>
         </div>
      </div>
   </div>
</div>


<script>

/* 신고관리버튼 */
$(function(){
   $("#report-member-btn").click(function(){
      var managerNo=<%=g.getMemberNo()%>;
      
      console.log(<%=g.getMemberNo()%>);
      $.ajax({
         url:"<%=request.getContextPath()%>/manager/reportMemberView?groupNo=<%=g.getGroupNo()%>",
         type:"post",
         data:{
            "managerNo":managerNo
         },
         dataType:"html",
         success:function(data){
            $('#content-div').html(data);
         },
         error:function(request){},
         complete:function(){console.log("ok");}
      })
   })
});

/* 멤버탈퇴관리버튼 */
$(function(){
   $("#delete-member-btn").click(function(){
      var managerNo=<%=g.getMemberNo()%>;
      
      console.log("매니저넘버" + <%=g.getMemberNo()%>);
      $.ajax({
         url:"<%=request.getContextPath()%>/manager/deleteMemberView?groupNo=<%=g.getGroupNo()%>",
         type:"post",
         data:{
            "managerNo":managerNo
         },
         dataType:"html",
         success:function(data){
            $('#content-div').html(data);
         },
         error:function(request){},
         complete:function(){console.log("ok");}
      })
   })
});

//동글 정보 변경 연령 10단위 이벤트
$(function() {
   $('#minAge').on('change', function() {
      var n = $(this).val(); 
      if(n > 100 || n < 0)
      {
         alert("0~100 사이로 입력하세요.")
         $(this).val(0);
         $(this).focus();
      }
      else
      {
         n = Math.floor(n/10) * 10; 
         $(this).val(n);
         
      }
      
   });
});

$(function() {
   $('#maxAge').on('change', function() {
      var n = $(this).val(); 
      if(n > 100 || n < 0)
      {
         alert("0~100 사이로 입력하세요.")
         $(this).val(0);
         $(this).focus();
      }
      else
      {
         n = Math.floor(n/10) * 10; 
         $(this).val(n);
         
      }
   });
});
//동글 메인 이미지 변경
$(function(){
	$('#update-main-image').click(function(){
		var groupNo=<%=groupNo%>
		$.ajax({
			url:"<%=request.getContextPath()%>/dongle/updateMain%>",
			data:{"groupNo":groupNo},
			type:'post',
			success:function(data){
				$('#content-div').html(data);
			};
		});
	});
})

</script>