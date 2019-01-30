<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.Group"%>
<%@ page import="com.dongle.group.model.vo.EditPickGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ page import="com.dongle.group.model.vo.TopicCtg, com.dongle.group.model.vo.LocalCtg"%>
<%@ include file="header.jsp"%>

<%	
	List<Group> list=(List)request.getAttribute("list");
	List<EditPickGroup> editList=(List)request.getAttribute("editList");
	List<TopicCtg> topicCtg = (List)request.getAttribute("topicCtg");
	List<LocalCtg> localCtg = (List)request.getAttribute("localCtg");
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
        input#address{display:"inline-block"}
		a.aTopic{padding-right:15px;color: rgb(50,50,50);position:relative;font-size:9pt;text-decoration:none;}
      a.aLocal{padding-right:15px;color: rgb(50,50,50);position:relative;font-size:9pt;text-decoration:none;}
      .image_p{
               border: 1px solid rgb(230,230,230);
               width: 120px;
               height: 120px;
               border-radius: 100px;
               margin-left: 180px;
              
       }
       a.aTopic:hover{color:rgb(50,50,50);}
       a.aLocal:hover{color:rgb(50,50,50);}
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
       div.form-group label{
          font-size:12pt;
       }
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

		var topicCnt = 0;
		var topicArr = [];
		var topicFlag = false;
		
		var locCnt = 0;
		var locArr = [];
		var locFlag = false;
		
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
		
		function fn_delTopic(index)
		{
			var spanId = "#topic-span" + index;
			
			$(spanId).remove();
			topicCnt--;
			
			
			topicArr.splice(index, 1);
			console.log("topicArr삭제" + topicArr);
			
		}
		
		
		
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
					console.log("토픽"  + locFlag);
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
		
		
		
	</Script>
<section>

	<div class="bar">
		<%if(loginMember.getMemberId().equals("admin")) {%>
		<button class="create-img" onclick="location.href='<%=request.getContextPath() %>/admin/memberList'" style="color: black; font-family: '여기어때잘난서체';">관리자 메뉴</button>
		<%} %>
		<!-- 동글 개설하기 버튼! -->
		<button id="create-dongle" data-toggle="modal" data-target="#change-dongle-modal" style="color: black; font-family: '여기어때잘난서체';">동글 개설</button>
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
				<form action="<%=request.getContextPath() %>/createDongle" method="post" enctype="multipart/form-data">
				<div class="modal-body" style="padding: 40px 50px;">
					
						<div class="form-group">
							
							<div class="image_p"></div>
							<label>이미지</label>
	           				<input type="file" id="upfile" class="upfile" name="upfile" required ><br>
							<label for="donglename">동호회 이름</label> 
								<input type="text" class="form-control" name="dongleName"id="dongleName" required> <br>
							<label for="topic">토픽</label><br> 
							<select	class="form-control" style="padding_bottm:2px; width:79%; display:inline" name="selectTopic" id="selectTopic" >
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
							
							<select	class="form-control" style="padding_bottm:2px; width:25.6%; display:inline" name="selectMetro" id="selectMetro" onchange="fn_metroChange()" >
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
									<input type="radio" name="activetime" value="주중" checked>주중
								</label> 
								<label class="radio-inline">
									<input type="radio" name="activetime" value="주말">주말
								</label> 
							</div>
							<br>
							<label for>연령대</label><br>
							
								<input type="number" class="form-control" style="width:20%; display:inline;"name="minAge" id="minAge" min="1" max="100" placeholder="최소" required>
							
							&nbsp~&nbsp
							
								<input type="number" class="form-control" style="width:20%; display:inline;" name="maxAge" id="maxAge" min="1" max="100" placeholder="최대" required>
							
							<br><br><br>
							
							<label>소개글</label>
							<textarea class="form-control" rows="5" name="intro" id="intro" required></textarea>
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
<script src="<%=request.getContextPath()%>/Dongle_js/Dongle_main.js">
</script>
<%@ include file="footer.jsp"%>
<div class="modal-div">
   <div class="dialog2" id="modal-container">
      <div class="modal-content2">
      </div>
      </div>
</div>  -