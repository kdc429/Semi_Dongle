<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ page import="java.util.*,com.dongle.group.model.vo.*, com.dongle.member.model.vo.Member,
com.dongle.board.model.vo.Board,com.dongle.gallery.model.vo.*,com.dongle.member.model.vo.Member" %>

<%

   Group g = (Group)request.getAttribute("group");
   List<MultiLocation> locList = (List)request.getAttribute("locList");
   List<MultiTopic> topicList = (List)request.getAttribute("topicList");
   Member loginMember = (Member)request.getAttribute("loginMember");
   GroupMember gm = (GroupMember)request.getAttribute("groupMember");
   int result = (int)request.getAttribute("result");
   int groupNo = (int)request.getAttribute("groupNo");
   List<GalleryPath> galList = (List)request.getAttribute("galList");
   List<EditPickGroup> editList=(List)request.getAttribute("editList");
   //List<Board> list=(List)request.getAttribute("list");
   int editnum=0;

%>
<!DOCTYPE html>
<link rel='icon' href='https://i.imgur.com/8k8yVjE.png'>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Bungee" rel="stylesheet">
   <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
   <link href="<%=request.getContextPath() %>/css/Dongle_Community.css" rel="stylesheet">
   <link href="<%=request.getContextPath()%>/css/feed.css" rel="stylesheet">
   <script src="./lightslider/js/lightslider.js"></script> 
   <!-- image slide -->
   <link rel="stylesheet"  href="./lightslider/css/lightslider.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    

</head>

<style>
   section div#mem_list_div table#tbl{
      margin-top: 20px;
      margin-left: 70px;
      width: 550px;
   }
   table#tbl>tr{
       
   }
   table#tbl>tr th{
      text-align: center;
      padding-left: 10px;
      padding-right: 10px;   
   }
   table#tbl>td
   {
      border: 1px solid lightgray;
      padding-right: 5px;
      padding-left: 5px;
   }
   table#tbl #tbl_img{
       width: 100px;
       height: 100px;
       border-radius: 50%;
       padding: 5px 0 5px 0;
   }
   section div#mem_list_div table#tbl>tr th,td{text-align:center;}
   
   section div#dongle_main_img {
      /*border: 1px solid black*/ 
      width:500px; 
      height: 300px; 
      /* background-color: lightgray;  */
      align-content: center;
      float: left;
      margin-left: 100px;
      margin-top: 30px;
      text-align:center;
   
   }
   section div#dongle_main_img >img{
   		
   		max-width:500px;
   		max-height:300px;
   		width:auto;
   		height:auto;
   
   }
   section div#mini_board{
      border-top:1px solid rgba(0,0,0,0.2); 
      border-bottom:1px solid rgba(0,0,0,0.2); 
      width: 530px; 
      height: 180px;
      margin-top: 30px;
      margin-left: 75px;
      position:relative;
      display:inline-block;
      left : 0px;
      top : 0px;
      float: left;   
   }
   

  section div#mini_feed{

   border-top:1px solid rgba(0,0,0,0.2); 
   border-bottom:1px solid rgba(0,0,0,0.2); 
   position:relative;
   display:inline-block;
   width: 530px; 
   height: 180px;
   margin-top: 30px;
   margin-left: 75px;  

   }   
   
   section div#mini_gallery{   

      border-top:1px solid rgba(0,0,0,0.2); 
      border-bottom:1px solid rgba(0,0,0,0.2);  
      width: 530px; 
      height: 130px; 
      margin-left:10px; 
      display:inline-block;
      margin-top: 20px;
      position:relative;
      margin-left: 75px;      

   }
   
   #main-table-bordered>tr{
       text-align: center;
    
   }
   #main-table-bordered>th{
      text-align: center;
   }
   .demo ul{
      list-style: none outside none;
      padding-left: 0;
        margin: 0;
   }
   .demo .item{
   margin-bottom: 60px;
    }
   .content-slider li{
   background-color: lightgray;
   text-align: center;
    color: #FFF;
   }
   .demo{
      width: 600px;
   }
   .com_mian_btn{
		display: inline-block;
		margin-bottom: 0;
		font-weight: 400;
		text-align: center;
		white-space: nowrap;
		vertical-align: middle;
		border: 1px solid transparent;
		font-size: 14px;
		line-height: 1.43;
		border-radius: 4px;
		color: #333;
		background-color: #fff;
		border-color: #ccc;
		margin: 5px 0 0 45px;
		font-family: 'netmarble Medium';
   }
   .menu-btn{
		color: #333;
		background-color: #fff;
		border-color: #ccc;
		display: inline-block;
		font-weight: 400;
		text-align: center;
		vertical-align: middle;
		background-image: none;
		border : 1px solid transparent;
		padding: 6px 12px;
		font-size: 14px;
		line-height: 1.43;
		border-radius: 4px;
		width: 100px;
		height: 34px;
		font-family: 'netmarble Medium';
		font-weight: bold;
		margin-left: -5px;
   }
   
   
