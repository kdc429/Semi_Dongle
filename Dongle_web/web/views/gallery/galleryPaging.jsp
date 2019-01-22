<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

JSP-자바스크립트


<script>
function fn_List_popup(data,num) {

	var id = data;

	var page = num; //현재페이지

	var countList = 5; // 페이지에 게시물 수 (고정)

	var countPage = 5; // 리스트 페이지수 (고정)

	var totalCount; // 총게시물갯수 = selectListTotCnt

	var totalPage; // 생성될 전체 페이지 수

	var startPage;

	var endPage;

	

	$.ajax({

	    type:"POST",

	    url:_ctx+"/usr/selectListTotCnt.json",

	    data: {'id':id },

	    success: function(data) {

	    	totalCount=data.resultTotCnt; // 총게시물 갯수 설정

	    	totalPage = Math.floor(totalCount / countList); // 게시물의 리스트갯수 계산

	    	if (totalCount % countList > 0) {

			    totalPage++;	// 나머지가 있으면 전체페이지수가 +1이 된다.

			}

	    	var pageMath= Math.floor(page/5.0000001)+1;

	    	startPage = (pageMath-1)*5+1;//((page - 1) / 10) * 10 + 1; 

	    	endPage = totalPage;

	    	var totalPageMath = Math.floor(totalPage/5.0000001)+1;

	    	

	    	if(totalPage<6){

	    		$("#paging2").html("");

	    		for(var i=startPage; i<=totalPage; i++){

	    			$("#paging2").append("<a href='#' onclick='fn_List_popup

("+id+","+i+")'>&nbsp;"+i+"&nbsp;</a>");

	    		}

	    	} else if( totalPageMath == pageMath ){

	    		$("#paging2").html("");

	    		$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+",1)'> << </a>");

	    		$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+","+(startPage-5)+")'> &nbsp;<&nbsp; </a>");

	    		for(var i=startPage; i<=totalPage; i++){

	    			$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+","+i+")'>&nbsp;"+i+"&nbsp;</a>");

	    		}

	    	} else if(totalPage > countPage){

	    		$("#paging2").html("");

	    		if(pageMath != 1){

		    		$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+",1)'> << </a>");

		    		$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+","+(pageMath-1)+")'> &nbsp;<&nbsp; </a>");

	    		}

	    		for(var i=startPage; i<=startPage+4; i++){

	    			$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+","+i+")'>&nbsp;"+i+"&nbsp;</a>");

	    		}

	    		$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+","+(startPage+5)+")'> &nbsp;>&nbsp; </a>");

	    		$("#paging2").append("<a href='#' onclick='fn_List_popup("+dprtmId+","+endPage+")'> >> </a>");

	    	}

	    	

	    	var firstIndex = countList*(page-1)+1;

	    	var lastIndex = page*countList;

	    	

	    	$(".List").html("");

	    	$(".popup").show(); //팝업오픈

	    		//데이터 가져오기

	    	$.ajax({

	    	    type:"POST",

	    	    url:_ctx+"/usr/selectList.json",

	    	    data: {'dprtmId':dprtmId, 'firstIndex':firstIndex, 'lastIndex':lastIndex },

	    	    success: function(data) {

	    	    	$.each(data.resultList,function(index,value){

	    	    		$(".popup .popupTable tbody").append('<tr class="List"><td>'+(firstIndex)+'</td><td>'+value.id+'</td><td class="bor_r_0">'+value.nm+'</td></tr>');

	    	    		firstIndex++;

	    	    	});

	    	    }

	    	})

	    	

	    	

	    }

	})

	

}
</script>


JSP-HTML



<a href="#" class="grayBtn2" onclick="fn_List_popup('<c:out value="${result.id}"/>','1')">조회</a></td>



<!-- 팝업 -->

<div class="popup" style="display:none;">

    <h1 class="popupT">유저정보<span class="closeBtn"></span></h1>

    <div class="popupCon">

        <table class="popupTable">

            <caption class="blind"></caption>

            <colgroup>

                <col width="20%">

                <col width="40%">

                <col width="40%">

            </colgroup>

            

            <thead class="">

                <tr>

                    <th scope="col">NO</th>

                    <th scope="col">ID</th>

                    <th scope="col"  class="bor_r_0">이름</th>

                </tr>

            </thead>

            

        </table>

            <div id="paging2"></div>

	   

    </div>

</div>
</body>
</html>