<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.ListGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="/Dongle_view/header.jsp"%>
<%@ page import='com.dongle.group.model.vo.EditPickGroup,java.util.*' %>
<%
	List<EditPickGroup> editList = (List)request.getAttribute("editList");
	int newGroupNo=0;
	if(request.getAttribute("newGroupNo")!=null){
		newGroupNo=(int)request.getAttribute("newGroupNo");
	}
%>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="<%=request.getContextPath() %>/3/ninja-slider.css" rel="stylesheet" />
    <script src="<%=request.getContextPath() %>/3/ninja-slider.js"></script>
    <link href="<%=request.getContextPath() %>/3/thumbnail-slider.css" rel="stylesheet" type="text/css" />
    <script src="<%=request.getContextPath() %>/3/thumbnail-slider.js" type="text/javascript"></script>
    <style>

        
        .img-cover{
        	margin-top:300px;
		   position: absolute;
		   height: 100px;
		   width: 100%;
		   background-color: rgba(0, 0, 0, 0.5);                                                                 
		   z-index:2;
		}
        
	div.ns-img div.img-cover > div.edit-content{
	     position: absolute;
	     top:50%;
	     /* left:50%; */
	     transform: translate(-50%, -50%);                                                              
         font-size:17px;
		 font-family: 'netmarble Medium';
	     color: white;
	     z-index: 3;
	     
	     /* text-align: center; */
	}
        
    </style>
    
    <!-- 에디터픽 시작 -->
   	<div class="bar"></div>
	<ul class="nav nav-tabs" id="admin-menu" style="margin-top:10px;width:1024px;">
    	<li ><a href="<%=request.getContextPath()%>/admin/memberList">회원 리스트 관리</a></li>
    	<li><a href="<%=request.getContextPath()%>/admin/dongleList">동글 리스트 관리</a></li>
    	<li class="active"><a href="<%=request.getContextPath()%>/admin/editpickForm">메인 관리</a></li>
   		<li><a href="<%=request.getContextPath()%>/admin/blackMemberList">블랙 리스트 관리</a></li>
 	</ul>
 	<h3>현재 Main 에디터픽 화면</h3>
	<div id='edit_all_div' style="width:1000px;margin:80px auto;">
        <div id="ninja-slider" style="float:left;">
            <div class="slider-inner">
                <ul  style='width:600px; height:400px;'>
                    <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(0).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(0).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                    <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(1).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(1).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(2).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(2).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(3).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(3).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(4).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:35px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(4).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(5).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:35px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(5).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                </ul>
                <!-- <div class="fs-icon" title="Expand/Close"></div> -->
            </div>
        </div>
        <div id="thumbnail-slider" style="float:left;">
            <div class="inner">
                <ul>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(0).getEditNewFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(1).getEditNewFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb"  href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(2).getEditNewFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(3).getEditNewFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(4).getEditNewFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(5).getEditNewFilePath() %>"></a>
                    </li>
                </ul>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
    <h3 style='margin-top:20px;margin-bottom:-10px;'>변경할 에디터픽 선택</h3>
	<div  style='margin-left:10px;display:inline;position:relative;width:auto;height:auto;'>
		<%for(int j=0;j<6;j++){ %>
			<%if((j+1)%3==1){ %>
			<br>
			<%} %>
			<div class='edit_cover' style='radius:5px;margin-left:10px;padding:0;margin-right:40px;width:250px;height:auto;display:inline-block;position:relative;'>
				<h6><%=j+1 %>번 에디터 픽</h6>
				<input type='hidden' name='edit-num' id='edit-num'/>
				<img style='width:250px;height:200px;display:inline;padding:5px;' id='<%=editList.get(j).getGroupNo() %>' class="edit_img" src="<%=request.getContextPath() %>/upload/editPick/<%=editList.get(j).getEditNewFilePath()%>"><br>
				<p><%=editList.get(j).getEditContent() %>'</p>
			</div>
		<%} %>
	</div>
    <div id='edit_insert_content' style='width:600px;height:auto;margin-left:50px;margin-top:50px;margin-bottom:30px;'>
		<div id='edit_group_no'>
		
		</div>
		<%if(newGroupNo!=0){ %>
			<div>
				<p style='font-size: 20px;'>새로 변경 될 동글 번호: &nbsp;<%=newGroupNo %></p>
				<input type='hidden' id='new_group_no' value='<%=newGroupNo %>'>
			</div>
		<%} %>
		<div class='input_wrap'>
			<br>
			<label>메인 이미지:<input type='file' id='input_edit_img' name='input_edit_img' multiple/></label>
		</div>
		<div class='edit_textarea'>
			<textarea name="input_edit_text" id='input_edit_text' cols='70' rows='4' style=' resize:none;margin-top:10px;' placeholder="내용을 입력해주세요." tabindex='3'></textarea>
			<br><br>
			<button style='float:right;display:inline-block;margin-right:100px;' name='edit_update' id='edit_update'>확인</button>
		</div>
	</div>
	
<script>
	$(function(){
		var sel_files = [];
		var en;
		var numdiv = $('#edit_group_no');
		var editImg = $('.edit_img');
		$('.edit_img').click(function(){
			console.log(this);
			editImg.css('border','none');
			numdiv.empty();
			$(this).css('border','2px solid rgb(112,136,172)');
			en=$(this).get(0).id;
			var html="<p style='font-size: 20px;'>선택한 에디터 픽 동글번호: ";
			html+=en+"</p>";
			html+="<input type='hidden' name='groupNo' id='groupNo' value='"+en+"'>";
			numdiv.append(html);
		});
		
		$(document).ready(function(){
			$('#input_edit_img').on('change', handleImgFileSelect);
		});
		function handleImgFileSelect(e){
			//이미지 정보들을 초기화
			sel_files = [];
			var files=e.target.files;
			var filesArr=Array.prototype.slice.call(files);
			
			var index = 0;
			console.log(filesArr.length);
			if(filesArr.length>=2)
			{
				alert('이미지는 1개만 등록 가능합니다.');
				return false;
			}
			filesArr.forEach(function(f){
				if(!f.type.match("image.*")){
					alert('확장자는 이미지 확장자만 가능합니다.');
					return;
				}
				sel_files.push(f);
			})
		}
		$('#edit_update').click(function(e){
			
			var oldGno = $('#groupNo').val();
			if(<%=newGroupNo%>!=0){
				var newGno = $('#new_group_no').val();
			}
			console.log(oldGno+" : "+newGno);
			
			var data = new FormData();
			data.append('groupNo',oldGno);
			if(<%=newGroupNo%>!=0){data.append('newGroupNo',newGno);}
			data.append('editContext',$('#input_edit_text').val());
			for(var i=0, len=sel_files.length; i<len; i++){
				data.append("image_"+i, sel_files[i]);
			}
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/editPickUpdate",
				data:data,
				type:'post',
				processData:false,
				contentType:false,
				success:function(data){
					alert('Message: '+data);
					location.href="<%=request.getContextPath()%>/admin/editpickForm";
				}
			});
		});
	});
	

</script>

<%@ include file="/Dongle_view/footer.jsp"%>


</html>