</style>

<body>

<script> 
	$(function(){
		$('.menu-btn').mouseenter(function(){
			$(this).css('background-color','rgb(255,234,184)');
			$(this).css('color','white');
		});
		$('.menu-btn').mouseleave(function(){
			$(this).css('background-color','#fff');
			$(this).css('color','#333');
		});
	});
    $(function(){
        $.ajax({
           url:"<%=request.getContextPath()%>/community/boardList",
           data:{groupNo:<%=g.getGroupNo()%>},
           dataType:"html",
           success:function(data){
              $('#mini_board').html(data);
           }
        });
        
        $.ajax({
           url:"<%=request.getContextPath()%>/community/mainGallery",
           data:{groupNo:<%=g.getGroupNo()%>},
           dataType:"html",
           success:function(data){
              $('#mini_gallery').html(data);
           }
        });
     });

	$(function(){
	   $('.boardView-btn').click(function(){
	      console.log($(this).children('input').val());
	      var num=$(this).children('input').val();
	      $.ajax({
	         url:"<%=request.getContextPath() %>/board/boardView?groupNo=<%=groupNo%>&boardNo="+num,
	         type:"post",
	         dataType:"html",
	         success:function(data){
	            $('#board-container').html(data);          
	         },
	         error:function(error,msg){console.log("---"+error+msg);}
	      });
	   });
	});

</script>

    <div class='back'>
        <!-- 로고 헤더 -->
        <header>
        	<div style="width: 1024px;height: 45px; position:fixed;z-index:2;">
				<%@ include file="header_default.jsp"%>
			</div>
        <div class='logo' style='width:1024px; height: 100px; background-color:rgb(20,150,200); margin-top:45px; '>
       		
        	<img src ='<%=request.getContextPath()%>/images/css-image/dongle-logo.png' onclick="logoCk()" style="margin-left: 15px; height: 60px;">
                    <!-- <h2 style="color:rgb(250,237,125); margin-left:15px;" onclick='logoCk();'>DONGLE</h2> -->
                </div>
            <div class='logoback' style='position:relative;width:1024px;height: 100px;'>
            </div>

            <div style='width:auto; height:auto;position:relative; float:right;margin-right:120px;margin-top:20px;z-index:3;'>
                  <%for(int i=0;i<editList.size();i++){ %>
                     <%if(editList.get(i).getGroupNo()==groupNo){ %>
                        <%editnum++; %>
                        <img alt="" src="<%=request.getContextPath() %>/images/editor_images/groupEdit.png" style='width:30px; height:30px;position:fixed;'>
                        <span style='display:inline-block;margin-left:37px;margin-top:7px;position: fixed;'>에디터 선정</span>
                  <%} %>
                  <%} %>
                  <%if(editnum==0){ %>
                     <button id='goEdit' onclick='goEdit();' style='background-color:rgb(20,150,200); color:white;border:none;position:fixed;'>에디터픽 선정하기</button>
                  <%} %>
            </div>
        </header>
        <script>
           function goEdit(){
              location.href='<%=request.getContextPath()%>/admin/editpickForm?groupNo=<%=groupNo%>';
           }
        </script>
   <aside>
      <div class="center">
         <!-- 왼쪽 사이드 -->
         <div class="sidel"
            style='height: 100vh; background-color: rgb(228, 228, 228)'>
            
            <div class="sideitem1"
               style="left: 10%; right: 10%; width:160px; height: 250px; margin: 5px 0 0 8px;">
               <!-- 동글 프로필 -->

               <img class="profile_img" src="<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupImageNewPath()%>" style="width: 155px; height: 150px;">

               
               <!-- 동글이름 -->
               <p class="dongle_name" style="margin-bottom: 0;"><%=g.getGroupName()%></p>
               <!-- 멤버 회원수 / 그룹멤버 전체 보기-->

               <div id="dongle_info_view" style="font-size: 11px; padding-left: 40px;">회원수 : <%=result %> 명 &nbsp;</div>
               <button id="dongle_mem_btn" class='com_mian_btn'>멤버보기</button>
               <%if(gm==null){ %>
                   <button id="dongle_mem_join" class='com_mian_btn'>가입하기</button>
               <%} %>

               
            <!-- 단추 -->
            <div class='profile_btn'>
               <span class="demoSpan1"></span>
            </div>
            <!-- 회원정보 -->
            <%if(gm!=null){ %>
            <div class="user_info">
               <table id="user_info_tb" style="width:138px; margin-left: 10px; border: 1px solid lightgray;">
                  <tr>
                  <td style="width:65px" rowspan="2">

                     <img id="user_img" src="<%=request.getContextPath()%>/images/member_img/<%=gm.getGroupMemberImageNewPath()%>" style="width:60px">

                  </td>                  
                     <td class="gm_info">닉네임 : <%=gm.getGroupMemberNickname()%></td>
                  </tr>
                  <tr>
                     <td class="gm_info" style="font-size: 9px">가입일 : <%=gm.getGroupMemberEnrollDate() %></td>
                  </tr>
                  <tr>                      
                     <td colspan="2" class="gm_info">작성글 수 : </td>
                  </tr>               
               </table>

            </div><br>
            <%} %>
            </div>
            <!-- 소개글 -->
            <div class="sideitem2"
               style="margin-top:90px; margin-left:10px; right: 10%; width:150px; height: 150px; font-family: '나눔스퀘어라운드 Regular';" >
               <%=g.getGroupIntro() %>
            </div>
         </div>
      </div>
      <script>
         $(function() {
            var flag = true;
            $('.profile_btn').click(function() {
               if(flag){
                  $(this).next().slideDown();
                  flag=false;
               }
               else{
                  $(this).next().slideUp();
                  flag=true;

               }               
         	})            

         });

      </script>
   </aside>

 <script>       
 
 $('#dongle_mem_btn').click(function(){
     $.ajax({
       url:"<%=request.getContextPath()%>/memberList?groupNo=" + <%=g.getGroupNo()%>,
        type:"get",
        dataType:"json",
        success:function(data){
           $('#content-div').html(data);
           var h = "<h2>"+"<%=g.getGroupName()%>"+"의 멤버보기"+"</h2>";
           //var h2_main = $('<h2></h2>');
           var tbl = $('<table id="tbl" style="margin-left:70px; width:550px;"></table>');
           var thead="<tr style='font-size:18px;'><th>프로필</th><th>닉네임</th><th>가입일</th></tr>";
           <%-- var h2_txt="<%=g.getGroupName()%>"+"의 멤버보기"; --%>
            var thtml="";
             for(var i = 0; i < data.length; i++)
                 {
                   /* var tbody= "<tr>"; */
                   thtml+="<tr>";
                   thtml+="<td><img src='<%=request.getContextPath()%>/images/member_img/"+data[i]['groupMemberImagePath']+"'/></td>";
                   thtml+="<td style='padding-right:10px;'>"+data[i]['groupMemberNickname']+"</td>";
                   /* thtml+=tbody; */
                   thtml+="<td style='padding-right:10px;'>"+data[i]['groupMemberEnrollData']+"</td>";
                   thtml+="</tr>";
           }
           console.log(thtml);
           thead += thtml;
           tbl.append(thead);
           console.log(thead);
           console.log(tbl);
           console.log(h);
            $('#header').html(h);                     
            $('#mem_list_div').append(tbl);
           }
     });         
  });    
   
