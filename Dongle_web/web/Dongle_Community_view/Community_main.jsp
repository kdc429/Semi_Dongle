<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.dongle.group.model.vo.Group,com.dongle.member.model.vo.Member"%>
	<%
		Group g = (Group)request.getAttribute("group");
		Member loginMember = (Member)session.getAttribute("loginMember");
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
	<link href="<%=request.getContextPath() %>/css/Test.css" rel="stylesheet">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
 
</head>

<body>
    <div class='back'>
        <!-- 로고 헤더 -->

        <header>
            <div class='logoback' style='position:relative;width:1024px;height: auto'>
                <!-- 로고 grid -->
                <div class='logo' style='width:1024px; height: auto; background-color:rgba(0, 10, 40, 0.8);'>
                    <h2 style="color:rgb(250,237,125); font-family: 'Bungee', cursive; margin-left:15px;">DONGLE</h2>
                </div>
            </div>
        </header>
        <aside>
            <div class="sideback center">
                <!-- 왼쪽 사이드 -->
                <div class="sidel" style='height:100vh; background-color:rgba(0, 10, 40, 0.8)'>
                    <div class="sideitem" style="border:1px solid red; left:10%; right:10%; height:250px;">프로필</div>
                    <div class="sideitem" style='border:1px solid red; left:10%; right:10%; height:150px'>소개글</div>
                </div>
            </div>

        </aside>
        <!-- 게시판 -->
        <section>
            <div class="main center" id="content-div" style='width:684px;height:auto; background-color:rgb(255, 255, 255); border:1px solid black;'>

            </div>
        </section>
        <!-- 오른쪽 사이드 -->
        <aside>
            <div class="sideback center">
                <div class="sider" style='height:100vh; background-color:rgba(0, 10, 40, 0.8)'>
                    <!-- 메뉴 버튼 -->
                    <button class='btn btn-primary' onclick="comunnityHome();">HOME</button><br>
                    <button class='btn btn-primary' id="board-btn">공지사항</button><br>
                    <button class='btn btn-primary' id="feed-btn">피드</button><br>
                    <button id="gallery-btn" class='btn btn-primary'>갤러리</button><br>
                    <button class='btn btn-primary'>일정</button><br>
                </div>
            </div>
        </aside>
    </div>

<script>

function communityHome(){
	location.href="<%=request.getContextPath()%>/communityJoin?gNo=<%=g.getGroupNo()%>&mNo=<%=loginMember.getMemberId()%>";
}

$(function(){
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
	$('#board-btn').click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/board/boardListView",
			type:"get",
			dataType:"html",
			success:function(data){
				$('.main').html(data);
			}
		});
	});
});

/* 갤러리 클릭시 매핑함수 */
$(function(){
	$("#gallery-btn").click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/gallery/albumGet?groupNo=<%=g.getGroupNo()%>&memberNo=<%=loginMember.getMemberNo()%>",
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

</script>
</body>

</html>