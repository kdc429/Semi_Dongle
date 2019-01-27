<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*,com.dongle.main.model.vo.*" %>
<!DOCTYPE html>
<%
	List<Group> groupList = (List)request.getAttribute("groupList");
	List<TopicCategory> topicList=(List)request.getAttribute("topicList");
	String pageBar=(String)request.getAttribute("pageBar");
	List<MultiLocationName> locationList=(List)request.getAttribute("locationList");
	List<MultiTopicName> topicList2=(List)request.getAttribute("topicList2");
	List<GroupMemberCount> memberCountList=(List)request.getAttribute("memberCountList");
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
		
		/* border: 4px solid #bcbcbc; */
		width: 1024px;
		height: auto;
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
    	height : auto;
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
    	
    	width: 150px;
    	height: 150px;
    	/* margin-left: 10%; */
    	display: flex;
    	justify-content:center;
    	align-items:center;
    	margin-left: 50px;
    	margin-right: 20px;
    	float: left;
    	text-align: center;
    }
    .search_dongle_info{
    	text-align:center;
    	width: 250px;
    	height: 250px;
    	display: inline-block;
    	float: left;
    	font-size:12px;
    }
    .search_dongle_info>ul{
    	list-style:none;
    	padding:0;
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
	#topic-select>ul>li{
		display:inline;
	}
	#topic-select>ul{
		list-style:none;
		padding:10px;
	}
	.topic-container>h3{
		font-family: '나눔스퀘어라운드 Regular';
		font-weight: bold;
	}
	.topic-container{
		padding : 10px;
	}
	#dongle-container-back>ul{
		list-style:none;
		padding:10px;
		height:auto;
	}
	
	#dongle-container{
		display:inline-block;
		
	}
	.group-img{
		max-width:150px;
		max-height:150px;
		width:auto;
		height:auto;
		cursor:pointer;
		
	}
	#dongle-container-back{
		display:inline-block;
		height:auto;
		width:1000px;
	}
	#page-bar-back{
		text-align:center;
		vertical-align: bottom;
	}
	.page-bar{
		margin:5px;
		display:inline;
		cursor:pointer;
	}
	.dongle-list{
		height:150px;
		width:450px;
		display:inline;
	}

