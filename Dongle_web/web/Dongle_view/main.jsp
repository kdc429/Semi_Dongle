<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<section>
	<div class="myDongle">
		<div id="myCarousel" class="carousel slide ClubBtn"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner carou">
				<div class="item active">
					<!-- <input type="button" id="createClub" class="mc" src="" />
                                <input type="button" class="mc" src="" />
                                <input type="button" class="mc" src="" />
                                <input type="button" class="mc" src="" />
                                <input type="button" class="mc" src="" /> -->
					<button id="createClub" class="mc">
						<img src="" alt="">+
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>

				</div>

				<div class="item">
					<button id="createClub" class="mc">
						<img src="" alt="">+
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>

				</div>

				<div class="item">
					<button id="createClub" class="mc">
						<img src="" alt="">+
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>
					<button id="mClub" class="mc">
						<img src="" alt="">
					</button>

				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control contr" href="#myCarousel"
				data-slide="prev" style="height: 200px;"> <span
				class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control contr" href="#myCarousel"
				data-slide="next" style="height: 200px;"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>

	</div>
	<hr>
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
	<div class="dongleRank">DONGLE 분야별 랭킹</div>
</section>
<script>
	var time; // 슬라이드 넘어가는 시간
	var $carouselLi;
	var carouselCount; // 캐러셀 사진 갯수
	var currentIndex; // 현재 보여지는 슬라이드 인덱스 값
	var caInterval;

	//사진 연결
	var imgW; // 사진 한장의 너비	
	$(document).ready(function() {

		carouselInit(250, 3000);
	});

	$(window).resize(function() {
		carousel_setImgPosition();
	});

	/* 초기 설정 */
	function carouselInit(height, t) {
		/*
		 * height : 캐러셀 높이
		 * t : 사진 전환 간격 
		 */

		time = t;
		$("#carousel_section").height(height); // 캐너셀 높이 설정
		$carouselLi = $("#carousel_section > ul >li");
		carouselCount = $carouselLi.length; // 캐러셀 사진 갯수
		currentIndex = 0; // 현재 보여지는 슬라이드 인덱스 값
		carousel_setImgPosition();
		carousel();
	}

	function carousel_setImgPosition() {

		imgW = $carouselLi.width(); // 사진 한장의 너비	
		// 이미지 위치 조정
		for (var i = 0; i < carouselCount; i++) {
			if (i == currentIndex) {
				$carouselLi.eq(i).css("left", 0);
			} else {
				$carouselLi.eq(i).css("left", imgW);
			}
		}
	}

	function carousel() {

		// 사진 넘기기
		// 사진 하나가 넘어간 후 다시 꼬리에 붙어야함
		// 화면에 보이는 슬라이드만 보이기
		caInterval = setInterval(function() {
			var left = "-" + imgW;

			//현재 슬라이드를 왼쪽으로 이동 ( 마이너스 지점 )
			$carouselLi.eq(currentIndex).animate({
				left : left
			}, function() {
				// 다시 오른쪽 (제자리)로 이동
				$carouselLi.eq(currentIndex).css("left", imgW);

				if (currentIndex == (carouselCount - 1)) {
					currentIndex = 0;
				} else {
					currentIndex++;
				}
			});

			// 다음 슬라이드 화면으로
			if (currentIndex == (carouselCount - 1)) {
				// 마지막 슬라이드가 넘어갈땐 처음 슬라이드가 보이도록
				$carouselLi.eq(0).animate({
					left : 0
				});
			} else {
				$carouselLi.eq(currentIndex + 1).animate({
					left : 0
				});
			}
		}, time);
	}
</script>
</body>
</html>
