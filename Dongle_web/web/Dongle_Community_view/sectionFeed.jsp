<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="newsfeed" style='width:684px;height:auto; background-color:rgb(255, 255, 255)'>
             <br><br>
                <div class="news" style="width:500px; height:100%; left:10%; right:10%;">
                    <div class="feednew" style="border:1px solid grey; width:500px; height:220px; left:10%; right:10%;">
                        <div style="width:500px;height:40px; background-color:rgba(58, 58, 58, 0.2); ">NEWSFEED</div>
                        <form>
                            <label>
                                <textarea name="feedup" cols='53' rows="6" style='resize: none'></textarea>
                                <button>등록</button>
                            </label>
                        </form>
                        <div>
                            <div class="fileup" style="border:1px solid grey; width: 500px; height: 40px;background-color:rgba(58, 58, 58, 0.2);">
                                <a class="picup" style="bottom:0;">사진업로드</a>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <!-- 피드 한칸 -->
                    <div class="feed" style="border:1px solid grey; width:500px; height:auto; left:10%; right:10%;">
                        feed
                        <div class="storyH" style="border:1px solid grey; width: 500px; height: 40px;">
                            작성자, 작성 시간
                        </div>
                        <div class="storyB" style="border:1px solid grey; width: 500px; height: 200px;">
                            작성 내용
                        </div>
                        <div class="storyF" style="border:1px solid grey; width: 500px; height: 40px;">
                            <a class="comment">∥댓글달기</a>
                            <a class="repleview">∥댓글보기</a>
                        </div>
                        

                    </div>
                    <hr>
                    <!-- 피드 한칸 -->
                    <div class="feed" style="border:1px solid grey; width:500px; height:auto; left:10%; right:10%;">
                        feed
                        <div class="storyH" style="border:1px solid grey; width: 500px; height: 40px;">
                            작성자, 작성 시간
                        </div>
                        <div class="storyB" style="border:1px solid grey; width: 500px; height: 200px;">
                            작성 내용
                        </div>
                        <div class="storyF" style="border:1px solid grey; width: 500px; height: 40px;">
                            <a class="comment">∥댓글달기</a>
                            <a class="repleview">∥댓글보기</a>
                        </div>

                    </div>
                    <hr>
                    <!-- 피드 한칸 -->
                    <div class="feed" style="border:1px solid grey; width:500px; height:auto; left:10%; right:10%;">
                        feed
                        <div class="storyH" style="border:1px solid grey; width: 500px; height: 40px;">
                            작성자, 작성 시간
                        </div>
                        <div class="storyB" style="border:1px solid grey; width: 500px; height: 200px;">
                            작성 내용
                        </div>
                        <div class="storyF" style="border:1px solid grey; width: 500px; height: 40px;">
                            <a class="comment">∥댓글달기</a>
                            <a class="repleview">∥댓글보기</a>
                        </div>

                    </div>



                    <button id="more">더 보기</button>

                </div>
    <script src="<%=request.getContextPath()%>/Dongle_js/feed.js"></script>

</body>
</html>