</script>
        <!-- 오른쪽 사이드 -->
        <aside>
            <div class="center" style="background-color: rgb(228, 228, 228)">

                <div class="sider" style='height:100vh; background-color:rgb(228, 228, 228)'>
                   <!-- 메뉴 버튼 -->
                    <button class='menu-btn' id='home-btn'onclick="comunnityHome();">HOME</button><br>
                    <button class='menu-btn' id="board-btn">공지사항</button><br>
                    <button class='menu-btn' id="feed-btn">피드</button><br>
                    <button class='menu-btn' id="gallery-btn" >갤러리</button><br>
                    <%if(loginMember.getMemberNo() == g.getMemberNo()){ %>
                    	<button class='menu-btn' id='manager-menu-btn'>동글 관리</button><br>
                    <%} %>
                </div>
            </div>
        </aside>
   
        <!-- 게시판 -->

       <section>
            <div class="main center" id="content-div"  style='width:684px; height:auto; background-color:white; align-content: center;'>
               
               <!-- 동글메인이미지 -->
               <div id='dongle_main_img'>
                  <img src="<%=request.getContextPath()%>/images/dongle_main_img/<%=g.getGroupMainNewImgPath()%>">
               </div>
               <div id='mini_board'></div>
               <div id='mini_gallery'></div>
               <div id='mini_feed'></div>

             </div>
             
        </section>

<!-- 갤러리 모달창 들어갈 부분입니다 (추가해주세요) 삭제하지마요 ㅠㅠ-->
<div class="modal-div">
   <div class="dialog" id="modal-container">
      <div class="modal-content">
      </div>
      </div>
</div>       

