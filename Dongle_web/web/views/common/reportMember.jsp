<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동글 멤버</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<script>
	
	
		window.onload=function(){
			
		var reportFrm=window.opener.document.getElementById('reportFrm');
		console.log(reportFrm);
		var reNickName=reportFrm.reportNickName.value;
		var reCommentNickName=reportFrm.reportCommentNickName.value;
		var reCode1=reportFrm.report1.value;
		var reCode2=reportFrm.report2.value;
		var reCode3=reportFrm.report3.value;
		var reCode4=reportFrm.report4.value;
		var reCode5=reportFrm.report5.value;
		var reCode6=reportFrm.report6.value;
		var reCode7=reportFrm.report7.value;
		var reason1=reportFrm.reason1.value;
		var reason2=reportFrm.reason2.value;
		var reason3=reportFrm.reason3.value;
		var reason4=reportFrm.reason4.value;
		var reason5=reportFrm.reason5.value;
		var reason6=reportFrm.reason6.value;
		var reason7=reportFrm.reason7.value;
		console.log(reNickName);
		document.getElementById('reportMemberNick').innerHTML=reNickName;
		document.getElementById('reportNo1').value=reCode1;
		document.getElementById('reportNo1').innerHTML=reason1;
		document.getElementById('reportNo2').value=reCode2;
		document.getElementById('reportNo2').innerHTML=reason2;
		document.getElementById('reportNo3').value=reCode3;
		document.getElementById('reportNo3').innerHTML=reason3;
		document.getElementById('reportNo4').value=reCode4;
		document.getElementById('reportNo4').innerHTML=reason4;
		document.getElementById('reportNo5').value=reCode5;
		document.getElementById('reportNo5').innerHTML=reason5;
		document.getElementById('reportNo6').value=reCode6;
		document.getElementById('reportNo6').innerHTML=reason6;
		document.getElementById('reportNo7').value=reCode7;
		document.getElementById('reportNo7').innerHTML=reason7;
		
	}
	
	$(document).ready(function(){
		$('#report').on('change',function(){
			
			window.opener.document.getElementById('selectRecode').value=document.getElementById('reportMemberNick').value;
			report();
		});
	})
	
	$(document).ready(function(){
		
		
		$('#report-close').on('click',function(){
			var feedNo=window.opener.document.getElementById('reportFeedNo').value;
			var feedCommentNo=window.opener.document.getElementById('reportFeedCommentNo').value;
			var groupNo=window.opener.document.getElementById('reportGroupNo').value;
			var memberNo=window.opener.document.getElementById('reportMemberNo').value;
			var reportCode=document.getElementById('reportMemberNick').value;
			console.log(feedNo);
			console.log(groupNo);
			console.log(feedCommentNo);
			if(feedCommentNo==0){
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedReport",
					type:"post",
					data:{
						"feedNo":feedNo,
						"groupNo":groupNo,
						"memberNo":memberNo,
						"reportCode":reportCode
						
					},
					success:function(data){
						if(data>0){
							alert("신고되었습니다.");
							
							$.ajax({
								url:"<%=request.getContextPath()%>/feed/feedListView",
								type:"post",
								data:{
									"groupNo":groupNo,
									"memberNo":memberNo	
								},
								
								dataType:"html",
								success:function(data2){
									console.log(data2);
									$(opener.document).find('#content-div').html(data2);
									setImage();
									self.close();
								}
								
							});
							
						}else{
							alert("신고 실패 하였습니다.")
						}
					}
				})
			}else if(feedNo==0){
				$.ajax({
					url:"<%=request.getContextPath()%>/feed/feedCommentReport",
					type:"post",
					data:{
						"feedCommentNo":feedCommentNo,
						"groupNo":groupNo,
						"memberNo":memberNo,
						"reportCode":reportCode
						
					},
					success:function(data){
						if(data>0){
							alert("신고되었습니다.");
							
							$.ajax({
								url:"<%=request.getContextPath()%>/feed/feedListView",
								type:"post",
								data:{
									"groupNo":groupNo,
									"memberNo":memberNo	
								},
								
								dataType:"html",
								success:function(data2){
									$(opener.document).find('#content-div').html(data2);
									setImage();
									self.close();
								}
								
							});
							
						}else{
							alert("신고 실패 하였습니다.")
						}
					}
				})
			}
			
		})
	})
	
</script>
	<div>
		<h2>신고 접수</h2>
	</div>
	<div>
		<div name="reportTarget">
			<span>신고대상</span>
			
			<span id="reportMemberNick" name="reportMemberNick"></span>
		</div>
		<div>
			<label>신고 사유
				<select id="report-reason" name="reportReason">
					
					<option id="reportNo1" name="reportNo1" value=""></option>
					<option id="reportNo2" name="reportNo2" value=""></option>
					<option id="reportNo3" name="reportNo3" value=""></option>
					<option id="reportNo4" name="reportNo4" value=""></option>
					<option id="reportNo5" name="reportNo5" value=""></option>
					<option id="reportNo6" name="reportNo6" value=""></option>
					<option id="reportNo7" name="reportNo7" value=""></option>
				</select>
			</label>
		</div>
	</div>
	<div>
		<button id="report-close">신고</button>
	</div>
</body>
</html>