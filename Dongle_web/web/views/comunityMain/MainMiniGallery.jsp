<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
$(document).ready(function() {
	$("#content-slider").lightSlider({
        loop:true,
        keyPress:true
    });
});
</script>
<style type="text/css">
	.demo ul{
		list-style: none outside none;
		padding-left: 0;
        margin: 0;
	}
   .demo .item{
   		margin-bottom: 60px;
    }
	.content-slider li{
		background-color: lightgray;
		ext-align: center;
	 	color: #FFF;
	}
	.demo{
		width: 600px;;
	}
</style>
</head>
<body> 
  	 <div class="item">
       <ul id="content-slider" class="content-slider">
           <li>
               <img src="./images/member_img/user01.png">
           </li>
           <li>
               <img src="./images/member_img/user02.png"> 
           </li>
           <li>
            	<img src="./images/member_img/user03.png"> 
           </li>
           <li>
               <h3>4</h3>
           </li>
           <li>
               <h3>5</h3>
           </li>
           <li>
               <h3>6</h3>
           </li>
           <li>
               <h3>7</h3>
           </li>
           <li>
               <h3>8</h3>
           </li>
           <li>>
               <h3>9</h3>
           </li>
           <li>
               <h3>10</h3>
           </li>
           
       </ul>
   </div>
</body>
</html>