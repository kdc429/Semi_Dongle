<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당신을 위한-DONGLE</title>
<link href="<%=request.getContextPath()%>/Dongle_css/Login.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Bungee" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Sunflower:300" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Bungee" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Sunflower:300" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>

    <style>

        .tex{
            border-radius: 3px;
            width: 280px;
            height: 30px;
            margin: 5px;
        }
    
        #emp{
            background-color: rgb(96, 192, 173);
            border-radius : 10px;
            margin: 150px auto;
            padding: 30px;
            left: 30%;
            width: 550px;
            /* position: absolute;
            left: 685px;
            top: 300px; */
        }
    
        .btn{
            width: 100px;
            margin-left: 200px;
            padding-right: 80px;
            display: inline-block;
            margin-top: 30px;
        }
    
        .btn_s{
            width: 100px;
            height: 30px;
        }
        
        .logo-text {
        font-family: 'Do Hyeon', sans-serif; 
        color: white;
        font-size: 50px;
        margin-left: 110px;
        margin-bottom: 50px;
        margin-top: 40px;
        letter-spacing: 40px;
    }

    #line{
        border:6px solid rgb(249, 247, 232);
        width: 500px; 
        border-radius: 10px;
        padding: 20px;
    }

    th{
        font-family: 'Do Hyeon', sans-serif; 
        color: white;
    }
    
    span{
        color: red;
    }


    @media ( max-width : 480px)
     {
        #enroll-container,#emp,#tab{
            width: auto;
         }

     }
    </style>
