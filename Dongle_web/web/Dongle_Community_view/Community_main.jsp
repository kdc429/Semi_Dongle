<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*, com.dongle.member.model.vo.Member,
com.dongle.board.model.vo.Board" %>
<!DOCTYPE html>
<%
	Group g = (Group)request.getAttribute("group");
	Member loginMember = (Member)request.getAttribute("loginMember");
	GroupMember gm = (GroupMember)request.getAttribute("groupMember");
	int result = (int)request.getAttribute("result");
	int groupNo = Integer.parseInt(request.getParameter("groupNo"));
	//List<Board> list=(List)request.getAttribute("list");
%>
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
 <!--    <link href="https://fonts.googleapis.com/css?family=Bungee" rel="stylesheet"> -->
	<!-- <link href="<%=request.getContextPath()%>/css/Test.css" rel="stylesheet"> -->
	<link href="<%=request.getContextPath()%>/css/Dongle_Community.css" rel="stylesheet">
	<!-- image slide -->
	<link rel="stylesheet"  href="./lightslider/css/lightslider.css"/>
 	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="./lightslider/js/lightslider.js"></script> 
</head>

<style>
	section div#mem_list_div table#tbl{
		margin-top: 20px;
	}
	table#tbl>tr{
		border : 1px solid darkgray;		
	}
	table#tbl>td
	{
		border: 1px solid lightgray;
	}
	section div#mem_list_div table#tbl>tr th,td{text-align:center;}
	
	section div#dongle_main_img {
	
		/*border: 1px solid black*/ 
		width:500px; 
		height: 250px; 
		background-color: lightgray; 
		align-content: center;
		float: left;
		margin-left: 100px;
	
	}
	section div#mini_board{
		border:1px solid rgba(178,204,255,0.3); 
		width: 300px; 
		height: 250px; 
		position:relative;
		display:inline-block;
		margin-top: 150px;
		margin-left: 35px;
	
	}
	
	section div#mini_gallery{
	
		border: 1px solid rgba(178,204,255,0.3);  
		width: 300px; 
		height: 250px; 
		margin-left:10px; 
		display:inline-block;
		margin-top: 150px;
		
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
		width: 600px;;
	}
</style>

