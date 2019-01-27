<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dongle.group.model.vo.*,com.dongle.main.model.vo.*, com.dongle.member.model.vo.Member" %>
<!DOCTYPE html>
<%
	List<Group> groupList = (List)request.getAttribute("groupList");
	Member loginMember = (Member)request.getAttribute("loginMember");
	//String Checked = 6
	
%>
<html>
<head>
<meta charset="UTF-8">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<style>
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

 a.white12bold:link { font-size : 12px;  color : #ffffff;  text-decoration : none; font-weight: bold;  }
 a.white12bold:visited { font-size : 12px;  color : #ffffff;  text-decoration : none; font-weight: bold;  }
 a.white12bold:active { font-size : 12px;  color : #ffffff;  text-decoration : none; font-weight: bold; }
 a.white12bold:hover { font-size :12px;  color : #ffffff;  text-decoration : none; font-weight: bold;  }
 a.grey12:link { font-size : 12px;  color : #666666;  text-decoration : none;   }
 a.grey12:visited { font-size : 12px;  color : #666666;  text-decoration : none;   }
 a.grey12:active { font-size : 12px;  color : #666666;  text-decoration : none;  }
 a.grey12:hover { font-size :12px;  color : #666666;  text-decoration : none;   }
 
 
.white12bold {
	font-size: 12px;
	font-weight: bold;
	color: #ffffff;
	line-height: 17px;
}
.grey12 {
	font-size: 12px;
	color: #666666;
	line-height: 18px;
	font-weight:normal;
}
.stitle{height:15px; color:#666666; font-size:11px; font-family:Tahoma, Verdana, MalgunGothic,"돋움", "굴림"; font-weight:bold; border-bottom:2px solid #cccccc}
.regtable {border-top:1px solid #eaeaea; margin:0 0 20px 0}
.regtable td{border-bottom:1px solid #eaeaea; padding:0 10px; font-family:Tahoma, Verdana, MalgunGothic,"돋움", "굴림"; font-size:11px;}

.find_btn a{display:block; background:url('/images/bts.gif') left 0; float:left; font-size:11px; font-family:Tahoma, Verdana, MalgunGothic,"돋움", "굴림"; color:#777; padding-left:6px; text-decoration:none; height:21px; cursor:pointer; margin-right:3px; overflow:hidden; margin:0 0 0 3px; text-align:center}
.find_btn a:hover{background:url('/images/bts.gif') left -21px; text-align:center}
.find_btn a span{display:block; float:left; background:url('/images/bts.gif') right 0; line-height:180%; padding-right:6px; height:21px; overflow:hidden; text-align:center}
.find_btn a:hover span{background:url('/images/bts.gif') right -21px; color:#000; text-align:center}
</style>
</head>
<body>
<table class='idPwdTitle' width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td bgcolor="#999999"  style="padding:5px 10px;" class="white12bold">아이디/비밀번호 찾기</td>
	</tr>
</table>
<table width="450" border="0" cellspacing="0" cellpadding="0" class="grey12">
	<tr>
		<td style="padding:20px 0 0 0">
			<table width="420" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding:15px; border-top:2px #cccccc solid; border-right:2px #cccccc solid; border-bottom:2px #cccccc solid; border-left:2px #cccccc solid;">
						<form name="id_find" id="id_find" method="post" action="<%=request.getContextPath()%>/idFindEnd">
						  <table width="380" border="0" cellspacing="0" cellpadding="0">
  							<tr>
  								<td class="stitle">아이디 찾기</td>
  							</tr>
  						  </table>
  						<table width="380" border="0" cellspacing="1" class="regtable">
  							<tr>
  								<td width="100" height="25" bgcolor="#f4f4f4">이름</td>
  								<td width="130">
  									<input type="text" name="userName" id="userName" tabindex="1"/>
  								</td>
  								<td rowspan="2" align="center">
  									<div class="find_btn" >
  										<!-- <button type="button" class="btn btn-default" onclick="fn_findId();">아이디 찾기</button> -->
  										<input type="submit" value="아이디 찾기"/>  									
  									</div>
  								</td>
  							</tr>
  							<tr>
  								<td height="25" bgcolor="#f4f4f4">e-Mail</td>
  								<td>
  									<input type="text" name="userEmail" id="userEmail" tabindex="2"/>
  								</td>
  							</tr>
						</table>
					  </form>		

						<form name="pw_find" id="pw_find" method="post" action="<%=request.getContextPath()%>/pwdFindEnd">
  						<table width="380" border="0" cellspacing="0" cellpadding="0">
  						  <tr>
  						  		
  								<td class="stitle">비밀번호 찾기</td>
  							</tr>
  						</table>
  						<table width="380" border="0" cellspacing="1" class="regtable">
  							<tr>
  								<td width="100" height="25" bgcolor="#f4f4f4">ID</td>
  								<td width="130">
  									<input type="text" name="userId" id="userId" tabindex="5"/>
  								</td>
  								<td rowspan="4" align="center">
  									<div class="find_btn">
  										<input type="submit" value="비밀번호 찾기"/>
  										</a>
  									</div>
  								</td>
  							</tr>
  							<tr>
  								<td height="25" bgcolor="#f4f4f4">e-Mail</td>
  								<td>
  									<input type="text" name="userEmail" id="userEmail" tabindex="6"/>
  								</td>
  							</tr>
  							 <tr>
  								<td height="25" bgcolor="#f4f4f4">비밀번호힌트</td>
  								<td>
				 					<select id='pwdHint' name='pwdHint' tabindex="7" value="">
										<option value='나의 고향은?' selected=''>나의 고향은?</option>
										<option value="나의 보물1호는?">나의 보물1호는?</option>
										<option value='나의 어머니 이름은?'>나의 어머니 이름은?</option>
										<option value="나의 아버지 이름은?">나의 아버지 이름은?</option>
										<option value='어릴 적 친했던 친구는?'>어릴 적 친했던 친구는?</option>
										<option value="가장 좋아했던 캐릭터는?">가장 좋아했던 캐릭터는?</option>
									</select>
  								</td>
  							</tr>
  							<tr>
  								<td height="25" bgcolor="#f4f4f4">힌트 답</td>
  								<td>
  									<input type="text" name="hintAnswer" id="hintAnswer" tabindex="8"/>
  								</td>
  							</tr>
  						</table>
            </form>
            
            
            
					</td>
				</tr>
			</table>
			<table border="0" align="right" cellpadding="0" cellspacing="0">
				<tr>
					<td height="40" style="padding:0 13px 0 0">
						<div class='find_btn'><button type="button" class="btn btn-default" onclick="self.close();">닫기</button></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<script>
	function fn_findId(){
		var userName=$('#userName');
		var userEmail=$('#userEmail');
		if(userId.val().length<1 || userEmail.val().length<1)
			{
				alert("이름과 이메일을 입력하세요!");
				userName.focus();
				return false;
			}
		return true;
	}
	
	function(){
		
		$("pwdHint option:eq(1)").attr();
	}
</script>
</body>
</html>