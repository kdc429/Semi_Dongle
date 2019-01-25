<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html ng-app="calendar">

<meta charset="utf-8">
    
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/Dongle_js/angular.min.js"></script>
   <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/calendar.css"></script>
	
	
    <script src="<%=request.getContextPath()%>/Dongle_js/moment.min.js"></script>
    <script type="text/javascript">
    moment.locale('kr', {
        months : "1월_2월_3월_4월_5월_6월_7월_8월_9월_10월_11월_12월".split("_"),
        monthsShort :  "1월_2월_3월_4월_5월_6월_7월_8월_9월_10월_11월_12월".split("_"),
        weekdays : "일요일_월요일_화요일_수요일_목요일_금요일_토요일".split("_"),
        weekdaysShort : "일._월._화._수._목._금_토.".split("_"),
        weekdaysMin :"일._월._화._수._목._금_토.".split("_"),
        ordinal : function (number) {
            return number + '일'
        }
    });
    </script>
    <script  src="<%=request.getContextPath()%>/Dongle_js/calendar.js"></script>

	<div ng-controller="calendarWidget">
    <calendar selected="day" ></calendar> 
    <span>선택된 날짜 : <strong>{{day.format('dddd, MMMM Do YYYY')}}</strong>

</html>