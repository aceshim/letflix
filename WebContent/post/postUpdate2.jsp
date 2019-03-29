<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html lang="en" class="no-js">

<head>
<link rel="stylesheet" href="../common/assets/css/detailView.css">
<link rel="stylesheet" href="../common/assets/css/form.css">
<link rel="stylesheet" href="../common/assets/css/dragNdrop.css">
<style>
.box__dragndrop,
.box__uploading,
.box__success,
.box__error {
  display: none;
}
</style>
<jsp:include page="/common/commonHeader.jsp" />
<section class="post">

	<article role="article" id="post" class="post-content"
		itemprop="articleBody">
		<p>
			<a href="javascript:history.back()" target="_blank">&lt; 뒤로가기</a> <a
				style="background-color: green; float: right; color: black; border: none;"
				href="javascript:history.back()" target="_blank">저장</a> <a
				style="background-color: red; float: right; color: black; border: none;"
				href="javascript:history.back()" target="_blank">삭제</a>
		</p>
		<h1 class="post-title" itemprop="name">
			<form>
				<input type="text" required="required" /> <span class="highlight"></span>
				<span class="bar"></span> <label>File Name</label>
			</form>
		</h1>
		<div id='tagInput'>
			<div class='tagHere'></div>
			<input type="text" autofocus placeholder=",로 태그를 구분하세요" />
		</div>


	</article>
</section>
<center>
<div>
<form class="box" method="post" action="" enctype="multipart/form-data">
  <div class="box__input">
    <input class="box__file" type="file" name="files[]" id="file" data-multiple-caption="{count} files selected" multiple />
    <label for="file"><strong>Choose a file</strong><span class="box__dragndrop"> or drag it here</span>.</label>
    <button class="box__button" type="submit">Upload</button>
  </div>
  <div class="box__uploading">Uploading&hellip;</div>
  <div class="box__success">Done!</div>
  <div class="box__error">Error! <span></span>.</div>
</form>
</div>
</center>

	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
		integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
		crossorigin="anonymous">

	<!-- 메뉴 바 -->
	<jsp:include page="/common/commonFooter.jsp" />
	<script src="../common/assets/js/tagInput.js"></script>
	<script src="../common/assets/js/detailView.js"></script>
	<script src="../common/assets/js/main.js"></script>
</body>

</html>