<body>
	<script>
		$(function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/community/boardList",
				data:{groupNo:<%=g.getGroupNo()%>},
				dataType:"html",
				success:function(data){
					$('#mini_board').html(data);
				}
			});
			
			
	
			$('#feed-btn').click(function(){
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedListView",
					type:"get",
					dataType:"html",
					success:function(data){
						$('.main').html(data);
					}
				});
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
		
		 $(document).ready(function() {
				$("#content-slider").lightSlider({
	                loop:true,
	                keyPress:true
	            });
	           /*  $('#image-gallery').lightSlider({
	                gallery:true,
	                item:1,
	                thumbItem:9,
	                slideMargin: 0,
	                speed:500,
	                auto:true,
	                loop:true,
	                onSliderLoad: function() {
	                    $('#image-gallery').removeClass('cS-hidden');
	                }  
	            }); */
			});
		 
	</script>
    <div class='back'>
        <!-- 로고 헤더 -->

        <header>
            <div class='logoback' style='width:1024px;height: auto'>
                <!-- 로고 grid -->
                <div class='logo' style='width:1024px; height: auto; background-color:rgb(228, 228, 228)'>
                    <h2 style="color:darkcyan; margin-left:15px;">DONGLE</h2>
                </div>
            </div>
        </header>
	<aside>
		<div class="center">
			<!-- 왼쪽 사이드 -->
			<div class="sidel"
				style='height: 100vh; background-color: rgb(228, 228, 228)'>
				<div class="sideitem1"
					style="border: 1px solid rgba(255,0,0,0.1); left: 10%; right: 10%; height: 250px;">
					<!-- 동글 프로필 -->
					<img class="profile_img" src="<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupImgNewPath()%>">
					
					<!-- 동글이름 -->
					<p class="dongle_name"><%=g.getGroupName()%></p>
					<!-- 멤버 회원수 / 그룹멤버 전체 보기-->
					<div class="dongle_info">
						<div id="dongle_info_view" style="font-size: 11px;">회원수 : <%=result %> 명 &nbsp;</div>
						<div id="dongle_info_view">
							<button id="dongle_mem_btn">멤버보기</button>
						</div>
					</div>
					<br/>
					
				<!-- 단추 -->
				<div class='profile_btn'>
					<span class="demoSpan1"></span>
				</div>
				<!-- 회원정보 -->
				<div class="user_info">
					<table id="user_info_tb" style="width:138px" border="1px solid black">
						<tr>
						<td style="width:65px" rowspan="2">
							<img id="user_img" src="<%=request.getContextPath()%>/images/member_img/<%=gm.getGroupMemberImgNewPath()%>" style="width:60px">
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
				<!-- 소개글 -->
				<div class="sideitem2"
					style='border: 1px solid rgba(255,0,0,0.1);  right: 10%; height: 150px'>
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
	        <!-- 오른쪽 사이드 -->
        <aside>
            <div class="center" style="background-color: rgb(228, 228, 228)">

                <div class="sider" style='height:100vh; background-color:rgb(228, 228, 228)'>
                    <!-- 메뉴 버튼 -->
                    <button class='btn btn-primary' onclick="comunnityHome();">HOME</button><br>
                    <button class='btn btn-primary' id="board-btn">공지사항</button><br>
                    <button class='btn btn-primary' id="feed-btn">피드</button><br>
                    <button class='btn btn-primary'>갤러리</button><br>
                    <button class='btn btn-primary'>일정</button><br>
                </div>
            </div>
        </aside>
	
        <!-- 게시판 -->
        <section>
            <div class="main center" id="content-div"  style='width:684px; height:auto; background-color:white; align-content: center;'>
            	<!-- <span id="header" style='text-align: center;'></span> -->
            	<!-- <br/><br/> -->

            	<!-- <div id="mem_list_div" style="padding: 0 0 0 30%;"></div> -->
            	
            	<!-- 동글메인이미지 -->
            	<div id='dongle_main_img'>
            		<img src="<%=request.getContextPath()%>/images/dongle_main_img/<%=g.getGroupMainNewImgPath()%>">
            	</div>
            	<div id='mini_board'></div>
            	<div id='mini_gallery'>갤 러 리</div>
            	
            	<div class="demo" style="margin-top: 50px; padding-left: 50px;">      
			       	 <div class="item">
			            <ul id="content-slider" class="content-slider">
			                <li>
			                    <img src="./images/member_img/user01.png">
			                </li>
			                <li>
			                    <img src="./images/member_img/user02.png"> 
			                </li>
			                <li>
			                 	<img src="./images/member_img/user03.png"> 
			                </li>
			                <li>
			                    <h3>4</h3>
			                </li>
			                <li>
			                    <h3>5</h3>
			                </li>
			                <li>
			                    <h3>6</h3>
			                </li>
			                <li>
			                    <h3>7</h3>
			                </li>
			                <li>
			                    <h3>8</h3>
			                </li>
			                <li>>
			                    <h3>9</h3>
			                </li>
			                <li>
			                    <h3>10</h3>
			                </li>
			                
			            </ul>
			        </div>
			     </div>	

             </div>
        </section>
       
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
     			var h = "<h2>"+"<%=g.getGroupName()%>"+"의 멤버보기"+"</h2>";
     			var tbl = $('<table id="tbl" style="margin-left:200px;"></table>');
     			var thead="<tr style='font-size:18px; background-color: #E1E1E1;'><th>프로필</th><th>닉네임</th><th>가입일</th></tr>";
     			var thtml="";
     			for(var i = 0; i < data.length; i++)
     			{
					thtml+="<tr>";
     			    thtml+="<td><img src='<%=request.getContextPath()%>/images/member_img/"+data[i]['groupMemberImgNewPath']+"'/></td>";
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
   	
   	$(function(){
   		$('#feed-btn').click(function(){
   			$.ajax({
   				url:"<%=request.getContextPath()%>/feed/feedListView",
   				type:"get",
   				dataType:"html",
   				success:function(data){
   					$('#content-div').html(data);
   				}
   			});
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
   				}
   			});
   		});
   	});

   	
   	function comunnityHome(){
   	   location.href="<%=request.getContextPath()%>/communityJoin?groupNo=<%=g.getGroupNo()%>";
   	}
    </script>



    </div>



</body>

</html>