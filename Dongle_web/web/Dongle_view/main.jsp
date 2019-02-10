<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.vo.EditPickGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="header.jsp"%>

<%	
	List<Group> list=(List)request.getAttribute("list");
	List<EditPickGroup> editList=(List)request.getAttribute("editList");
	List<Group> rankList=(List)request.getAttribute("rankList");
%>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Dongle_Main.css" />
    <style>
        div.body-a {font: normal 0.9em Arial;color:#222;}
        div.header-a {display:block; font-size:1.2em;margin-bottom:100px;}
        div.header-a a, div.header-a span {
            display: inline-block;
            padding: 4px 8px;
            margin-right: 10px;
            border: 2px solid #000;
            background: #DDD;
            color: #000;
            text-decoration: none;
            text-align: center;
            height: 20px;
        }
        div.header-a span {background:white;}
        a {color: #1155CC;}
    </style>
    <Script>
		$(function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/editpickForm2",
				dataType:"html",
				type:'post',
				success:function(data){
					$('#edit_test').html(data);
				}
			});
		});
	</Script>
<section>

	<div class="bar">
		<%if(loginMember.getMemberId().equals("admin")) {%>
		<button class="img-icon" onclick="location.href='<%=request.getContextPath() %>/admin/memberList'">
				<span class="sub-icon">관리자 메뉴</span>
				<img class="create-img" src="<%=request.getContextPath()%>/images/button-images/editSetting2.png">		
		</button>
		<%} %>
		<!-- 동글 개설하기 버튼! -->
		<div class="cre-icon-back">
			<button class="img-icon">
				<span class="sub-icon" style="margin-bottom: 2px;">동글개설하기</span>
				<img class="create-img" style="width: 30px; height: 28px;" src="<%=request.getContextPath()%>/images/button-images/addDongle.png">		
			</button>
		</div>
	</div>
	<h2 class="item-logo">MY DONGLE</h2>
	<!-- 가입한 동글 캐러셀 -->
	<div class="carousel-back">
		<!-- 이전 버튼 -->
        <button class="left"><</button>
        <!--캐러셀 아이템   -->
        <div class="carousel-box">
                <ol class="item">
                    <% if(list.size()==0||list.isEmpty()||loginMember.getMemberId().equals("admin")){%>
                    	<!-- 동글에 가입하지 않았을 경우 혹은 관리자 일 경우-->
                    	<li>DONGLE 에 가입하세요!</li>
                    <%}else{ 
                    	for(Group g:list){
                    %>
                    	<!-- 가입한 동글 리스트 -->

                    	<form action="<%=request.getContextPath()%>/communityJoin" name="join" method='post'>

                    	<li class="dongle-icon">
                    		<div class="icon-back">
                    			<button class="join-btn" type="submit"> 
                    			
                    			<!-- 여기서 그룹 넘버 전송 -->
                    				<span class="group-name"><%=g.getGroupName() %></span>
                    				<img class="icon" src="<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupImageNewPath()%>"/>

                    				<input type="hidden" name="groupNo" value="<%=g.getGroupNo()%>"/>
									<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo() %>"/>
                    			</button>
                    		</div>
                    	</li>
                    	</form>
                    	
                    <%} 
                    }
                    %>
	
                </ol>
			
    	</div>
		<!-- 다음 버튼 -->
    	<button class="right">></button>
    </div>
	<hr>
	<!-- 에디터 픽 캐러셀 -->
	<h2 style="font-family:'YanoljaYacheR'; text-align:left;margin-left:100px;display:inline-block;margin-top:-20px;">HOT</h2>
	<h1 style="font-family:'YanoljaYacheR'; text-align:left;display:inline;margin-top:-20px;text-shadow: 1px 1px 2px gray;">  &nbsp;에디터 추천</h1>
	<div id='edit_test' style='width:1024px;height:400px;margin-top:-80px;'>
	
	</div>
	<hr>
<%-- 	<!-- 분야별 랭킹 컨텐츠 -->
	<div class="bar"></div>
	<h4 style="font-family: 'netmarble Medium'; text-align:center; font-weight: bold;">당신을 위한 가장 인기있는 동글!</h4>
	<h2 class="item-logo">DONGLE'S RANKING</h2>
	<div class="dongle-rank" style='height:auto;'>
		
		<% if(rankList!=null) {
			for(Group g :rankList){%>
		<div class="dongle-ranker" >
			
			<div class="ranker-img-back">
				<form action="<%=request.getContextPath()%>/communityJoin?=<%=g.getGroupNo()%>&<%=loginMember.getMemberNo() %>">
				<!-- 여기서 그룹 넘버 전송 -->
					<div class="ranker-img"> 
						<!-- 순위권 동글 리스트 -->
						<button class="join-btn" type="submit">
						<img class="rImg" src="<%=request.getContextPath()%>/images/group_profile/<%=g.getGroupImageNewPath()%>"/>
						<input type="hidden" name="groupNo" value="<%=g.getGroupNo()%>"/>
						<input type="hidden" name="groupNo" value="<%=loginMember.getMemberNo()%>"/>
						</button>
					</div>
				</form>
			</div>
			<div class="ranker-intro">
					<!-- 동글 소개 컨텐트 -->			
					<p class="intro-content">
						<%=g.getGroupName() %><br>
						동글 소개:<%=g.getGroupIntro() %><br>
					</p>
			</div>
		</div>
		<%	}
		}%>
		
	</div> --%>
	
</section>
<script src="<%=request.getContextPath()%>/Dongle_js/Dongle_main.js">
</script>

<style>
.dialog{
   display:none;
   position:absolute;
   margin-top:-1050px;
   z-index:10;
   left:0;                                                                                                   
   right:0;
   width:100%;
   height:auto;
   overflow:hidden;
   background-color:rgb(0,0,0);
   background-color:rgba(0,0,0,0.4);
}
.modal-content {
       background-color: #fefefe;
       margin: 15% auto; 
       padding: 20px;
       border: 1px solid #888;
       width: 40%;  
       border-radius: 5px;
   }
</style>

<div class="modal-div">
	<div class="dialog2" id="modal-container">
		<div class="modal-content2">
		</div>
   	</div>
</div>    
<%@ include file="footer.jsp"%>