var time; // 슬라이드 넘어가는 시간
        var feedPicLi;
        var feedPicCount; // 캐러셀 사진 갯수
        var currentIndex; // 현재 보여지는 슬라이드 인덱스 값
        var indiIndex; //인디케이터 인덱스
        var imgWidth; // 사진 한장의 너비
        var indicator;// 캐러셀 인디케이터
        currentIndex = 0;
        $(function set_Position(){
       	 	for (var i = 0; i < feedPicCount; i++) {
       	 		if (i == currentIndex) {
       	 			feedPicLi.eq(i).css("left", 0);
       	 			
       	 		} else {
                 
       	 			feedPicLi.eq(i).css("left", imgWidth);
       	 		}
       	 	}
        });
        
        
        
        $(document).on('click','.feed-pic-indi',function () {

            console.log($($(this).parent('ul').children('li')).index(this));
            feedPicLi=$(this).parent('ul').parent('div').siblings('.feed-pics').children('.media-carousel').children('li');
            indiIndex = $($(this).parent('ul').children('li')).index(this);
            feedPicCount = feedPicLi.length;
            indicator=$(this);
            
            
            if (indiIndex == currentIndex) {
                return false;
                //같은 인덱스의 인디케이터를 눌렀을때 이벤트 작동 안함
            } else if (indiIndex > currentIndex) {
                
                imgWidth = feedPicLi.width(); // 사진 한장의 너비	
                // 이미지 위치 조정
                
                
                // 이미지 위치 조정
                for (var i = 0; i < feedPicCount; i++) {
                    if (i == currentIndex) {
                        feedPicLi.eq(i).css("left", 0);
                        
                    } else {
                        feedPicLi.eq(i).css("left", imgWidth);
                    }
                }
            
                
                console.log(feedPicLi.eq(currentIndex));
                var left = -imgWidth;
                //현재 슬라이드를 왼쪽으로 이동 ( 마이너스 지점 )
                feedPicLi.eq(currentIndex).animate({
                    left: left
                },function () {
                	
                    feedPicLi.eq(currentIndex-1).css("left", imgWidth);
                });
                //선택화면 넘어옴
                for (var i = 0; i < feedPicCount; i++) {
                    if (i == indiIndex) {
                        feedPicLi.eq(indiIndex).animate({
                            left: 0
                        });
                        
                    } else {
                        feedPicLi.eq(i).css({
                            left: imgWidth
                        });
                    }
                }
                //현재 인덱스 저장
                currentIndex = indiIndex;
                return currentIndex;

            } else if (indiIndex < currentIndex) {
                imgWidth = feedPicLi.width(); // 사진 한장의 너비	
                // 이미지 위치 조정
                for (var i = 0; i < feedPicCount; i++) {
                    if (i == currentIndex) {
                        feedPicLi.eq(i).css("left", 0);
                        
                    } else {
                        feedPicLi.eq(i).css("left", -imgWidth);
                    }
                }
                
                var left = imgWidth;

                //현재 슬라이드를 오른쪽으로 이동 ( 마이너스 지점 )
                feedPicLi.eq(currentIndex).animate({
                    left: left
                },function () {
                	
                    feedPicLi.eq(currentIndex+1).css("left", -imgWidth);
                });

                //선택화면 넘어옴
                for (var i = 0; i < feedPicCount; i++) {
                    if (i == indiIndex) {
                        feedPicLi.eq(indiIndex).animate({
                            left: 0
                        });
                        
                    } else {
                        feedPicLi.eq(i).css({
                            left: -imgWidth
                        });
                    }
                }
                //현재 화면 인덱스 저장
                currentIndex = indiIndex;
                return currentIndex;
            }

        });

        $(document).on('click','.next',function() {
            // carousel_setImgPosition();
            feedPicLi=$(this).siblings('.media-carousel').children('li');
            imgWidth = feedPicLi.width();
            feedPicCount = feedPicLi.length;
            indicator=$(this);
                
                // 이미지 위치 조정
            for (var i = 0; i < feedPicCount; i++) {
                if (i == currentIndex) {
                    feedPicLi.eq(i).css("left", 0);
                    
                } else {
                    
                    feedPicLi.eq(i).css("left", imgWidth);
                }
            }
            

            
            var left = -imgWidth;
            feedPicLi.eq(currentIndex).animate({
                left: left
            }, function () {
            	
                feedPicLi.eq(currentIndex-1).css("left", imgWidth);
            });
            
            feedPicLi.eq(currentIndex).css({
                "left": imgWidth
            });
            // 다음 슬라이드 화면으로
            if (currentIndex == (feedPicCount - 1)) {
                //마지막 슬라이드 화면일때 초기화
                
                currentIndex = 0;
            } else {
                
                currentIndex++;
            }
            for (var i = 0; i < feedPicCount; i++) {
                if (i == currentIndex) {
                    //다음 화면 넘기기
                    feedPicLi.eq(currentIndex).animate({
                        left: 0
                    });
                    
                } else {
                    feedPicLi.eq(i).css({
                        left: imgWidth
                    });
                }
            }


            //현재 화면 인덱스 저장
            return currentIndex;
        });

        $(document).on('click','.prev',function() {
            feedPicLi=$(this).siblings('.media-carousel').children('li');
            imgWidth = feedPicLi.width();
            feedPicCount = feedPicLi.length;
            indicator=$(this);
            for (var i = 0; i < feedPicCount; i++) {
                if (i == currentIndex) {
                    feedPicLi.eq(i).css("left", 0);
                } else {
                    console.log('태그이동');
                    feedPicLi.eq(i).css("left", -imgWidth);
                }
            }

            // carousel_setImgPositionL();
            
            var left = imgWidth;
            console.log(left);
            feedPicLi.eq(currentIndex).animate({
                left: left
                
            },function () {
            	
                feedPicLi.eq(currentIndex+1).css("left", -imgWidth);
            });
            console.log("태그이동!");
            // 다음 슬라이드 화면으로
            if (currentIndex == 0) {
                //마지막 슬라이드 화면일때 초기화
                currentIndex = feedPicCount - 1;
                console.log(currentIndex);
            } else {
                currentIndex--;
            }
            for (var i = 0; i < feedPicCount; i++) {
                if (i == currentIndex) {
                    //다음 화면 넘기기
                    feedPicLi.eq(currentIndex).animate({
                        left: 0
                    });
                   
                } else {
                    feedPicLi.eq(i).css({
                        left: -imgWidth
                    });
                }
            }
            //현재 화면 인덱스 저장
            return currentIndex;
        });