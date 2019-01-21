<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<meta charset="UTF-8">
<title>Insert title here</title>

    <style>

        #dongle_join{
            border: 20px solid rgb(144, 202, 135);
            width: 500px;
            height: 400px;
            margin-left: 700px;
            margin-top: 50px;  
        }
        
       .image_p{
            border: 2px solid black;
            width: 150px;
            height: 150px;
            border-radius: 40px;
            margin-left: 175px;
            margin-top: 50px;
        }

        .list{
            width: 300px;
            height: 50px;
           margin-top: 50px;
           margin-left: 75px;
        }
        #dongle_nickname{
            width: 180px;
            height: 25px;
        }

        .subm{
           position: absolute;
           left: 950px;
           top: 650px;
        }
        
        #upfile{
            padding-left: 215px;
            padding-top: 10px;
        }
        
        #dongle_title{
        	font-size: 70px;
        	margin-left: 820px;
        	margin-top: 150px;
        }
        /* 등러가는 이미지 style 클래스명 */
        .selProductFile{
            width: 145px;
            height: 145px;
            border-radius: 40px;
            padding:3px;
        }
    </style>
    
</head>
<body>


<div id="dongle_title">동글 가입</div>
	<form name='donglememberjoin' action="<%=request.getContextPath()%>/donglememberjoin" method="post" enctype="multipart/form-data">
		<section>
	    <div id="container">
	        <div id="dongle_join">
	            <div class="image_p"></div>
	            <input type="file" id="upfile" class="upfile" name="upfile" >
	            <div class="list">
	                <table>
	                    <tr>
	                        <th>닉네임 &nbsp;&nbsp;&nbsp;&nbsp; </th>
	                        <td>
	                            <input type="text" name="dongle_nickname" id="dongle_nickname" required/>
	                        </td>
	                    </tr>
	                    <tr class="subm">
	                        <td colspan='2'>
	                            <input type="submit" onclick="return password_validate();" value="가입"/>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	    </div>
		</section>
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

	</Script>

</body>
</html>