</head>
<body>

	<script>
		function fn_enroll_validate(){
			
			if($('input[name=idValid]')[0].value=='0')
			{
				alert('아이디 중복체크를 해주세요!');
				return false;	
			}
			var userId=$("#userId_");
			if(userId.val().length<4)
			{
				alert("최소 4자리 이상 입력하세요!");
				userId.focus();
				return false;
			}
			return true;			
		};
		$(function(){
			$("#password_2").blur(function(){
			
				var p1=$("#password_").val(), p2=$("#password_2").val();
				if(p1!=p2){
					alert("패스워드가 일치하지 않습니다.");
					$("#password_2").val('');
					$("#password_").focus();
				}
			});
		});
		
		//아이디 중복검사하기 : 팝업창을 띄워서 해보자~! 
		function fn_checkduplicate(){
			var userId=$("#userId_").val().trim();
			if(!userId || userId.length<4)
			{
				alert("아이디를 4글자 이상 입력하세요~!");
				return;	
			}
			//팝업창에 대한 설정해주기!@
			var url="<%=request.getContextPath()%>/checkIdDuplicate";
			var title="checkIdDuplicate";
			var shape="left=200px, top=100px, width=300px, height=200px";
			
			var popup=open("",title,shape);
			
			//현재페이지에 있는값을 새창으로 옮기는 작업~!
			checkIdDuplicateFrm.userId.value=userId;
			//popup창에서 이 폼을 작동시키게 하는 구문!
			checkIdDuplicateFrm.target=title;
			checkIdDuplicateFrm.action=url;
			checkIdDuplicateFrm.method="post";
			checkIdDuplicateFrm.submit();		
			
			//window.open(url,"명칭/여는방식",shape)
			

		}
		
	
	</script>

	    <section id='enroll-container'>
        <div id="emp">
        <div id="line">
        <div class="logo-text">회원가입</div>
            <form name='memberEnrollFrm' action="<%=request.getContextPath()%>/memberEnrollEnd" 
            method="post" onsubmit="return fn_enroll_validate();">
                <table id="tab">
                    <tr>
                        <th><span>* </span>아이디</th>
                        <td>
                            <input type="text"  class="tex" placeholder="4글자이상" 
                            name="userId" id="userId_"  class="tex" required>
                            <input type="button" value="중복검사" 
                            onclick="fn_checkduplicate();"/>
                            <input type='hidden' name="idValid" value="0"/> 
                        </td>
                    </tr>
                    <tr>
                        <th><span>* </span>패스워드</th>
                        <td>
                            <input type="password" class="tex"  
                            name="password" id="password_" required>
                        </td>
                    </tr>
                    <tr>
                        <th><span>* </span>패스워드확인</th>
                        <td>
                            <input type="password" class="tex"  
                            id="password_2" required>
                        </td>
                    </tr>
                    <tr>
                    <th><span>* </span>비밀번호 힌트&nbsp;</th>
                    <td>
                        <select id='pwdHint' name='pwdHint' class="tex" required/>
                            <option value='나의 고향은?'>나의 고향은?</option>
                            <option value="나의 보물1호는?">나의 보물1호는?</option>
                            <option value='나의 어머니 이름은?'>나의 어머니 이름은?</option>
                            <option value="나의 아버지 이름은?">나의 아버지 이름은?</option>
                            <option value='어릴 적 친했던 친구는?'>어릴 적 친했던 친구는?</option>
                            <option value="가장 좋아했던 캐릭터는?">가장 좋아했던 캐릭터는?</option>
                        </select>
                    </td>
                    </tr>
                    <tr>
                        <th><span>* </span>힌트 답변&nbsp;</th>
                            <td>
                                <input type="text" class="tex" id="hintAnswer" name='hintAnswer' required/>
                            </td>
                        </tr>
                    <tr>
                        <th><span>* </span>이름</th>
                        <td>
                            <input type="text" class="tex"  
                            id="userName" name="userName" required>
                        </td>
                    </tr>
                    <tr>
                        <th><span>* </span>성별</th>
                        <td>
                            <input type="radio"  
                            id="gender0" name="gender" value='M' >
                            <label for="gender0">남</label>
                            <input type="radio"  
                            id="gender1" name="gender" value='W' >
                            <label for="gender1">여</label>
                        </td>
                    </tr>			
                    <tr>
                        <th><span>* </span>생년월일</th>
                        <td>
                            <input type="text"  class="tex" 
                            id="age" name="age" 
                            placeholder="931230" required>
                        </td>
                    </tr>			
                
                    <tr>
                        <th><span>* </span>휴대폰</th>
                        <td>
                            <input type="tel"  class="tex"
                            id="phone" name="phone" maxlength="11"
                            placeholder="(-없이)01012345678" required>
                        </td>
                    </tr>
                    <tr>
                        <th><span>* </span>주소</th>
                        <td>
                            <input type="text"  class="tex"
                            id="address" name="address" required>
                        </td>
                    </tr>
                    <tr>
                        <th><span>* </span>이메일</th>
                        <td>
                            <input type="email"   class="tex"
                            id="email" name="email" 
                            placeholder="db123@dfd.com" required>
                        </td>
                    </tr>

    <!-- 				<tr>ss
                        <th>취미</th>
                        <td>
                            <input type="checkbox"  
                            id="hobby0" name="hobby" value='운동' >
                            <label for="hobby0">운동</label>
                            <input type="checkbox"  
                            id="hobby1" name="hobby" value='등산' >
                            <label for="hobby1">등산</label>
                            <input type="checkbox"  
                            id="hobby2" name="hobby" value='독서' >
                            <label for="hobby2">독서</label>
                            <input type="checkbox"  
                            id="hobby3" name="hobby" value='게임' >
                            <label for="hobby3">게임</label>
                            <input type="checkbox"  
                            id="hobby4" name="hobby" value='여행' >
                            <label for="hobby4">여행</label>
                        </td>
                    </tr>	 -->														
                </table>
                
                <div class="btn">
                    <input type="submit" class="btn_s" value="가입"/>
                </div>
    
            </form>
            </div>
        </div>
            <form action="" name="checkIdDuplicateFrm">
                <input type="hidden" name="userId"/>
            </form>	
        
        </section>

</body>
</html>