<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
%>
<%@ include file="header.jsp"%>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Dongle_css/Dongle_Main.css" />
	

<section>
	
	<!-- 가입한 동글 캐러셀 -->
	<div class="carousel-back">

		<!-- 이전 버튼 -->
        <button class="left"><</button>
        <!--캐러셀 아이템   -->
        <div class="carousel-box">
                <ol class="item">
                    <li class="first">
                        <button class="dongle-icon">7</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li class="5">
                        <button class="dongle-icon">7</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li class="10">
                        <button class="dongle-icon">7</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">7</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">7</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">7</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">7</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li>
                        <button class="dongle-icon">1</button>
                    </li>
                    <li class="last">
                        <button class="dongle-icon">7</button>
                    </li>

                </ol>

    	</div>
		<!-- 다음 버튼 -->
    	<button class="right">></button>
    </div>
	<hr>
	<!-- 에디터 픽 캐러셀 -->
	<div class="editorPick">
		에디터 픽
		<div id="carousel_section">
			<ul>
				<li>에디터 픽 이미지 1</li>
				<li>에디터 픽 이미지 2</li>
				<li>에디터 픽 이미지 3</li>
				<li>에디터 픽 이미지 4</li>
			</ul>
		</div>
		<div class="bar">BAR</div>
	</div>
	<hr>
	<!-- 분야별 랭킹 컨텐츠 -->
	<div class="dongleRank">DONGLE 분야별 랭킹</div>
	
</section>
<script
	src="<%=request.getContextPath()%>/Dongle_js/Dongle_main.js">
</script>

</body>
</html>
