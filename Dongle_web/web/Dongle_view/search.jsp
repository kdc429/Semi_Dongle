<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*,com.dongle.main.model.vo.*" %>
<!DOCTYPE html>
<%
	List<Group> groupList = (List)request.getAttribute("groupList");
	List<LocationCategory>metroCodeList = (List)request.getAttribute("metroCodeList");
	
%>
<html>
<head>
<meta charset="UTF-8">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
    body {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	}
	ul.search-Local li 
	{
		list-style-type: none; 
    	width: 4em; 
	    height: 2em;
	    float: left; 
	    text-align: center; 
	    font-family: '나눔스퀘어라운드 Regular';
	    border: 1px lightgray solid;
	    background-color: #EAEAEA;
	}
	.container
	{
		margin: 0 auto;
	}
	ul.tabs
	{
		margin: 0px;
		padding: 0px;
		list-style: none;
	}
	ul.tabs li
	{
		background: none;
		color: black;
		display: inline-block;
		padding: 10px 15px;
		cursor: pointer;
		font-family: '나눔스퀘어라운드 Regular';
	} 
	ul.tabs li.current
	{
		background: #ededed;
		color: black;
	}
	.tab-content
	{
		display: none;
		background: #ededed;
		padding: 15px;
	}
	.tab-content.current
	{
		display: inherit;
	}
	.search-container
	{
		margin :10px;
		/* border: 4px solid #bcbcbc; */
		width: 1024px;
		height: 100vh;
		font-family: '나눔스퀘어라운드 Regular';

	}
    .condition-container
    {
		padding : 10px;
		font-size : 18px;  
		font-family: '나눔스퀘어라운드 Regular';  
    }
    .location-container
    {
    	padding : 10px;
    	height : 250px;
    	font-family: '나눔스퀘어라운드 Regular';
    }
     .location-container>h3{
		font-family: '나눔스퀘어라운드 Regular';
		font-weight: bold;
     }
    .tab-content>time
    {
    	width:50px;
    }
    div#search-groupName
    {
    	display : inline;
    }
    #sortBar
    {
    	float: right;
    	display : inline-block;
    }
    .search_dongle_main_img{
    	border: 1px solid blue;
    	width: 150px;
    	height: 150px;
    	/* margin-left: 10%; */
    	display: inline-block;
    	margin-left: 50px;
    	margin-right: 20px;
    	float: left;
    }
    .search_dongle_info{
    	border: 1px solid blue;
    	width: 250px;
    	height: 150px;
    	display: inline-block;
    	float: left;
    }
	#searchKeyword
	{
		margin-bottom: 30px;
	}
	#main_dongle_info
	{
		left: 0;
		top:0;
		margin-left: 50px;
	}
	fieldset {
		font-family: '나눔스퀘어라운드 Regular';
	}
}
    
