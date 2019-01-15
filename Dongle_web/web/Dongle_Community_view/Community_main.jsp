<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.member.model.vo.*,com.dongle.group.model.vo.*" %>
<%
	Group g=(Group)request.getAttribute("group");
	Member loginMember=(Member)request.getAttribute("loginMember");

%>
<!DOCTYPE html>
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
	<link href="<%=request.getContextPath()%>/css/Dongle_Community.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/feed.css" rel="stylesheet">
 
</head>

<body>
	<script>
		$(function(){
			$('#feed-btn').click(function(){
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedListView?groupNo=<%=g.getGroupNo()%>&memberNo=<%=loginMember.getMemberNo()%>",
					type:"get",
					dataType:"html",
					success:function(data){
						$('.main').html(data);
					}
				});
			});
		});
	</script>
    <div class='back'>
        <!-- 로고 헤더 -->

        <header>
            <div class='logoback' style='width:1024px;height: auto'>
                <!-- 로고 grid -->
                <div class='logo' style='width:1024px; height: auto; background-color:rgba(130, 238, 41, 0.8)'>
                    <h2 style="color:darkcyan; font-family: 'Bungee', cursive; margin-left:15px;">DONGLE</h2>
                </div>
            </div>
        </header>
        <aside>
            <div class="sideback center">
                <!-- 왼쪽 사이드 -->
                <div class="sidel" style='height:100vh; background-color:rgba(130, 238, 41, 0.8)'>
                    <div class="sideitem" style="border:1px solid red; left:10%; right:10%; height:250px;">프로필</div>
                    <div class="sideitem" style='border:1px solid red; left:10%; right:10%; height:150px'>소개글</div>
                </div>
            </div>

        </aside>
        <!-- 게시판 -->
        <section>
            <div class="main center" style='width:684px;height:auto; background-color:rgb(255, 255, 255);'>
                <br><br>
                651651651651
            



                

            </div>

        </section>
        <!-- 오른쪽 사이드 -->
        <aside>
            <div class="sideback center">

                <div class="sider" style='height:100vh; background-color:rgba(130, 238, 41, 0.8)'>
                    <!-- 메뉴 버튼 -->
                    <button class='btn btn-primary'>HOME</button><br>
                    <button class='btn btn-primary'>공지사항</button><br>
                    <button class='btn btn-primary' id="feed-btn">피드</button><br>
                    <button class='btn btn-primary'>갤러리</button><br>
                    <button class='btn btn-primary'>일정</button><br>
                </div>
            </div>
        </aside>



    </div>



</body>

</html>