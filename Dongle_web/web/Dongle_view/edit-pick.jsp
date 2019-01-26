<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dongle.group.model.vo.ListGroup"%>
<%@ page import="com.dongle.group.model.service.GroupService"%>
<%@ page import="java.util.*" %> 

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
       	color:rgb(240,240,240);
       	font-size:17px;
       	font-family: 'netmarble Medium';
       	 position: absolute;
	     z-index: 3;
	     opacity: 0.9;
	    /*  text-align: center;	 */
       	}
       	a:hover{
       		color:white;
   			text-shadow:1px 1px 2px gray;
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
<div id='edit_all_div' style="width:1000px;margin:80px auto;">
        <div id="ninja-slider" style="float:left;">
            <div class="slider-inner">
                <ul  style='width:600px; height:400px;'>
                    <li>
                    	<a id='<%=editList.get(0).getGroupNo() %>' class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(0).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(0).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                    <li>
                    	<a id='<%=editList.get(1).getGroupNo() %>' class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(1).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(1).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                    <li>
                    	<a id='<%=editList.get(2).getGroupNo() %>' class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(2).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(2).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                    <li>
                    	<a id='<%=editList.get(3).getGroupNo() %>' class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(3).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:25px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(3).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                    <li>
                    	<a id='<%=editList.get(4).getGroupNo() %>' class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(4).getEditNewFilePath() %>">
                    		<div class="img-cover">
                    			<div class='edit-content' style='margin-top:35px;margin-left:10px;opacity:1;'>Editor:<br>&nbsp;&nbsp;<%=editList.get(4).getEditContent() %></div>
                    		</div>
                   		</a>
               		</li>
                    <li>
                    	<a id='<%=editList.get(5).getGroupNo() %>' class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/upload/editPick/<%=editList.get(5).getEditNewFilePath() %>">
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
    
   <script>
   	$(function(){
   		var po;
   		$('.ns-img').click(function(e){
   			po = $(this).get(0).id;
   			location.href="<%=request.getContextPath()%>/communityJoin?groupNo="+po;
   		});
   	});
   </script>
</html>




