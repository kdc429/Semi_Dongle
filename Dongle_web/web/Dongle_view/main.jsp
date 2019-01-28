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
		<button class="create-img" onclick="location.href='<%=request.getContextPath() %>/admin/memberList'" style="color: black; font-family: '여기어때잘난서체';">관리자 메뉴</button>
		<%} %>
		<!-- 동글 개설하기 버튼! -->
		<button class="search-btn" style="color: black; font-family: '여기어때잘난서체';">동글 개설</button>
	</div>
	<h2 class="item-logo">MY DONGLE</h2>
	<!-- 가입한 동글 캐러셀 -->
	<div class="carousel-back">
		<!-- 이전 버튼 -->
        <button class="left">
        	<img src='<%=request.getContextPath()%>/images/button-images/left-btn.png' style='width: 30px;'>
        </button>
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
    	<button class="right"><img src='<%=request.getContextPath()%>/images/button-images/right-btn.png' style='width: 30px;'></button>
    </div>
	<hr>
	<!-- 에디터 픽 캐러셀 -->
	<h2 style="font-family:'YanoljaYacheR'; text-align:left;margin-left:100px;display:inline-block;margin-top:-20px;">TODAY's</h2>
	<h1 style="font-family:'YanoljaYacheR'; text-align:left;display:inline;margin-top:-20px;text-shadow: 1px 1px 2px gray;">  &nbsp;에디터 추천</h1>
	<div id='edit_test' style='width:1024px;height:400px;margin-top:-80px;'>
	
	</div>
	<hr>
	
</section>
<script src="<%=request.getContextPath()%>/Dongle_js/Dongle_main.js">
</script>
<%@ include file="footer.jsp"%>
<div class="modal-div">
   <div class="dialog" id="modal-container">
      <div class="modal-content">
      </div>
      </div>
</div> 