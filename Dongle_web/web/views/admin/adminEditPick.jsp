<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.ListGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 
<%@ include file="/Dongle_view/header.jsp"%>
<%@ page import='com.dongle.group.model.vo.EditPickGroup,java.util.*' %>
<%
	List<EditPickGroup> editList = (List)request.getAttribute("editList");
%>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="<%=request.getContextPath() %>/3/ninja-slider.css" rel="stylesheet" />
    <script src="<%=request.getContextPath() %>/3/ninja-slider.js"></script>
    <link href="<%=request.getContextPath() %>/3/thumbnail-slider.css" rel="stylesheet" type="text/css" />
    <script src="<%=request.getContextPath() %>/3/thumbnail-slider.js" type="text/javascript"></script>
    <style>
        body {font: normal 0.9em Arial;color:#222;}
        /* header {display:block; font-size:1.2em;margin-bottom:100px;} */
        header a, header span {
            display: inline-block;
            padding: 4px 8px;
            margin-right: 10px;
            text-decoration: none;
            text-align: center;
            height: 20px;
        }
        a {
       	text-decoration:none;
       	color:rgb(220,220,220);
       	font-size:17px;
       	font-family: 'netmarble Medium';
       	 position: absolute;
	     z-index: 3;
	     opacity: 0.9;
	    /*  text-align: center;	 */
       	}
       	a:hover{
       		color:white;
       	}
        
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
<!-- <body> -->
<div style="width:1000px;margin:80px auto;">
        <div id="ninja-slider" style="float:left;">
            <div class="slider-inner">
                <ul  style='width:600px; height:400px;'>
                    <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(0).getEditFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(0).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                    <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(1).getEditFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(1).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(2).getEditFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(2).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(3).getEditFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(3).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(4).getEditFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:35px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(4).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                                        <li>
                    	<a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(5).getEditFilePath() %>">
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
                        <a class="thumb" href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(0).getEditFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(1).getEditFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb"  href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(2).getEditFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(3).getEditFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(4).getEditFilePath() %>"></a>
                    </li>
                    <li>
                        <a class="thumb" href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(5).getEditFilePath() %>"></a>
                    </li>
                </ul>
            </div>
        </div>
        <div style="clear:both;"></div>
    </div>
    <h2>변경할 에디터픽 선택</h2>
	<div  style='margin-left:10px;display:inline;position:relative;width:auto;height:auto;'>
		<%for(int j=0;j<6;j++){ %>
			<%if((j+1)%3==1){ %>
			<br>
			<%} %>
			<div class='edit_cover' style='radius:5px;margin-left:10px;padding:0;margin-right:40px;width:250px;height:auto;display:inline-block;position:relative;'>
				<h6><%=j+1 %>번 에디터 픽</h6>
				<input type='hidden' name='edit-num' id='edit-num'/>
				<img style='width:250px;height:200px;display:inline;padding:5px;' id='<%=editList.get(j).getGroupNo() %>' class="edit_img" src="<%=request.getContextPath() %>/images/editor_images/<%=editList.get(j).getEditFilePath()%>"><br>
				<p><%=editList.get(j).getEditContent() %>'</p>
			</div>
		<%} %>
	</div>
    <div id='edit_insert_content' style='width:600px;height:auto;margin-left:50px;margin-top:50px;margin-bottom:30px;'>
		<div id='edit_group_no'>
		
		</div>
		<div class='input_wrap'>
			<br>
			<label>메인 이미지:<input type='file' id='input_edit_img' name='input_imgs' multiple/></label>
		</div>
		<div class='edit_textarea'>
			<textarea name="input_edit_text" id='input_edit_text' cols='100' rows='4' style=' resize:none;margin-top:10px;' placeholder="내용을 입력해주세요." tabindex='3'></textarea>
			<br><br>
			<button style='float:right;display:inline-block;margin-right:100px;' name='edit_update' id='edit_update'>확인</button>
		</div>
	</div>
	
<script>
	$(function(){
		var en;
		var numdiv = $('#edit_group_no');
		var editImg = $('.edit_img');
		$('.edit_img').click(function(){
			editImg.css('border','none');
			numdiv.empty();
			$(this).css('border','2px solid rgb(112,136,172)');
			en=$(this).get(0).id;
			var html='<p>선택한 에디터 픽 동글번호: ';
			html+=en+"</p>";
			html+"<input type='hidden' name='groupNo' id='groupNo' value='"+en+"'>";
			numdiv.append(html);
		});
		
		$('#edit_update').click(function(){
			var gNo = $('#groupNo').val();
			console.log(gNo);
			var data = new FormData();
			data.append('groupNo',gNo);
			data.append('editContext',$('#input_edit_text').val());
			$.ajax({
				url:"<%=request.getContextPath()%>/edit/updateEditPick",
				data:data,
				type:'post',
				processData:false,
				contentType:false,
				success:function(data){}
			});
		});
	});
	

</script>

<%@ include file="/Dongle_view/footer.jsp"%>


</html>