</style>
<script>

	var checkLoc; //체크한 로케이션 코드
	var locArray=[]; //체크한 로케이션 코드 배열
	var checkTime;//체크한 평일 주말 value
	var checkMinAge;//체크한 최소 연령
	var checkMaxAge;//체크한 최대 연령
	var checkTopic;// 체크한 토픽 코드
	var topicArray=[];//체크한 토픽코드 배열
	var sortCheck="date";
	var cPage;
	
	$(document).ready(function(){
		//metro check 서울,인천,대전......
		$('.tab-link').on('click',function(){
			var locationCode=$(this).children('input').val();
			$.ajax({
				url:"<%=request.getContextPath()%>/main/mainLocationSearch",
				type:"post",
				data:{"locationCode":locationCode},
				datatype:"html",
				success:function(data){
					console.log(data);
					locArray=[];
					$('#do>li').remove();
					$('#tab-1>ul>label').remove();
					$('#tab-1>ul').html(data);
				}
			})
		})
	});
	
	$(document).ready(function(){
		//metro check 강원,경기,......
		$('.tab-link2').on('click',function(){
			var locationCode=$(this).children('input').val();
			$.ajax({
				url:"<%=request.getContextPath()%>/main/mainLocationSearch2",
				type:"post",
				data:{"locationCode":locationCode},
				datatype:"html",
				success:function(data){
					console.log(data);
					locArray=[];
					var html="";
					$('#tab-1>ul>label').remove();
					$('#do>li').remove();
					
					for(var key in data){
						var areaNameCode=data[key];
						var areaCode=areaNameCode["areaCode"];
						var areaName=areaNameCode["locAreaName"];
						console.log(areaCode);
						console.log(areaName);
						html+="<li class='tab-link-area'><input class='area-code' type='hidden' value='"+areaCode+"'>"+areaName+"</li>";
					}
					
					$('#do').html(html);
				}
			})
		})
		
	});
	
	$(document).on('click','.tab-link-area',function(){
		//click area 도 선택 이후 시(성남시,안산시.......)
		console.log("dd");
		var locationCode=$(this).children('input').val();
		$.ajax({
			url:"<%=request.getContextPath()%>/main/mainLocationSearch3",
			type:"post",
			data:{"locationCode":locationCode},
			datatype:"html",
			success:function(data){
				console.log(data);
				$('#tab-1>ul').html(data);
			}
		})
	})
	
	$(document).on('click','#tab-1>ul>label>input',function(){
		
		console.log(locArray);
		console.log(topicArray);
		console.log(checkMinAge)
		console.log(checkMaxAge);
		console.log(checkTime);
		if($(this).is(":checked")){
			checkLoc=($(this).val());
			locArray.push(checkLoc);
			console.log(locArray);
		}else{
			checkLoc=($(this).val());
			
			for(var i=0;i<locArray.length;i++){
				if(locArray[i]==checkLoc){
					
					var idx=locArray.indexOf(locArray[i]);
					if(idx>-1){
						locArray.splice(idx,1);
					}
					
				}
			}
			console.log(locArray);
		}
		jQuery.ajaxSettings.traditional = true;
		
		$.ajax({
			
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			type:"post",
			data:{
				"locArray":locArray,
				"topicArray":topicArray,
				"minAge":checkMinAge,
				"maxAge":checkMaxAge,
				"time":checkTime,
				"sortCheck":sortCheck,
				"cPage":cPage
				
			},
			datatype:"html",
			success:function(data){
				$('#dongle-container').html(data);
				
			}
			
		})
		
		
	})
	
	$(document).on('click','#time',function(){
		checkTime=$(this).val();
		
		console.log(locArray);
		console.log(topicArray);
		console.log(checkMinAge)
		console.log(checkMaxAge);
		console.log(checkTime);
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
			
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			type:"post",
			data:{
				"locArray":locArray,
				"topicArray":topicArray,
				"minAge":checkMinAge,
				"maxAge":checkMaxAge,
				"time":checkTime,
				"sortCheck":sortCheck,
				"cPage":cPage
				
			},
			datatype:"html",
			success:function(data){
				$('#dongle-container').html(data);
				
			}
			
		})
	});
	
	$(document).on('change','#min-age',function(){
		checkMinAge=$('#min-age option:selected').val();
		
		console.log(locArray);
		console.log(topicArray);
		console.log(checkMinAge)
		console.log(checkMaxAge);
		console.log(checkTime);
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
			
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			type:"post",
			data:{
				"locArray":locArray,
				"topicArray":topicArray,
				"minAge":checkMinAge,
				"maxAge":checkMaxAge,
				"time":checkTime,
				"sortCheck":sortCheck,
				"cPage":cPage
				
			},
			datatype:"html",
			success:function(data){
				$('#dongle-container').html(data);
				
			}
			
		})
	});
	
	$(document).on('change','#max-age',function(){
		checkMaxAge=$('#max-age option:selected').val();
		
		console.log(locArray);
		console.log(topicArray);
		console.log(checkMinAge)
		console.log(checkMaxAge);
		console.log(checkTime);
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
			
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			type:"post",
			data:{
				"locArray":locArray,
				"topicArray":topicArray,
				"minAge":checkMinAge,
				"maxAge":checkMaxAge,
				"time":checkTime,
				"sortCheck":sortCheck,
				"cPage":cPage
				
			},
			datatype:"html",
			success:function(data){
				$('#dongle-container').html(data);
				
			}
			
		})
		
	});
	
	$(document).on('click','.topic',function(){
		
		console.log(locArray);
		console.log(topicArray);
		console.log(checkMinAge)
		console.log(checkMaxAge);
		console.log(checkTime);
		if($(this).is(":checked")){
			checkTopic=($(this).val());
			topicArray.push(checkTopic);
			
		}else{
			checkTopic=($(this).val());
			
			for(var i=0;i<topicArray.length;i++){
				if(topicArray[i]==checkTopic){
					
					var idx=topicArray.indexOf(topicArray[i]);
					if(idx>-1){
						topicArray.splice(idx,1);
					}
					
				}
			}
			console.log(topicArray);
		}
		jQuery.ajaxSettings.traditional = true;

		$.ajax({
			
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			type:"post",
			data:{
				"locArray":locArray,
				"topicArray":topicArray,
				"minAge":checkMinAge,
				"maxAge":checkMaxAge,
				"time":checkTime,
				"sortCheck":sortCheck,
				"cPage":cPage
				
			},
			datatype:"html",
			success:function(data){
				$('#dongle-container').html(data);
				
			}
			
		})
		
	})
	
	$(document).on('keyup','#searchKeyword',function(){
		
		var keyword=$('#searchKeyword').val();
		console.log(keyword);
		
		$.ajax({
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			data:{
				"keyword":keyword,
				"sortCheck":sortCheck,
				"cPage":cPage
			},
			type:"post",
			datatype:"html",
			success:function(data){
				
				$('#dongle-container').html(data);
			}
		})
		
	})
	
	$(document).on('change','#sortBar',function(){
		
		sortCheck=$('#sortBar option:selected').val();
		console.log(sortCheck);
		
		jQuery.ajaxSettings.traditional = true;

		$.ajax({
			
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			type:"post",
			data:{
				"locArray":locArray,
				"topicArray":topicArray,
				"minAge":checkMinAge,
				"maxAge":checkMaxAge,
				"time":checkTime,
				"sortCheck":sortCheck,
				"cPage":cPage
				
			},
			datatype:"html",
			success:function(data){
				$('#dongle-container').html(data);
				
			}
			
		})
	
	})
	
	$(document).on('click','.page-bar',function(){
		cPage=$(this).children('input').val();
		console.log(cPage);
		
		jQuery.ajaxSettings.traditional = true;

		$.ajax({
			
			url:"<%=request.getContextPath()%>/main/mainSearchEnd",
			type:"post",
			data:{
				"locArray":locArray,
				"topicArray":topicArray,
				"minAge":checkMinAge,
				"maxAge":checkMaxAge,
				"time":checkTime,
				"sortCheck":sortCheck,
				"cPage":cPage
				
			},
			datatype:"html",
			success:function(data){
				$('#dongle-container').html(data);
				
			}
			
		})
		
	});
	
	
	$(document).ready(function(){
		
		$(document).on('click','.group-img',function(){
			var groupNo=$(this).siblings('input').val();
			
			location.href="<%=request.getContextPath()%>/communityJoin?groupNo="+groupNo;
			
		})
		
	});

	
