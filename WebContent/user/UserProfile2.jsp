<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>


<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	max-width: 300px;
	margin: auto;
	text-align: center;
	font-family: arial;
	
}



button {
	border: none;
	outline: 0;
	display: inline-block;
	padding: 8px;
	color: white;
	background-color: #000;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-size: 18px;
}

a {
	text-decoration: none;
	font-size: 22px;
	color: black;
}

button:hover {
	opacity: 0.7
}

.rounded{
	border-radius: 50%
}
</style>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp"/>	
<section class="post">
	<div class="card" >
		<br/>
		<h2 style="text-align: center;color: white ">회원 프로필</h2>
		<div class=" column green">
		<img class = "rounded" src="<c:url value="/user/profile_img/${user.userPic }" />" alt="userProfilePic" style="width: 100%">
		</div>
		
		<form action="/letflix/user/selectTag" method="post">
		<h1 style="color: white">${user.userName}</h1>
		<p style="color: white">${user.userCompany }</p>
		<p style="color: white">${user.userTeam }</p>
		<p class="title" style="color: white">${user.userPosition }</p>
		<p style="color: white">${user.userPhone }</p>
		<p style="color: white">${user.userId }</p>
		<c:if test="${empty userTagList}">
		<p style="color: white">관심사 없음</p>
		</c:if>
		<c:if test="${not empty userTagList}">
		<p style="color: white; font-weight: bold">관심사</p>
		${tag.tagName }
		<c:forEach items="${userTagList }" var="tag">
			<p style="color: white">${tag.tagName }</p>
 
    	</c:forEach>
		</c:if>
		
		
		
		
		<button type="submit" >관심사 수정</button>
		</form>

	</div>
	</section>
	
<!-- 메뉴 바 -->
<jsp:include page="/common/commonFooter.jsp"/>

</body>
</html>