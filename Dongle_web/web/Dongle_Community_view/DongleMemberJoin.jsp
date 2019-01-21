<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
        
        #image_p{
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
    </style>
    
</head>
<body>


<div id="dongle_title">동글 가입</div>
	<form name='donglememberjoin' action="<%=request.getContextPath()%>/donglememberjoin" method="post" enctype="multipart/form-data">
		<section>
	    <div id="container">
	        <div id="dongle_join">
	            <div id="image_p"></div>
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
	
	<script>
	
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
	</script>

</body>
</html>