</script>
<body>
<hr>
<div class="search-container" >
	<div class='search-menu' style="border: 1px dotted darkgray;">
		<div class="condition-container" style="border : red">
			<form style="widht:400px" id='search-condition'>	
				<fieldset>
					<span style="font-size: 20px;font-weight: bold;">시간대 :</span>
					<span>
						<label style="font-size: 17px; font-weight: lighter;">
							<input type="checkbox" id="time" name="time" value="평일"/>평일
						</label>
						<label style="font-size: 17px; font-weight: lighter;">
							<input type="checkbox" id="time" name="time" value="주말"/>주말</label>
					</span> &nbsp;&nbsp; &nbsp;&nbsp;
					<span style="font-size: 20px;font-weight: bold;">연령대 : </span><!-- <input type="range" id="age-group" name="age-group" min="0" max="100"/></label> -->
					<label style="font-size: 17px; font-weight: lighter;">
						<select id="min-age">
							<option value="0">최소 연령</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
							<option value="60">60</option>
							<option value="70">70</option>
							<option value="80">80</option>
							<option value="90">90</option>
						</select>
						<select id="max-age">
							<option value="0">최대연령</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
							<option value="50">50</option>
							<option value="60">60</option>
							<option value="70">70</option>
							<option value="80">80</option>
							<option value="90">90</option>
						</select>
					</label>
					
				</fieldset>
			</form>
		</div>
		<div class="topic-container">
			<h3>분야별 검색 설정</h3>
			<div id='topic-select'>
				<ul>
				<%for(TopicCategory tc:topicList) {%>
					<li><label><input class="topic" type="checkbox" value="<%=tc.getTopicCtgCode()%>"><%=tc.getTopicCtgName()%></label></li>
				<%} %>
				</ul>
			</div>
			
		</div>
		<div class="location-container">
			<h3>지역검색설정</h3>
				<ul class="tabs">
					
					<li class="tab-link"><input class="loc-code" type="hidden" value="L1">서울특별시</li>
					<li class="tab-link"><input class="loc-code" type="hidden" value="L2">인천광역시</li>
					<li class="tab-link"><input class="loc-code" type="hidden" value="L3">대전광역시</li>
					<li class="tab-link"><input class="loc-code" type="hidden" value="L4">대구광역시</li>
					<li class="tab-link"><input class="loc-code" type="hidden" value="L5">광주광역시</li>
					<li class="tab-link"><input class="loc-code" type="hidden" value="L6">울산광역시</li>
					<li class="tab-link"><input class="loc-code" type="hidden" value="L7">부산광역시</li>
					<li class="tab-link"><input class="loc-code" type="hidden" value="L8">세종특별시</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L9">강원도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L10">경기도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L11">충청남도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L12">충청북도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L13">전라남도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L14">전라북도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L15">경상남도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L16">경상북도</li>
					<li class="tab-link2"><input class="loc-code" type="hidden" value="L17">제주도</li>

				</ul>
				<hr>
				<ul class="tabs" id="do">
					
				</ul>
				<div id="tab-1">
					<ul>
					
					</ul>
				</div>
		</div>
	</div>
	<h2>동글검색</h2>
	<div id="search-container">
		<div id="search-groupName">
			<form action="<%=request.getContextPath() %>/admin/memberFinder">
				<input type="hidden" name="searchType" value="groupName"/>
				<input type="text" name="searchKeyword" size="100" placeholder="검색할 동글을 입력하세요" id='searchKeyword'/>
										
				<select id="sortBar" style="height:25px">				
					<option value="date">창설 날짜</option>
					<option value="gender">회원수</option>				
				</select>	
			</form>
		</div>				
	</div>
	<div id="dongle-container">
		<div id="dongle-container-back">
			<ul>
		<%for(int i=0; i<groupList.size();i++){%>
			<li class="dongle-list">
				<div class='search_dongle_main_img'>
					<input type="hidden" value="<%=groupList.get(i).getGroupNo() %>">
					<img class="group-img" src="<%=request.getContextPath()%>/images/dongle_main_img/<%=groupList.get(i).getGroupMainNewImgPath()%>" >
				</div>
				<div class='search_dongle_info'>
					<p><strong>동글 명:</strong> <%=groupList.get(i).getGroupName() %></p>
					<%for(GroupMemberCount gmc:memberCountList){
						if(gmc.getGroupNo()==groupList.get(i).getGroupNo()){	
						%>
					<p><strong>동글 회원수:</strong> <%=gmc.getMemberCount() %></p>
					<%}} %>
					<p><strong>동글 창설 날짜:</strong> <%=groupList.get(i).getGroupEnrollDate() %>
					<ul><strong>동글 활동 지역:</strong>
						<%for(MultiLocationName mln:locationList){ 
							if(mln.getGroupNo()==groupList.get(i).getGroupNo()&&mln!=null){
						%>
						<li><%=mln.getLocCtgName() %></li>
						<%}} %>
					</ul>
					<ul><strong>동글 활동 주제:</strong>
						<%for(MultiTopicName mtn:topicList2){ 
							if(mtn.getGroupNo()==groupList.get(i).getGroupNo()&&mtn!=null){
						%>
						<li><%=mtn.getTopicCtgName() %></li>
						<%}} %>
					</ul>
				</div>
			</li>
		<%} %>
			</ul>
		</div>
	</div>
	<div id='page-bar-back'>
		<ul id='page-bar'>
			<%if(pageBar!=null){ %>
			<%=pageBar %>
			<%} %>
		</ul>
	</div>
</div>	
</body>

</html>