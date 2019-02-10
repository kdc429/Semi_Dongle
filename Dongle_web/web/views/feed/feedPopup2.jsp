<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dongle.feed.model.vo.*" %>
    <%
    	FeedFile ff=(FeedFile)request.getAttribute("feedFile");
    %>
<html>

<meta charset="UTF-8">
	<div id="close">
   		<button id="close-btn">
      		<img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/window-close-solid.png"/>
   		</button>
	</div>

	<div id="popup-body">
		<ul id="file">
      	<% 
            if(ff.getFeedNewFilePath()!=null){
               if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")){
                  %>
                     
                    <li>
                       <input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">   
                       <img src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic2">
                       
                    </li>
                       
                    <%      
                 }else if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){%>
                             
                    <li>
                       <input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">
                       <video controls src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic2" type="video/<%=ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1))%>"></video>
                      
                    </li>
               <%}else{%>
                          
                 <%}
                    }      
                     %>
   		</ul>
	</div>
	
	<script>
	 	$('#close-btn').on('click',function(){//레이어 창 닫기 이벤트
         	console.log("눌리나?");
         	var button="<button class='delete-btn'><img class='delete-icon' src='<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png'></button>";
         	var feedPopup=document.getElementById("feed-popup");
         	console.log(feedPopup);
         	$('#feed-popup').removeAttr('style');
             
      	})
	</script>
</html>