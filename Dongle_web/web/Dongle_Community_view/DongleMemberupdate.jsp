<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@ page import="com.dongle.member.model.vo.Member"%>

<%
   int groupNo=(int)request.getAttribute("groupNo");
   Member loginMember = (Member)session.getAttribute("loginMember");
%>


<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
#dongle_title h4{color:rgb(20,150,200);float:left;margin-left:20px;margin-top:70px;position:relative;display: block;}
#dongle_join {
	width: 500px;
    height: 400px;
    margin-left: 100px;
	position:relative;
	margin-top:120px;
	display: block;
	font-family: "여기어때잘난서체";
}

.image_p {
	border: 1px solid rgb(230,230,230);
	width: 150px;
	height: 150px;
	border-radius: 100px;
    margin-left: 155px;
    margin-top: 50px;
}


#nickname {
	width: 180px;
  	height: 30px;
}

.subm {
	position: absolute;
	left: 315px;
	top: 500px;
}

#upfile {
	padding-left: 120px;
    padding-top: 10px;
    font-family: "netmarble Medium";
}

#dongle_title {
	font-size: 40px;
    margin-left: 260px;
}
/* 등러가는 이미지 style 클래스명 */
.selProductFile {
	width: 145px;
    height: 145px;
    border-radius: 100px;
    margin-left:2px;
    margin-top:1px;
}

#nickbox {
	display: inline-block;
}


</style>


<script>
		function fn_enroll_validate(){
			
			if($('input[name=idValid]')[0].value=='0')
			{
				alert('닉네임 중복체크를 해주세요!');
				return false;	
			}
			var userId=$("#nickname");
			if(userId.val().length<2)
			{
				alert("최소 2자리 이상 입력하세요!");
				userId.focus();
				return false;
			}
			return true;			
		};
		
		
		//아이디 중복검사하기 : 팝업창을 띄워서 해보자~! 
		function fn_checkduplicate(){
			var nickname=$("#nickname").val().trim();
			if(!nickname || nickname.length<2)
			{
				alert("닉네임를 2글자 이상 입력하세요~!");
				return;	
			}
			//팝업창에 대한 설정해주기!@
			var url="<%=request.getContextPath()%>/NicknameCheck";
			var title="checkdongleDuplicate";
			var shape="left=200px, top=100px, width=300px, height=200px";
			
			var popup=open("",title,shape);
			
			//현재페이지에 있는값을 새창으로 옮기는 작업~!
			checkdongleDuplicateFrm.nickname.value=nickname;
			//popup창에서 이 폼을 작동시키게 하는 구문!
			checkdongleDuplicateFrm.target=title;
			checkdongleDuplicateFrm.action=url;
			checkdongleDuplicateFrm.method="post";
			checkdongleDuplicateFrm.submit();		
			
			//window.open(url,"명칭/여는방식",shape)
			

		}
		
	
	</script>


<div id="dongle_title"><h4><b>동글 정보수정</b></h4></div>
<form name='dongleMemberUpdate'
	action="<%=request.getContextPath()%>/dongleMemberUpdate" method="post"
	enctype="multipart/form-data">
	
			<div id="dongle_join">
				<div class="image_p"></div>
				<input type="file" id="upfile" class="upfile" name="upfile">
				<div class="form-group">
                	<input type="text" class="form-control" name="nickname" id="nickname" placeholder="닉네임" style="font-family:'나눔스퀘어라운드 Regular'; display:inline; margin-left:100px;" required>&nbsp;&nbsp;&nbsp;&nbsp;
                	<button style="width:70px; height:30px;" class="btn btn-default" type="button" onclick="fn_checkduplicate();">중복검사</button><br/><br/>
                	<button style="width:268px; height:30px; margin-left:100px;"class="btn btn-primary" type="submit" onclick="return password_validate();">정보 수정</button>
                	<button style="width:268px; height:30px; margin-left:100px;"class="btn btn-primary" type="button" onclick="fn_deleteDongleMember();">동글 탈퇴</button>
                	
           		</div>
           		<div class="form-group">
                    <input type='hidden' name="idValid" value="0"/> 
                    <input type='hidden' name="groupNo" value="<%=groupNo%>"/> 
                    <input type='hidden' name="memberNo" value="<%=loginMember.getMemberNo()%>"/>
           		</div>
				
				<%-- <div class="list">
					<div id="just"></div>
					<table id="nickbox">
						<tr>
							<th>닉네임 &nbsp;</th>
							<td><input type="text" name="nickname" id="nickname"
								required /> <input type="button" value="중복검사"
								onclick="fn_checkduplicate();" /> <input type='hidden'
								name="idValid" value="0" /> <input type='hidden' name="groupNo"
								value="<%=groupNo%>" /> <input type='hidden' name="memberNo"
								value="<%=loginMember.getMemberNo()%>" /></td>
						</tr>
						<tr class="subm">
							<td colspan='2'><input type="submit" onclick="return password_validate();" value="정보수정" /></td>
							<td><button type="button" id="delete-btn" onclick="fn_deleteDongleMember();">동글 탈퇴</button></td>
						</tr>
					</table>
				</div> --%>
			</div>
		
</form>

<form action="" name="checkdongleDuplicateFrm">
	<input type="hidden" name="nickname" />
</form>
<form action="<%=request.getContextPath() %>/deleteDongleMember"
	name="deleteSubmitFrm" method="post">
	<input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo() %>">
	<input type="hidden" name="groupNo" value="<%=groupNo %>">
</form>


<%-- 	<script>
	
	$('#upfile').click(function() {
	
		var upfile = $('#upfile').val();
		
	    $.ajax({
			url:"<%=request.getContextPath()%>/donglememberjoin",
			type:"post",
			data:{
				"upfile":upfile,
				},
			dataType:"html",
			success:function(data){																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																														
				$('#image_p').html(data);
			}
			})
		})
	</script> --%>


<script type='text/javascript'>
	   //이미지 정보들을 담을 배열 선언
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
	   function fn_deleteDongleMember(){
		  	
			var checkDelete = window.confirm("정말 탈퇴하시겠습니까?");
			console.log(checkDelete);
			if(checkDelete)
			{			
				deleteSubmitFrm.submit();		
			}
			
		}
				
			
	</Script>