<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.dongle.feed.model.vo.*,com.dongle.group.model.vo.*,com.dongle.member.model.vo.*,com.dongle.feed.model.service.*,com.dongle.group.model.service.*" %>
<%
	List<Feed> feedList=(List)request.getAttribute("feedList");
	Group g=(Group)request.getAttribute("group");
	Member loginMember=(Member)request.getSession().getAttribute("loginMember");
	List<GroupMember> memberList=(List)request.getAttribute("memberList");
	GroupMember groupMember=(GroupMember)request.getAttribute("groupMember");
	List<FeedFile> feedFileList=(List)request.getAttribute("feedFileList");
	List<FeedComment> feedCommentList=(List)request.getAttribute("feedCommentList");
	List<FeedComment> feedLevel2CommentList=(List)request.getAttribute("level2FeedCommentList");
	List<ReportReason> reportCategory=(List)request.getAttribute("reportCategory");
%>

<html>

<meta charset="UTF-8">

<link href="<%=request.getContextPath()%>/css/feed.css" rel="stylesheet"/>
	<script>
	
	$('.feed-content').each(function () {
		  this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
		}).on('input', function () {
		  this.style.height = 'auto';
		  this.style.height = (this.scrollHeight) + 'px';
	});
	$('#feed-content-up').each(function () {
		  this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
		}).on('input', function () {
		  this.style.height = 'auto';
		  this.style.height = (this.scrollHeight) + 'px';
	});
	$(document).ready(function() {
		// 스크롤 발생 이벤트 처리
		$(document).on("scroll",document,function() {
			
			var lengthFeed=$('.feed').length; //현재 피드 개수
			var scrollT = $(document).scrollTop(); // 스크롤바의 상단위치
			var scrollH = $(document).height();
			var maxHeight = $('.main').height(); // 스크롤을 갖는 태그의 최하단
			var scrollMax=$(document).height() - $(window).height() - $(window).scrollTop();
			console.log(lengthFeed);
			console.log(maxHeight);
			console.log(scrollMax);
			
			// 스크롤바가 맨 아래에 위치할 때	
			if (scrollMax<1&&lengthFeed%10==0) {
				console.log("이벤트");
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedAdd?groupNo=<%=g.getGroupNo()%>&currentFeed="+lengthFeed,
					type:"get",
					dataType:"html",
					success:function(data){
						
						
						$('.news').append(data);
						console.log(lengthFeed);
						if(lengthFeed%10==0){

							$('.newsfeed').append($('#fountainG'));//로딩 div 뒤로 넘기기
						}else{
							console.log("sdj");
							$('#fountainG').remove(); //로딩 div 지우기
						}
						
					}
					
				});
			}						
		});
	});
	//버튼 누르면 file input버튼 click되는 이벤트
	$('#pic-up-btn').click(function(){
		$('#feed-pic-up').click()
		
	});
	$('#video-up-btn').click(function(){
		$('#feed-video-up').click()
		
	});
	$('#file-up-btn').click(function(){
		$('#feed-file-up').click()
	});
	
	//수정할때 지우는 파일 이벤트
	var sel_files=[];
	$(document).ready(function(){
		$("#feed-pic-up").on("change",feedImgsFileSelect);
	});
	$(document).ready(function(){
		$("#feed-video-up").on("change",feedMidisFileSelect);
	});
	
	function feedImgsFileSelect(e){
		var files=e.target.files;
		var filesArr=Array.prototype.slice.call(files);
		console.log(files);
		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다.");
				return;
			}
			console.log(f)
			sel_files.push(f);
			
			var reader=new FileReader();
			reader.onload=function(e){
				var imgHtml='<li><img src=\"'+e.target.result+'\"/></li>';
				$("#imgPreview>ul").append(imgHtml);
			}
			
			reader.readAsDataURL(f);
		})
	}
	function feedMidisFileSelect(e){
		var files=e.target.files;
		var filesArr=Array.prototype.slice.call(files);
		console.log(files);
		filesArr.forEach(function(f){
			if(!f.type.match("video.*")){
				alert("확장자는 비디오 확장자만 가능합니다.");
				return;
			}
			console.log(f)
			sel_files.push(f);
			
			var reader=new FileReader();
			reader.onload=function(e){
				var imgHtml='<li><video src=\"'+e.target.result+'\"></video></li>';
				$("#imgPreview>ul").append(imgHtml);
			}
			
			reader.readAsDataURL(f);
		})
	}
	
	//피드 업로드
	$(document).ready(function(){
		$('#feedup').click(function(){
			
			var fd=new FormData();
			var imgBtn=$('#feed-pic-up'); //이미지파일 추가 버튼
			var videoBtn=$('#feed-video-up'); //영상 파일 추가 버튼
			var fileBtn=$('#feed-file-up'); //일반 텍스트 파일 추가 버튼
			var feedContentUp=document.feedFrm.feedContentUp.value; //입력받은 피드 컨텐트 내용
			var groupNo=<%=g.getGroupNo()%>;
			var memberNo=<%=loginMember.getMemberNo()%>;
			var resultFeedNo=0;
			var newFeed=$('.feednew');
			var filesLength=document.feedFrm.feedPicUp.files+feedFrm.feedVideoUp.files+feedFrm.feedFileUp.files.length;
			if(feedContentUp==""){
				//피드 내용 없을시 예외처리
				alert("피드 내용을 입력해주세요!");
				console.log($('.feednew'));
				return;
			}
			//formdata에 내용 추가
		    var feedFd=new FormData();
			feedFd.append('groupNo',groupNo);
			feedFd.append('memberNo',memberNo);
	  
		    if(document.feedFrm.feedContentUp.value){
		    	feedFd.append('content',feedContentUp);
		    }
		    
			if(document.feedFrm.feedPicUp.value){
				
				var imageExt = document.feedFrm.feedPicUp.value; //파일을 추가한 input 박스의 값

				imageExt = imageExt.slice(imageExt.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

				if(imageExt != "jpg" && imageExt != "png" &&  imageExt != "gif"){ //확장자를 확인합니다.

					alert('사진 파일은 이미지 파일(jpg, png, gif)만 등록 가능합니다.');
					console.log(document.feedFrm.feedPicUp.value);
					return;

				}else{
					
					for(var i=0;i<document.feedFrm.feedPicUp.files.length;i++){
						feedFd.append('image'+i,document.feedFrm.feedPicUp.files[i]);
					}
				}
			}
			if(document.feedFrm.feedVideoUp.value){
				
				var videoExt = document.feedFrm.feedVideoUp.value;
				console.log(videoExt);
				//파일을 추가한 input 박스의 값

				videoExt = videoExt.slice(videoExt.indexOf(".") + 1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.

				if(videoExt != "mp4" && videoExt != "ogg"){ //확장자를 확인합니다.

					alert('영상 파일은 (jpg, png, gif) 만 등록 가능합니다.');

					return;

				}else{
					
					for(var i=0;i<document.feedFrm.feedVideoUp.files.length;i++){
						feedFd.append('video'+i,document.feedFrm.feedVideoUp.files[i]);
						console.log(feedFrm.feedVideoUp.files[i]);
						console.log(feedFd);
					}
				}
				
			}
			if(document.feedFrm.feedFileUp.value){
				
				for(var i=0;i<feedFrm.feedFileUp.files.length;i++){
					feedFd.append('files'+i,feedFrm.feedFileUp.files[i]);
				}
			}
	 		//피드 업로드 ajax
	 		$.ajax({
				url:"<%=request.getContextPath()%>/feed/feedContentUpload",
				data:feedFd,
				type:"post",
				dataType:"html",
				processData:false,
				contentType:false,
				success:function(data){ 
					console.log(data);
						newFeed.next().after(data);
						$('#feed-content-up').val("");
						$('#imgPreview').children().children().remove();
						setImage();
				}
				
			});
			
			
		});
	})
	
	$(document).ready(function(){
		
		$(document).on('click','.btn-insert',function(event){
			
			
			var groupNo=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.groupNo').val();//그룹넘버
			var feedNo=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedNo').val();//피드넘버
			var feedCommentWriterNo=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedCommentWriterNo').val();
			//피드 작성자 넘버
			var feedCommentLevel=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedCommentLevel').val();
			//피드 댓글 레벨
			var feedCommentRef=$(this).parent('.comment_btn').parent('.modal_comment').siblings('.feedCommentRef').val();
			//피드 댓글 참조 변수
			var feedCommentContent=$(this).parent('.comment_btn').siblings('.comment_write').children('.feedCommentContent').val();
			//피드 댓글 내용
			var commentLevel1=$(this).parent().parent().siblings('ul');
			//댓글 레벨 1 태그
			var commentLevel2=$(this).parent().parent().parent().parent();
			//댓글 레벨 2 태그
			var commentRepleTag=$(this).parent().parent();
			console.log(commentRepleTag);
			if(feedCommentContent==""){
				alert("댓글 내용을 입력해주세요!");
				return;
			}
			if(feedCommentLevel==1){
				//일반 댓글 입력시
				$.ajax({
					
					url:"<%=request.getContextPath()%>/feed/feedInsertComment",
					type:"post",
					data:{
							"groupNo":groupNo,
							"feedNo":feedNo,
							"feedCommentWriterNo":feedCommentWriterNo,
							"feedCommentLevel":feedCommentLevel,
							"feedCommentContent":feedCommentContent,
							"feedCommentRef":feedCommentRef
						},
					dataType:"html",
					contentType: "application/x-www-form-urlencoded",
					success:function(data){
						
						commentLevel1.append(data);
						
							
					}
				})
			}else if(feedCommentLevel==2){
				//댓글 밑 댓글 입력시
				console.log(feedCommentRef);
				$.ajax({
					
					url:"<%=request.getContextPath()%>/feed/feedInsertComment",
					type:"post",
					data:{
							"groupNo":groupNo,
							"feedNo":feedNo,
							"feedCommentWriterNo":feedCommentWriterNo,
							"feedCommentLevel":feedCommentLevel,
							"feedCommentContent":feedCommentContent,
							"feedCommentRef":feedCommentRef
						},
					dataType:"html",
					contentType: "application/x-www-form-urlencoded",
					success:function(data){
						
						commentLevel2.after(data);
						commentRepleTag.remove();
					}
				})
			}
			
		});
	})
	
	
	$(function(){
		//대댓글 입력창 열기
		var eventflag;
		$(document).on('click','.comment-reple',function(e){
			var feedNoReple=$(this).parent().parent().children('.feedCommentNo').val();
			var feedNo=$(this).parent().parent().parent().siblings('.feedNo').val();
			console.log(feedNo);
			<%if(loginMember!=null){%>
				eventflag=true;
				var div=$(this).parent().siblings('.recomment_content');
				var html="";
				html+="<input type='hidden' class='groupNo' value='<%=g.getGroupNo()%>'/>"
				html+="<input type='hidden' class='feedNo' value='"+feedNo+"'/>";
				html+="<input type='hidden' class='feedCommentWriterNo' value='<%=loginMember.getMemberNo()%>'/>";
				html+="<input type='hidden' class='feedCommentLevel' value='2'/>";
				html+="<input type='hidden' class='feedCommentRef' value='"+feedNoReple+"'/>";
				html+="<fieldset class='modal_comment'>";
				html+="<div class='comment_write'>";
				html+="<textarea name='feedCommentContent' class='feedCommentContent' placeholder='소중한 댓글을 입력해주세요' tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>";
				html+="</div>";
				html+="<div class='comment_btn'>";
				html+="<button type='button' class='btn-insert'> Send</button>";
				html+="</div>"
				html+="</fieldset>"
				div.html(html);
				div.insertAfter($(this).parent().siblings('.comment-content-back')).slideDown(800);
				/* 연결된 이벤트 삭제 */
				
				
				
				div.find('.btn-insert').click(function(e){
					if(<%=loginMember==null%>)
					{
					 	fn_loginAlert();
						e.preventDefault();
						return;
					}
				});	

			<%}%>
			})
		}); 
		function fn_loginAlert()
		{
			alert('로그인 후 이용할 수 있습니다.');
		}

		
		$(document).ready(function(){
			//수정 팝업 레이어 열기
			console.log($(this));
			$(document).on('click','.update-btn',function(event){
				var button=$(this);
				console.log(button);
				var elementTop=$(this).parent().parent();
				console.log("눌리나?");
				var feedPopup=document.getElementById("feed-popup");
				console.log(elementTop);
				var feedNo=$(this).siblings('.feed-no-update').val();
				console.log(feedNo);
				var _x = document.body.scrollLeft; //마우스로 선택한곳의 x축(화면에서 좌측으로부터의 거리)를 얻는다. 
				var _y = elementTop.offset().top; //마우스로 선택한곳의 y축(화면에서 상단으로부터의 거리)를 얻는다. 
				console.log(_x);
				console.log(_y);
				//if(_x < 0) _x = 0; //마우스로 선택한 위치의 값이 -값이면 0으로 초기화. (화면은 0,0으로 시작한다.) 
				//if(_y < 0) _y = 0; //마우스로 선택한 위치의 값이 -값이면 0으로 초기화. (화면은 0,0으로 시작한다.) 
	
				feedPopup.style.left = _x+"px"; //레이어팝업의 좌측으로부터의 거리값을 마우스로 클릭한곳의 위치값으로 변경. 
				feedPopup.style.top = _y+"px"; //레이어팝업의 상단으로부터의 거리값을 마우스로 클릭한곳의 위치값으로 변경. 

				feedPopup.style.visibility="visible";
				feedPopup.style.display="flex";
				
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedPopupView",
					type:"post",
					data:{"feedNo":feedNo},
					
					success:function(data){
						$('#feed-popup').html(data);
						
					}
					
				})
				
			})
			
			
		});
			//
		
		$(document).ready(function(){
			//피드 삭제 에이작스
			$(document).on('click','.delete-btn',function(){
				var feedNo=$(this).siblings('.feed-no-update').val();
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedDelete",
					type:"post",
					data:{"feedNo":feedNo},
					dataType:"html",
					success:function(data){
						if(data>0){
							alert('삭제완료!');
							$.ajax({
								url:"<%=request.getContextPath()%>/feed/feedListView?groupNo=<%=g.getGroupNo()%>&memberNo=<%=loginMember.getMemberNo()%>",
								type:"get",
								dataType:"html",
								success:function(dataHtml){
									$('#content-div').html(dataHtml);
									
								}
							})
						}else{
							alert("삭제 실패!");
						}
					}
				})
			})
		})
	
	
	$(document).ready(function(){
		
		$('.report-button').on('click',function(){
			var reNickName=$(this).parent().siblings('.content-writer-nick').val();
			console.log(reNickName);
			document.reportFrm.reportNickName.value=reNickName;
			var reportWin=window.open("<%=request.getContextPath()%>/views/common/reportMember.jsp","reportWin","width=300, height=300, menubar=no, status=no, toolbar=no");
			var feedNo=$(this).children('.content-feed-no').val();
			var groupNo=<%=g.getGroupNo()%>;
			var memberNo=$(this).children('.content-writer-no').val();
			console.log(memberNo);
			
			document.getElementById('reportGroupNo').value=groupNo;
			document.getElementById('reportMemberNo').value=memberNo;
			document.getElementById('reportFeedNo').value=feedNo;
			
			
		});
		
		
	})
	
	$(document).ready(function(){
		
		$('.report-comment-button').on('click',function(){
			var reNickName=$(this).children('.comment-writer-nick').val();
			console.log(reNickName);
			document.reportFrm.reportNickName.value=reNickName;
			var reportWin=window.open("<%=request.getContextPath()%>/views/common/reportMember.jsp","reportWin","width=300, height=300, menubar=no, status=no, toolbar=no");
			var feedCommentNo=$(this).children('.comment-no').val();
			var groupNo=<%=g.getGroupNo()%>;
			var memberNo=$(this).children('.comment-writer-no').val();
			document.getElementById('reportGroupNo').value=groupNo;
			document.getElementById('reportMemberNo').value=memberNo;
			document.getElementById('reportFeedCommentNo').value=feedCommentNo;
			
			
		});
		
		
	})
	
	
	</script>
	<div class="newsfeed">
		<br><br>
		<div class="news">
			<div class="feednew">
				<div class="feednew-logo"></div>
					<div>
						<form name="feedFrm" id="feedFrm" method="post" enctype="multipart/form-data">
							<textarea name="feedContentUp" id="feed-content-up" cols='75' rows="6" style="resize:none"></textarea>
							<div id="imgPreview">							
							<ul></ul>
							</div>
							
                    		<div class="fileup" >
                    			<div id="file-back">
                    				<button type="button" class="up-btn" id="pic-up-btn">
										<img class="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/camera-retro-solid.png">	
									</button>
									<button type="button" class="up-btn" id="video-up-btn">
										<img class="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/video-solid.png">
									</button>
									<button type="button" class="up-btn" id="file-up-btn">
										<img class="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/file-solid.png">
									</button>
									<input type="file" id='feed-pic-up' name='feedPicUp' class="fileup" multiple="multiple" accept=".gif, .jpg, .png" style='display: none;'/>
									<input type="file" id='feed-video-up' name='feedVideoUp' class="fileup" multiple="multiple" accept=".mp4,.ogg" style='display: none;'/>
                    				<input type="file" id='feed-file-up' name='feedFileUp' class="fileup" multiple="multiple" style='display: none;'/>
                    			</div>
                    			<div id="upload-back">
                    				<button type="button" id="feedup">
                       					<img id="upbtn-icon" src="<%=request.getContextPath()%>/images/button-images/pen-solid.png"/>Upload
                       				</button>
                    			</div>
                       			
                    		</div>
						</form>
                </div>
			</div>
			<hr>
            <% if(feedList!=null){ 
            	for(Feed f:feedList){%>
                   <!-- 피드 한칸 -->
                   <%if(f.getFeedReportStatus().equals("Y")) {
                   
                   }else{
                   %>
                   
        	<div class="feed">
            	<div class="feed-header">
		
              <% for(GroupMember gm:memberList){		
                	if(gm.getMemberNo()==f.getMemberNo()){%>
                	<img style="padding:10px 10px;" src="<%=request.getContextPath() %>/images/member_img/<%=gm.getGroupMemberImageNewPath() %>" class="member-profile">
                	<a><%=gm.getGroupMemberNickname() %></a>
                	<input type="hidden" class="content-writer-nick" value="<%=gm.getGroupMemberNickname() %>">
                	
                <%		break;
                		}
                	}%>
                	<span class="write-date"><%=f.getFeedWriteDate() %></span>
                	<div class="update-back">
            		<%if(f.getMemberNo()==loginMember.getMemberNo()){ %>
            			<input type="hidden" class="feed-no-update" value="<%=f.getFeedNo() %>"/>
            			<button class="delete-btn">
            				<img class="delete-icon" src="<%=request.getContextPath()%>/images/button-images/trash-alt-solid.png">	
            			</button>
            			<button class="update-btn">
            				<img class="update-icon" src="<%=request.getContextPath()%>/images/button-images/edit-solid.png">
            			</button>
            		<%}else{%>
            			<button class="report-button">
            				<input type="hidden" class="content-feed-no" value="<%=f.getFeedNo() %>"/>
            				<input type="hidden" class="content-writer-no" value="<%=f.getMemberNo() %>"/>
            				<img class="report-icon" src="<%=request.getContextPath()%>/images/button-images/report-solid.png">
            			</button>
            		<%} %>
            		</div>
            		<% if(feedFileList!=null){%>
 					<div class="download-back">
	            		<ul class="file-download">
 					
            		<%for(FeedFile ff:feedFileList){
            			if(ff.getFeedNo()==f.getFeedNo()){ %>
            				<li class="file-down-list" ><a href="<%=request.getContextPath()%>/feed/fileDownLoad?rName=<%=ff.getFeedNewFilePath()%>"><%=ff.getFeedOldFilePath()%></a></li>
            		<%		}
            			}%>

            			</ul>
            		</div>
            		<% }%>
            	</div>
            	<div class="feed-body">
            		
            		<textarea type="text" cols="73" class="feed-content" readonly><%=f.getFeedContent() %></textarea>
            		
            		<% for(FeedFile ff:feedFileList){
    						if(ff.getFeedNo()==f.getFeedNo()){%>
    				<div class="feed-pics">
    	                <button class="prev">❮</button>
    	                <ul class="media-carousel">
    				<%		break;
    						}
	            		}%>

            		<% for(FeedFile ff:feedFileList){ 
            			  if(ff.getFeedNo()==f.getFeedNo()&&ff.getFeedNewFilePath()!=null&&feedFileList.size()>0){
            				 if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("jpg")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("png")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("gif")){
            					%>
            			
                    		<li><img src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic"></li>
                    	
                    		<%}else if(ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("mp4")||ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1),ff.getFeedNewFilePath().length()).equals("ogg")){%>
                    			
                    		<li><video controls src="<%=request.getContextPath() %>/images/feed-images/<%=ff.getFeedNewFilePath() %>" class="feed-pic" type="video/<%=ff.getFeedNewFilePath().substring((ff.getFeedNewFilePath().lastIndexOf(".")+1))%>"></video></li>
                    		<%}%>
                    		
                    		
                    	<%}
            			}
            			%>
            				
            			<% for(FeedFile ff:feedFileList){
    						if(ff.getFeedNo()==f.getFeedNo()&&feedFileList.size()>0){%>	
                		</ul>
                		<button class="next">❯</button>
            		</div>
            		<div class="indi-wrap-div" style="text-align: center">
    
                		<ul class="indi-wrap">
                		<%
    							break;
    						}
	            		}
            			%>	
                		<% for(FeedFile ff:feedFileList){ 
            				if(ff.getFeedNo()==f.getFeedNo()&&feedFileList.size()>0){%>
            				<li class="feed-pic-indi"></li>
            			
                    	<%	}
            			}	%>
            			
            			<% for(FeedFile ff:feedFileList){
    						if(ff.getFeedNo()==f.getFeedNo()&&feedFileList.size()>0){%>	
                			
                		</ul>
    				</div>
                		<%
    							break;
    						}
	            		}
            			%>
        		</div>
        		<hr>
            	
            	<div class="feed-footer">
            		<div class="comment-back">
            			<ul>
            			<%for(FeedComment fc:feedCommentList){
            				if(fc.getFeedNo()==f.getFeedNo()&&fc.getFeedCommentReportStatus().equals("N")){ %>
                			<li class='level1' style="list-style:none;">
                				<input type="hidden" class="feedCommentNo" value="<%=fc.getFeCommentNo() %>"/>
                				<%for(GroupMember gm:memberList){
    									if(gm.getMemberNo()==fc.getMemberNo()&&fc.getFeCommentRef()==0){            					
                					%>
                    			<span class="profile-back">
                        			<img class="profile-img" src="<%=request.getContextPath() %>/images/member_img/<%=gm.getGroupMemberImageNewPath()%>">
                    			</span>
                    			<span class="comment-info">
                        			<span class="comment-writer"><%=gm.getGroupMemberNickname() %></span>
                        			<span class="comment-date"><%=fc.getFeCommentDate() %></span>
                        			<%if(fc.getMemberNo()!=loginMember.getMemberNo()){ %>
                        			<button class="report-comment-button">
                        				<input type="hidden" class="comment-writer-nick" value="<%=gm.getGroupMemberNickname() %>"/>
                        				<input type="hidden" class="comment-no" value="<%=fc.getFeCommentNo() %>"/>
                        				<input type="hidden" class="comment-writer-no" value="<%=fc.getMemberNo() %>">
                        				<img class="report-icon" src="<%=request.getContextPath()%>/images/button-images/report-solid.png">
                        			</button>
                        			<%} %>
                        			<button class="comment-reple">
                        				<img class="reple-icon" src="<%=request.getContextPath()%>/images/button-images/comments-solid.png">답글
                        			</button>
                    			</span>
                    			<span class="comment-content-back">	
                        			<span class="comment-content"><%=fc.getFeCommentContent() %></span>		
                    			</span>
                    			<div class='recomment_content'></div>
                			<%				
                					}
                				}
    									%> 
                			</li>
                			<%for(FeedComment fcl2:feedLevel2CommentList) {
                				if(fcl2.getFeedCommentReportStatus().equals("Y")){
                				}else{
                					
                				for(GroupMember gm:memberList){
                				if(fcl2.getMemberNo()==gm.getMemberNo()&&fc.getFeCommentNo()==fcl2.getFeCommentRef()){
                				
                			%>
                			<li class="level2" style="list-style:none;">
                    			<span class="profile-back">
                        			<img class="profile" src="<%=request.getContextPath()%>/images/member_img/<%=gm.getGroupMemberImageNewPath()%>">
                    			</span>
                    			<span class="comment-info">
                        			<span class="comment-writer"><%=gm.getGroupMemberNickname() %></span>
                        			<span class="comment-date"><%=fcl2.getFeCommentDate() %></span>
                        			<%if(fcl2.getMemberNo()!=loginMember.getMemberNo()){ %>
                        			<button class="report-comment-button">
                        				<input type="hidden" class="comment-writer-nick" value="<%=gm.getGroupMemberNickname() %>"/>
                        				<input type="hidden" class="comment-no" value="<%=fcl2.getFeCommentNo() %>"/>
                        				<input type="hidden" class="comment-writer-no" value="<%=fcl2.getMemberNo() %>">
                        				<img class="report-icon" src="<%=request.getContextPath()%>/images/button-images/report-solid.png">
                        			</button>
                        			<%} %>
                        			
                    			</span>
                    			<span class="comment-content-back">
                        			<span class="comment-content"><%=fcl2.getFeCommentContent() %></span>
                        			
                    			</span>
                			</li>
                			<%			}
                					}
                				}
                			}	
                			%>
            			
            			<%					}
            							}	
                					
                				
            					%> 
            			</ul>
            			<input type="hidden" name="groupNo" class='groupNo' value="<%=g.getGroupNo()%>"/>
            			<input type="hidden" name="feedNo" class="feedNo" value="<%=f.getFeedNo() %>"/>
            			<input type="hidden" name="feedCommentWriterNo" class='feedCommentWriterNo' value="<%=loginMember.getMemberNo() %>"/>
            			<input type="hidden" name="feedCommentLevel" class='feedCommentLevel' value="1"/>
            			<input type="hidden" name="feedCommentRef" class='feedCommentRef' value="0"/>
            			<fieldset class='modal_comment'>
                			<legend class='screen_out'>댓글쓰기 폼</legend>
                			<div class='comment_write'>
                    			<label for='comment' class='lab_write screen_out'>내용</label>
                    			<textarea name="feedCommentContent" class='feedCommentContent' placeholder="소중한 댓글을 입력해주세요" tabindex='3' style='resize:none;box-sizing: border-box;width:100%;height:80;border:1px solid #fff;'></textarea>
                			</div>
                			<div class='comment_btn'>
                    			<button type="button" class='btn-insert'>SEND</button>
                			</div>
            			</fieldset>
        			</div>
            	</div>
            </div>
            <hr>
						<% 		}	}
            				}	
            					
            			%>
			
		</div>
			<hr>
            <div id="fountainG">
				<div id="fountainG_1" class="fountainG"></div>
				<div id="fountainG_2" class="fountainG"></div>
				<div id="fountainG_3" class="fountainG"></div>
				<div id="fountainG_4" class="fountainG"></div>
				<div id="fountainG_5" class="fountainG"></div>
				<div id="fountainG_6" class="fountainG"></div>
				<div id="fountainG_7" class="fountainG"></div>
				<div id="fountainG_8" class="fountainG"></div>
			</div>
			
			<div id="feed-popup">
				
			</div>
		</div>
		<form id='reportFrm' name="reportFrm">
			<input type="hidden" name="reportNickName" value="">
			<input type="hidden" name="reportCommentNickName" value="">
			<input type="hidden" id="report1" name="report1" value="<%=reportCategory.get(0).getReportCode()%>">
			<input type="hidden" id="reason1" name="reason1" value="<%=reportCategory.get(0).getReportReason()%>">
			<input type="hidden" id="report2" name="report2" value="<%=reportCategory.get(1).getReportCode()%>">
			<input type="hidden" id="reason2" name="reason2" value="<%=reportCategory.get(1).getReportReason()%>">
			<input type="hidden" id="report3" name="report3" value="<%=reportCategory.get(2).getReportCode()%>">
			<input type="hidden" id="reason3" name="reason3" value="<%=reportCategory.get(2).getReportReason()%>">
			<input type="hidden" id="report4" name="report4" value="<%=reportCategory.get(3).getReportCode()%>">
			<input type="hidden" id="reason4" name="reason4" value="<%=reportCategory.get(3).getReportReason()%>">
			<input type="hidden" id="report5" name="report5" value="<%=reportCategory.get(4).getReportCode()%>">
			<input type="hidden" id="reason5" name="reason5" value="<%=reportCategory.get(4).getReportReason()%>">
			<input type="hidden" id="report6" name="report6" value="<%=reportCategory.get(5).getReportCode()%>">
			<input type="hidden" id="reason6" name="reason6" value="<%=reportCategory.get(5).getReportReason()%>">
			<input type="hidden" id="report7" name="report7" value="<%=reportCategory.get(6).getReportCode()%>">
			<input type="hidden" id="reason7" name="reason7" value="<%=reportCategory.get(6).getReportReason()%>">
			
			<input type="hidden" id="reportFeedNo" name="reportFeedNo" value="0">
			<input type="hidden" id="reportFeedCommentNo" name="reportFeedCommentNo" value="0">
			<input type="hidden" id="reportGroupNo" name="selectGroupNo" value="">
			<input type="hidden" id="reportMemberNo" name="selectMemberNo" value="">
			
			
		</form>
    <script src="<%=request.getContextPath()%>/Dongle_js/feed.js"></script>

</html>