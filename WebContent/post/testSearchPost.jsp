<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<html lang="en" itemscope="" itemtype="http://schema.org/WebPage">
 
<head>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp"/>

<section class="content">
    <main class="home" id="post" role="main" itemprop="mainContentOfPage" itemscope="itemscope" itemtype="http://schema.org/Blog">
    <c:if test="${ empty searchPostByTagBtn }">
    	<h1 style="text-align: center; color: white;">NOTHING HERE!</h1>
    </c:if>
    

      <div class="groupname" style="color:white">
        ${tagName} >
      </div>
      <div id="grid" class="row flex-grid horizontal">
      <c:forEach items="${searchPostByTagBtn}" var="postList"> 
        	<article class="box-item shown" itemscope="itemscope" itemtype="http://schema.org/BlogPosting" itemprop="blogPost">
                <div class="box-body">
                    <div class="cover">
                        <a href="<c:url value="/post/postDetail?id=${postList.postId}"/>">
                            <img src="<c:url value="/upload/${postList.pptId}/01.JPG"/>" data-url="<c:url value="/upload/${postList.pptId}/01.JPG"/>" class="preload">
                        </a>
                    </div>
                    <div class="box-info">
                        <meta itemprop="datePublished" content="${postList.createDate}">
                        <time itemprop="datePublished" datetime="${postList.createDate}" class="date"> ${postList.createDate}
                        </time>
                        <a class="post-link" href="<c:url value="/post/postDetail?id=${postList.postId}"/>">
                            <h2 class="post-title" itemprop="name"> ${postList.title}
                            </h2>
                        </a>
                        <span class="category">
                          <a href="<c:url value="/post/postDetail?id=${postList.postId}"/>">
                          <span><font style="font-size:22px" color="#E50914">${postList.userCompany}  </font>  ${postList.userName} ${postList.userPosition}</span>
                          </a>
                        </span>
                        <a class="post-link" href="<c:url value="/post/postDetail?id=${postList.postId}"/>">
                            <p class="description">${postList.description}
                            </p>
                        </a>
                        <div class="tags">
                          <c:forEach items="${postList.tagList}" var="tag">
                            <a href="<c:url value="/search/searchBtn?id=${tag.tagId}&name=${tag.tagName }"/>">${tag.tagName}</a>
                          </c:forEach>
                        </div>
                    </div>
                </div>
            </article>
      </c:forEach>
     </div> 
    </main>
</section>
<!-- Footer -->
<jsp:include page="/common/commonFooter.jsp"/>

</body>

</html>