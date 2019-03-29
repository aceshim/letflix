<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html lang="en" itemscope="" itemtype="http://schema.org/BlogPosting">
<head>

<link rel="stylesheet" href="<c:url value="/common/assets/css/detailView.css"/>">
<link rel="stylesheet" href="<c:url value="/common/assets/css/form.css"/>">
<%-- <script src="<c:url value="/common/assets/js/main.js"/>"></script> --%>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp"/>
<section class="post">

    <article role="article" id="post" class="post-content" itemprop="articleBody">
        <p>
          <a href="javascript:history.back()" target="_blank">&lt; 뒤로가기</a>
          <c:if test="${not empty group}">
            <span id="${group.groupId}" style="padding-left: 3rem; color: white;">현재 그룹: ${group.groupName}</span>
          </c:if>
          <a id="deleteButton" style="float: right;" href="javascript:history.back()" target="_blank">삭제</a>
          <a id="submitButton" style="background-color: green; float: right; border: none;" href="javascript:history.back()" target="_blank">저장</a>
        </p>
        <input id="postId" style="display:none" type="text" required="required" value="${post.postId }"/>
        <input id="pptId" style="display:none" type="text" required="required" value="${post.pptId }"/>
        
        <h1 class="post-title" itemprop="name">
          <form>
            <input type="text" id = "fileName" required="required" value="<c:if test="${not empty post }">${post.title }</c:if>"/>
            
            <span class="highlight"></span>
            <span class="bar"></span>
            <label>File Name</label>
          </form>
        </h1>
        <div id='tagInput'>
          <div class='tagHere'>
          <c:if test="${not empty post }">
			<c:if test="${not empty post.tagList }">
          	<c:forEach items="${post.tagList }" var="tag">
          	<div class="tag">
          		<span># ${tag.tagName }</span>
          		<a class="tagDelete" href="javascript:">
          			<i class="fa fa-times" aria-hidden="true"></i>
          		</a>
          	</div>
          	</c:forEach>
          </c:if>
          </c:if>
          </div>
          <input disabled type="text" autofocus placeholder=",로 태그를 구분하세요"/>
        </div>
        <h1>
          <form id="descriptionForm">
            <textarea type="textarea" required="required"><c:if test="${not empty post }">${post.description }</c:if></textarea>
            <span class="highlight"></span>
            <span class="bar"></span>
            <label>description</label>
          </form>
        </h1>
        <h3 class="groupname" style="color:white">
          내가 업로드한 파일들
        </h3>

  </article>7]
</section>
<section class="content">
<div id="grid" class="row flex-grid horizontal">
  <c:if test="${not empty pptList}">
    <c:forEach items="${pptList}" var="ppt">
      <article class="box-item shown" itemscope="itemscope" itemtype="http://schema.org/BlogPosting" itemprop="blogPost">
          <div class="box-body">
        	  <input class="pptId" style="display:none" type="text" required="required" value="${ppt.pptId }"/>
        
              <div class="cover">
                  <a href="">
                      <img src="<c:url value="/upload/${ppt.pptId}/01.JPG"/>" class="preload">
                  </a>
              </div>
              <div class="box-info">
                  <meta itemprop="datePublished" content="${ppt.pptDate}">
                  <time itemprop="datePublished" datetime="${ppt.pptDate}" class="date"> ${ppt.pptDate}
                  </time>
                  <a class="post-link" href="">
                      <h2 class="post-title" itemprop="name">
                      ${ppt.pptTitle }
                      </h2>
                  </a>
                  <a class="post-link" href="">
                      <p class="description">
                      </p>
                  </a>
                  <div class="tags">
                    <c:if test="${not empty ppt.tagList }">
                      <c:forEach items="${ppt.tagList}" var="tag">
                        <a href="<c:url value="/tag/?id=${tag.tagId}"/>">${tag.tagName}</a>
                      </c:forEach>
                    </c:if>
                  </div>
              </div>
          </div>
      </article>
    </c:forEach>
  </c:if>
  </section>
  <!-- 메뉴 바 -->
	<jsp:include page="/common/commonFooter.jsp" />

<link rel="stylesheet" href="<c:url value="/common/assets/css/postCreate.css"/>">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<script src="<c:url value="/common/assets/js/tagInput.js"/>"></script>
<script src="<c:url value="/common/assets/js/postCreate.js"/>"></script>
</body></html>
