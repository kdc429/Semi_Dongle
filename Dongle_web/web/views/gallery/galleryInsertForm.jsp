<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	int groupNo=(int)request.getAttribute("groupNo");
	String albumCode = (String)request.getAttribute("albumCode");
%>
<meta charset="EUC-KR">
<div>
	<div>
		<h2>이미지 미리보기</h2>
		<div class='input_wrap'>
			<a href='javascript:' onclick='fileUploadAction();' class='up_btn'>파일 업로드</a>
			<input type='file' id='input_img' multiple/>
		</div>
	</div>
	<div>
		<div class='imgs_wrap'>
			<img id='img'>
		</div>
	</div>
	<a href='javascript:' onclick='submitAction();' class='up_btn'>업로드</a>
</div>

<script type='text/javascript'>
	//이미지 정보들을 담을 배열 선언
	var sel_files = [];
	
	$(document).ready(function(){
		$('#input_imgs').on('change', handleImgFileSelect);
	});
	
	function fuleUploadAction(){
		console.log('fileUploadAction');
		$('#input_imgs').trigger('click');
	}
	
	function handleImgFileSelect(e){
		//이미지 정보들을 초기화
		sel_files = [];
		$('.imgs_wrap').empty();
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
				var html = "<a href='javascript:void(0);' onclick='deleteImageAction("+index+")' id='img_id_"+index+"'><img src='"+e.target.result+"' data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
				$(".imgs_wrap").append(html);
				index++;
			}
			reader.readAsDataURL(f);
		})
	}
</script>

<script>
//다중 파일 미리보기에서 특정 이미지만 삭제하기
function deleteImageAction(index){
	console.log("index: "+index);
	sel_files(index,1);
	var img_id = "#img_id_"+index;
	$(img_id).remove();
	console.log(sel.files);
}
</script>

<script>
//다중 파일 POST전송
// 선택된 이미지들을 서버로 업로드하기
function submitAction(){
	var data = new FormData();
	for(var i=0, len=sel_files.length; i<len; i++){
		var name = "image_"+i;
		data.append(name, sel_files[i]);
	}
	data.append("image_count",sel_files.length);
	
	var xhr=new XMLHttpRequest();
	xhr.open("POST","<%=request.getContextPath()%>/gallery/insertGalleryEnd",true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.onload=function(e){
		if(this.status==200)
		{
			console.log("Result : "+e.currentTarget.responseText);
		}
	}
	var param="groupNo=<%=groupNo%>&albumCode=<%=albumCode%>";
	xhr.send(data);
}


</script>