</style>
<body>
<div class="search-container" style="border:1px solid red;">
	<div class='search-menu' style="border: 1px dotted darkgray;">
		<div class="condition-container" style="border : red">
			<form style="widht:400px" id='search-condition'>	
				<fieldset>
					<span style="font-size: 20px;font-weight: bold;">시간대 :</span><span><label style="font-size: 17px; font-weight: lighter;"><input type="checkbox" id="time" name="time"/>평일</label>
					<label style="font-size: 17px; font-weight: lighter;"><input type="checkbox" id="time" name="time"/>주말</label></span> &nbsp;&nbsp; &nbsp;&nbsp;
					<span style="font-size: 20px;font-weight: bold;">성별 : </span><span><label style="font-size: 17px; font-weight: lighter;"><input type="radio" id="gender" name="gender"/>무관</label>
				   	<label style="font-size: 17px; font-weight: lighter;"><input type="radio" id="gender" name="gender"/>남</label>
					<label style="font-size: 17px; font-weight: lighter;"><input type="radio" id="gender" name="gender" />여</label></span> &nbsp;&nbsp; &nbsp;&nbsp;
					<span style="font-size: 20px;font-weight: bold;">연령대 : </span><!-- <input type="range" id="age-group" name="age-group" min="0" max="100"/></label> -->
					<label style="font-size: 17px; font-weight: lighter;"><input type="checkbox" id='ck-age'>10대</label><label style="font-size: 17px; font-weight: lighter;"><input type="checkbox" id='ck-age'>20대</label>
					<label style="font-size: 17px; font-weight: lighter;"><input type="checkbox" id='ck-age'>30대</label><label style="font-size: 17px; font-weight: lighter;"><input type="checkbox" id='ck-age'>40대</label>
					<label style="font-size: 17px; font-weight: lighter;"><input type="checkbox" id='ck-age'>50대이상</label>
				</fieldset>
			</form>
		</div>
		<div class="location-container">
			<h3>지역검색설정</h3>
				<ul class="tabs">
					<li class="tab-link current" data-tab="tab-1">서울</li>
				    <li class="tab-link" data-tab="tab-2">경기</li>
				    <li class="tab-link" data-tab="tab-3">인천</li>
				    <li class="tab-link" data-tab="tab-4">강원</li>
				    <li class="tab-link" data-tab="tab-5">대전</li>
				    <li class="tab-link" data-tab="tab-6">세종</li>
				    <li class="tab-link" data-tab="tab-7">충북</li>
				    <li class="tab-link" data-tab="tab-8">충남</li>
				    <li class="tab-link" data-tab="tab-9">부산</li>
				    <li class="tab-link" data-tab="tab-10">울산</li>
				    <li class="tab-link" data-tab="tab-11">경남</li>
				    <li class="tab-link" data-tab="tab-12">경북</li>
				    <li class="tab-link" data-tab="tab-13">대구</li>
				    <li class="tab-link" data-tab="tab-14">광주</li>
				    <li class="tab-link" data-tab="tab-15">전남</li>
				    <li class="tab-link" data-tab="tab-16">전북</li>
				    <li class="tab-link" data-tab="tab-17">제주</li>
				    <li class="tab-link" data-tab="tab-18">전국</li> 
				</ul>
				<div id="tab-1" class="tab-content current">
					<label><input type="checkbox" id="time1"/>서울전체</label>
					<label><input type="checkbox" id="time2"/>강남구</label>
					<label><input type="checkbox" id="time"/>강동구</label>
					<label><input type="checkbox" id="time"/>강북구</label>
					<label><input type="checkbox" id="time"/>강서구</label>
					<label><input type="checkbox" id="time"/>관악구</label>
					<label><input type="checkbox" id="time"/>광진구</label>
					<label><input type="checkbox" id="time"/>구로구</label>
					<label><input type="checkbox" id="time" name="time"/>금천구</label>
					<label><input type="checkbox" id="time" name="time"/>노원구</label>
					<label><input type="checkbox" id="time" name="time"/>도봉구</label>
					<label><input type="checkbox" id="time" name="time"/>동대문구</label>
					<label><input type="checkbox" id="time" name="time"/>동작구</label>
					<label><input type="checkbox" id="time" name="time"/>마포구</label>
					<label><input type="checkbox" id="time" name="time"/>서대문구</label>
					<label><input type="checkbox" id="time" name="time"/>서초구</label>
					<label><input type="checkbox" id="time" name="time"/>성동구</label>
					<label><input type="checkbox" id="time" name="time"/>성북구</label>
					<label><input type="checkbox" id="time" name="time"/>송파구</label>
					<label><input type="checkbox" id="time" name="time"/>양천구</label>
					<label><input type="checkbox" id="time" name="time"/>영등포구</label>
					<label><input type="checkbox" id="time" name="time"/>용산구</label>
					<label><input type="checkbox" id="time" name="time"/>은평구</label>
					<label><input type="checkbox" id="time" name="time"/>종로구</label>
					<label><input type="checkbox" id="time" name="time"/>중구</label>
					<label><input type="checkbox" id="time" name="time"/>중랑구</label>
				
					<div id="tab-2" class="tab-content">
					---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
						
					</div>
					<div id="tab-3" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-4" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-5" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-6" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-7" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-8" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-9" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-10" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-11" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-12" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-13" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-14" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-15" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-16" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-17" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
					<div id="tab-18" class="tab-content">
						---- ★-- -------- ---- ---- ---- -★- ---- ---- -------- ---- -★- ---- ---- ---- ---- -------- ---- ---- ---- ---- ---- --★ -------- ★-- ---- ---- ---- ---- ---- -------- ---- ---- --★ ---- ---- ---- -------- ---- ---- ---- --★
					</div>
				</div>
		</div>
	</div>
	<h2>동글검색</h2>
	<div id="search-container">
		<div id="search-groupName">
			<form action="<%=request.getContextPath() %>/admin/memberFinder">
				<input type="hidden" name="searchType" value="groupName"/>
				<input type="text" name="searchKeyword" size="100" placeholder="검색할 동글을 입력하세요" id='searchKeyword'/>
				<button type="submit">검색</button>
										
				<select id="sortBar" style="height:25px">				
					<option value="userId">게시글수 </option>
					<option value="userName">조회수</option>
					<option value="gender">회원수</option>				
				</select>	
			</form>
		</div>				
	</div>
	<%for(int i=0; i<groupList.size();i++){%>
	<div style="vertical-align: middle;">
		<div class='search_dongle_main_img'><img src="<%=request.getContextPath()%>/images/dongle_main_img/<%=groupList.get(i).getGroupMainNewImgPath()%>" style='width: 150px; height: 150px;'></div>
		<div class='search_dongle_info'><p><%=groupList.get(i).getGroupIntro() %></p></div>
	</div>
	
		
	<%} %>
</div>	
</body>




<script>
$(document).ready(function(){
	   
	  $('ul.tabs li').click(function(){
	    var tab_id = $(this).attr('data-tab');
	 
	    $('ul.tabs li').removeClass('current');
	    $('.tab-content').removeClass('current');
	 
	    $(this).addClass('current');
	    $("#"+tab_id).addClass('current');
	  });
	 
});


</script>
</html>