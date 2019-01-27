<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.member.model.vo.*,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*" %>
<%
   Feed feed=(Feed)request.getAttribute("feed");
   List<FeedFile> feedFileList=(List)request.getAttribute("feedFileList");
   Member loginMember=(Member)request.getAttribute("loginMember");
   GroupMember groupMember=(GroupMember)request.getAttribute("groupMember");

%>
<html>

<meta charset="UTF-8">
<div id="close">
   <button id="close-btn">
      <img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/window-close-solid.png"/>
   </button>
</div>
<div id="popup-header">
   <img style="padding:10px 10px;" src="<%=request.getContextPath() %>/images/member_img/<%=groupMember.getGroupMemberImageNewPath() %>" class="member-profile">
   <span id="popupFeedWriter"><%=groupMember.getGroupMemberNickname()%></span> 
   <span id="popupFeedDate"><%=feed.getFeedWriteDate() %></span>
</div>
<div id="popup-body">
   <ul>
      <% for(FeedFile ff:feedFileList){
         if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){
         }else{
      %>
      <li>
         <input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">
         <%=ff.getFeedOldFilePath() %>
         <button class="delete-file">
            <img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/backspace-solid.png"/>
         </button>
      </li>
      <%}
      } %> 
   </ul>
   <textarea id="popupFeedContent" cols="75"><%=feed.getFeedContent()%></textarea>
   <ul id="file">
      <% for(FeedFile ff:feedFileList){ 
            if(ff.getFeedNewFilePath()!=null){
               if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")){
                  %>
                     
                    <li>
                       <input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">   
                       <img src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic">
                       <button class="delete-file">
                          <img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/backspace-solid.png">
                       </button>
                    </li>
                       
                    <%      
                 }else if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){%>
                             
                    <li>
                       <input type="hidden" class="feedFileNo" value="<%=ff.getFeedFileNo() %>">
                       <video controls src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic" type="video/<%=ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1))%>"></video>
                       <button class="delete-file">
                          <img class="popup-icon" src="<%=request.getContextPath() %>/images/button-images/backspace-solid.png">
                       </button>
                    </li>
               <%}else{%>
                          
                 <%}
                    }      
                     }%>
   </ul>
   <div id="updateImgPreview">
      <ul>
      
      </ul>
   </div>
</div>
<div id="popup-footer">
   <form name="upfeedFrm" id="upfeedFrm" method="post" enctype="multipart/form-data">
      
        <div class="fileup" >
            <div id="file-back">
               <button type="button" class="up-btn" id="pic-update-btn">
               <img class="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/camera-retro-solid.png">   
            </button>
            <button type="button" class="up-btn" id="video-update-btn">
               <img class="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/video-solid.png">
            </button>
            <button type="button" class="up-btn" id="file-update-btn">
               <img class="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/file-solid.png">
            </button>
            <input type="file" id='feed-pic-update' name='feedPicUpdate' class="fileup" multiple="multiple" accept=".gif, .jpg, .png" style='display: none;'/>
            <input type="file" id='feed-video-update' name='feedVideoUpdate' class="fileup" multiple="multiple" accept=".mp4,.ogg" style='display: none;'/>
               <input type="file" id='feed-file-update' name='feedFileUpdate' class="fileup" multiple="multiple" style='display: none;'/>
            </div>
            <div id="upload-back">
            
               <button type="button" id="submit">
                  <img id="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/pen-solid.png"/>Update
               </button>
            </div>
                                
        </div>
   </form>
</div>

<script>


$('#popupFeedContent').each(function () {
     this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
   }).on('input', function () {
     this.style.height = 'auto';
     this.style.height = (this.scrollHeight) + 'px';
});