<script>
      $('#dongle_mem_btn').click(function(){
         $.ajax({
         url:"<%=request.getContextPath()%>/memberList?groupNo=" + <%=g.getGroupNo()%>,
           type:"get",
           dataType:"json",
           success:function(data){
              var sp = "<span id='header' style='text-align:center;'>"+"</span>";
              sp+="<br/>"+"<br/>";
              sp+="<div id='mem_list_div' style='padding:0 0 0 30%';>"+"</div>";
              var h = "<h2 style='margin-top:100px;'>"+"<%=g.getGroupName()%>"+"의 멤버보기"+"</h2>";
              var tbl = $('<table id="tbl" style="margin-left:70px; width:550px;"></table>');
              var thead="<tr style='font-size:20px; height:50px; background-color:rgba(234,233,233,0.4);'><th>프로필</th><th>닉네임</th><th>가입일</th></tr>";
              var thtml="";
              for(var i = 0; i < data.length; i++)
              {
					thtml+="<tr>";
                  thtml+="<td><img id='tbl_img' src='<%=request.getContextPath()%>/images/member_img/"+data[i]['groupMemberImageNewPath']+"'/></td>";
                  thtml+="<td>"+data[i]['groupMemberNickname']+"</td>";
                  thtml+="<td>"+data[i]['groupMemberEnrollData']+"</td>";
                  thtml+="</tr>";
               }
              console.log(thtml);
            thead += thtml;
            tbl.append(thead);
            console.log(tbl);
            console.log(h);
            $('#content-div').html(sp);
            $('#header').html(h); 
            $('#content-div').append(tbl);
            }
           });         
        });
      
      $('#feed-btn').click(function(){
      var groupNo=<%=groupNo%>;
      var memberNo=<%=loginMember.getMemberNo()%>;
      console.log(groupNo);
      $.ajax({
         url:"<%=request.getContextPath()%>/feed/feedListView",
         type:"post",
         data:{
            "groupNo":groupNo,
            "memberNo":memberNo   
         },
         
         dataType:"html",
         success:function(data){
        	
            $('#content-div').html(data);
            setImage();
            $("#feed-btn").css('background-color','rgb(255,234,184)');
            $("#feed-btn").css('color','white');

         }
      });
   });
      $(function(){
         $('#board-btn').click(function(){
            $.ajax({
               url:"<%=request.getContextPath()%>/board/boardList?groupNo=<%=g.getGroupNo()%>",
               type:"post",
               dataType:"html",
               success:function(data){
                  $('#content-div').html(data);
                  //해당 div를 ajax로 바꾼다는 의미.
/*                   
                  $('#gallery-btn').css('background-color','#fff');
                  $('#gallery-btn').css('color','#333');
                  $("#board-btn").css('background-color':'rgb(255,234,184)','color':'white');    */       
                  
               }
            });
         });
      });
      
      function comunnityHome(){
         location.href="<%=request.getContextPath()%>/communityJoin?groupNo=<%=g.getGroupNo()%>";
      }
    </script>

</div>

<div style='display: inline-block; width:1024px;height:60px;background-color:color:rgb(250,237,125);position:relative;'></div>

<script>
/* 갤러리 클릭시 매핑함수 */
$(function(){
   $("#gallery-btn").click(function(){
      $.ajax({
         url:"<%=request.getContextPath()%>/gallery/albumGet?groupNo=<%=g.getGroupNo()%>&memberNo=<%=loginMember.getMemberNo()%>",
         type:"post",
         dataType:"html",
         success:function(data){
            $('#content-div').html(data);
            
			$("#gallery-btn").css('background-color','rgb(255,234,184)');
			$("#gallery-btn").css('color','white');
         },
         error:function(request){},
         complete:function(){console.log("ok");}
      })
   })
});

$(function(){
   $("#manager-menu-btn").click(function(){
      var managerNo=<%=g.getMemberNo()%>;
      
      console.log(<%=g.getMemberNo()%>);
      $.ajax({
         url:"<%=request.getContextPath()%>/manager/managerView?groupNo=<%=g.getGroupNo()%>",
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
$(function(){
   $("#dongle_mem_join").click(function(){
      $.ajax({
         url:"<%=request.getContextPath()%>/dongleJoinView?groupNo=<%=g.getGroupNo()%>",
         type:"post",
         dataType:"html",
         success:function(data){
            $('#content-div').html(data);
         },
         error:function(request){},
         complate:function(){console.log("ok");}
      })
   })
});



$(function(){
   $("#dongle_mem_update").click(function(){
      $.ajax({
         url:"<%=request.getContextPath()%>/dongleUpdateView?groupNo=<%=g.getGroupNo()%>",
         type:"post",
         dataType:"html",
         success:function(data){
            $('#content-div').html(data);
         },
         error:function(request){},
         complate:function(){console.log("ok");}
      })
   })
});


function comunnityHome(){
   location.href="<%=request.getContextPath()%>/communityJoin?groupNo=<%=g.getGroupNo()%>";
}
function logoCk(){
	location.href="<%=request.getContextPath()%>/communityJoin?groupNo=<%=g.getGroupNo()%>";
}
</script>
</body>

</html>