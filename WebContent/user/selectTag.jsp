<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<!-- Remember to include jQuery :) -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>CSS BUTTONS!!</title>
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/selectTag.css">
</head>
<body>
	<h1>관심사를 선택해주세요</h1>

	<!-- <button class="btn tag">Button 4</button> -->
	<c:if test="${not empty tagList }">
		<c:forEach items="${tagList }" var="tag">
			<button id="${tag.tagId }"
				class="btn tag
    		<c:if test="${not empty userTagList }">
    			<c:forEach items="${userTagList }" var="userTag">
    				<c:if test="${ tag.tagName eq userTag.tagName}"> selected </c:if>
    			</c:forEach>
    		</c:if>">${tag.tagName }</button>
		</c:forEach>
	</c:if>


	<p id="message">3개 이상 선택해주세요</p>
	<h2 style="color: white">
		<span id="count"> 0 </span> / 25 개 선택중
	</h2>
	<button class="btn submit"
		onclick="window.location='<c:url value="/user/userProfile?sUserId=${user.userId }"/>'">Submit</button>
	<script>

$('#count').text($('.selected').length);
if ($('.selected').length >= 3){
	$('#message').hide();
	$('.submit').show();
} else {
	$('#message').show();
	$('.submit').hide();
}

$('button.tag').click(function(){
	if ($(this).hasClass("selected")){
		$.post("<c:url value="/deregisterUserTag"/>", {
			tagId : $(this).attr("id")});
	} else {
		$.post("<c:url value="/registerUserTag"/>", {
			tagId : $(this).attr("id")});
	}
	$(this).toggleClass("selected");
	$('#count').text($('.selected').length);
	
	if ($('.selected').length >= 3){
		$('#message').hide();
		$('.submit').show();
	} else {
		$('#message').show();
		$('.submit').hide();
	}
});





</script>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<!--<script src="js/selectTag.js"></script>  -->
</body>
</html>