$('#pic-update-btn').click(function(){
   $('#feed-pic-update').click()
   
});
$('#video-update-btn').click(function(){
   $('#feed-video-update').click()
   
});
$('#file-update-btn').click(function(){
   $('#feed-file-update').click()
});
//formdata에 내용 추가
   var feedFd=new FormData();
   var deleteFileList=[];
   var fileNoList={};
   var updateContent=document.getElementById("popupFeedContent").value;
   var feedNo=<%=feed.getFeedNo()%>;
   $(document).ready(function(){
      $('.delete-file').click(function(){
         var fileNo=$(this).siblings('.feedFileNo').val();
         deleteFileList.push(fileNo);
         $(this).parent().remove();
         return deleteFileList;
      })
   });
   
   var sel_files2=[];
   $(document).ready(function(){
      $("#feed-pic-update").on("change",feedUpImgsFileSelect);
   });
   $(document).ready(function(){
      $("#feed-video-update").on("change",feedUpMidisFileSelect);
   });
   
   function feedUpImgsFileSelect(e){
      var files=e.target.files;
      var filesArr=Array.prototype.slice.call(files);
      console.log(files);
      filesArr.forEach(function(f){
         if(!f.type.match("image.*")){
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
         }
         console.log(f)
         sel_files2.push(f);
         
         var reader=new FileReader();
         reader.onload=function(e){
            var imgHtml='<li><img src=\"'+e.target.result+'\"/></li>';
            $("#updateImgPreview>ul").append(imgHtml);
         }
         
         reader.readAsDataURL(f);
      })
   }
   function feedUpMidisFileSelect(e){
      var files=e.target.files;
      var filesArr=Array.prototype.slice.call(files);
      console.log(files);
      filesArr.forEach(function(f){
         if(!f.type.match("video.*")){
            alert("확장자는 비디오 확장자만 가능합니다.");
            return;
         }
         console.log(f)
         sel_files2.push(f);
         
         var reader=new FileReader();
         reader.onload=function(e){
            var imgHtml='<li><video src=\"'+e.target.result+'\"></video></li>';
            $("#updateImgPreview>ul").append(imgHtml);
         }
         
         reader.readAsDataURL(f);
      })
   }
   
   
   
   var imgBtn=$('#feed-pic-update'); //이미지파일 추가 버튼
   var videoBtn=$('#feed-video-update'); //영상 파일 추가 버튼
   var fileBtn=$('#feed-file-update'); //일반 텍스트 파일 추가 버튼
   var memberNo=<%=loginMember.getMemberNo()%>;
   var resultFeedNo=0;
   var groupNo=<%=groupMember.getGroupNo()%>;
   
   jQuery.ajaxSettings.traditional = true;
   $(document).ready(function(){
      
      $('#submit').click(function(){
         
         console.log("tlf?");
         feedFd.append('groupNo',groupNo);
         if(updateContent==""){
            //피드 내용 없을시 예외처리
            alert("피드 내용을 입력해주세요!");
            console.log($('.feednew'));
            return;
         }
         

          if(updateContent!=""){
             feedFd.append('updateContent',updateContent);
          }
          feedFd.append('feedNo',feedNo);
          
          for(var i=0;i<deleteFileList.length;i++){
             if(deleteFileList!=null){
                feedFd.append('deleteFileList'+i,deleteFileList[i]);
             }else{
                
             }
          }
          
         if(document.upfeedFrm.feedPicUpdate.value){
            
            var imageExt = document.upfeedFrm.feedPicUpdate.value; //파일을 추가한 input 박스의 값

            imageExt = imageExt.slice(imageExt.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

            if(imageExt != "jpg" && imageExt != "png" &&  imageExt != "gif"){ //확장자를 확인합니다.

               alert('사진 파일은 이미지 파일(jpg, png, gif)만 등록 가능합니다.');

               return;

            }else{
               
               for(var i=0;i<document.upfeedFrm.feedPicUpdate.files.length;i++){
                  feedFd.append('image'+i,document.upfeedFrm.feedPicUpdate.files[i]);
               }
            }
         }
         if(document.upfeedFrm.feedVideoUpdate.value){
            
            var videoExt = document.upfeedFrm.feedVideoUpdate.value;
            console.log(videoExt);
            //파일을 추가한 input 박스의 값

            videoExt = videoExt.slice(videoExt.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

            if(videoExt != "mp4" && videoExt != "ogg"){ //확장자를 확인합니다.

               alert('영상 파일은 (jpg, png, gif) 만 등록 가능합니다.');

               return;

            }else{
               
               for(var i=0;i<document.upfeedFrm.feedVideoUpdate.files.length;i++){
                  feedFd.append('video'+i,document.upfeedFrm.feedVideoUpdate.files[i]);
                  console.log(feedFrm.feedVideoUp.files[i]);
                  console.log(feedFd);
               }
            }
            
         }
         if(document.upfeedFrm.feedFileUpdate.value){
            
            for(var i=0;i<upfeedFrm.feedFileUpdate.files.length;i++){
               feedFd.append('files'+i,upfeedFrm.feedFileUpdate.files[i]);
            }
         }
         
         
         if(deleteFileList!=null){
            updateContent=document.getElementById("popupFeedContent").value;
            console.log(deleteFileList);
            $.ajax({
               url:"<%=request.getContextPath()%>/feed/feedUpdateEnd",
               type:"post",
               data:feedFd,
               processData:false,
               contentType:false,
               success:function(data){
                  if(data>0){
                     alert('수정완료!');
                     $.ajax({
                        url:"<%=request.getContextPath()%>/feed/feedListView?groupNo=<%=groupMember.getGroupNo()%>&memberNo=<%=loginMember.getMemberNo()%>",
                        type:"get",
                        dataType:"html",
                        success:function(dataHtml){
                           $('#content-div').html(dataHtml);
                           setImage();
                           
                        }
                     })
                  }else{
                     alert("수정 실패!");
                  }
               }
            });
         }
      })
   })
   
   $('#close-btn').on('click',function(){//레이어 창 닫기 이벤트
            console.log("눌리나?");
            var button="<button class='delete-btn'><img class='delete-icon' src='<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png'></button>";
            var feedPopup=document.getElementById("feed-popup");
            console.log(feedPopup);
            $('#feed-popup').removeAttr('style');
            
               
         })
   
</script>
</html>