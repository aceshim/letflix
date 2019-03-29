<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<html lang="en" itemscope="" itemtype="http://schema.org/WebPage">

<head>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp"/>
	<section class="content">
		<main class="home">
		
			<div>
				<h1 style="text-align: center; color: white;">${title }</h1>	
			</div>
			<div align="center">
				<h3 style="color: white;">${message }</h3>
				<a style="color: red;" href="<c:url value="/post/postList" />">메인으로</a>
			</div>
		</main>
	</section>
	<!-- Footer -->
	<jsp:include page="/common/commonFooter.jsp"/>