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
/*             border: 2px solid #000;
            background: #DDD;
            color: #000; */
            text-decoration: none;
            text-align: center;
            height: 20px;
        }
        header span {/* background:white; */}
        a {color: #1155CC;}
    </style>
<!-- <body> -->
<div style="width:1000px;margin:80px auto;">
        <div id="ninja-slider" style="float:left;">
            <div class="slider-inner">
                <ul  style='width:600px; height:400px;'>
                    <li><a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(0).getEditFilePath() %>"></a></li>
                    <li><a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(1).getEditFilePath() %>"></a></li>
                    <li><a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(2).getEditFilePath() %>"></a></li>
                    <li><a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(3).getEditFilePath() %>"></a></li>
                    <li><a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(4).getEditFilePath() %>"></a></li>
                    <li><a class="ns-img" style='width:600px; height:400px;' href="<%=request.getContextPath()%>/images/editor_images/<%=editList.get(5).getEditFilePath() %>"></a></li>
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
<!--     </body> -->
</html>




