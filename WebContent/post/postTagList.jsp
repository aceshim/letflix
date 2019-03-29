<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<html lang="en" itemscope="" itemtype="http://schema.org/WebPage">

<head>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp"/>

<section class="content">
    <main class="home" id="post" role="main" itemprop="mainContentOfPage" itemscope="itemscope" itemtype="http://schema.org/Blog">
    <c:if test="${ empty postListGroup }">
    	<h1 style="text-align: center; color: white;">NOTHING HERE!</h1>
    </c:if>
      <c:forEach items="${postListGroup}" var="postList">
      
		<c:if test="${ not empty postList.list }">
        <div class="groupname" style="color:white">
          ${postList.name} >
        </div>
        <div id="grid" class="row flex-grid">
          <c:forEach items="${postList.list}" var="post">
            <article class="box-item shown" itemscope="itemscope" itemtype="http://schema.org/BlogPosting" itemprop="blogPost">
                <div class="box-body">
                    <div class="cover">
                        <a href="<c:url value="/post/postDetail?id=${post.postId}"/>">
                            <img src="<c:url value="/upload/${post.pptId}/01.JPG"/>" data-url="<c:url value="/upload/${post.pptId}/01.JPG"/>" class="preload">
                        </a>
                    </div>
                    <div class="box-info">
                        <meta itemprop="datePublished" content="${post.createDate}">
                        <time itemprop="datePublished" datetime="${post.createDate}" class="date"> ${post.createDate}
                        </time>
                        <a class="post-link" href="<c:url value="/post/postDetail?id=${post.postId}"/>">
                            <h2 class="post-title" itemprop="name"> ${post.title}
                            </h2>
                        </a>
                        <span class="category">
                          <a href="<c:url value="/post/postDetail?id=${post.postId}"/>">
                          <span><font style="font-size:22px" color="#E50914">${post.userCompany}  </font>  ${post.userName} ${post.userPosition}</span>
                          </a>
                        </span>
                        <a class="post-link" href="<c:url value="/post/postDetail?id=${post.postId}"/>">
                            <p class="description">${post.description}
                            </p>
                        </a>
                        <div class="tags">
                          <c:forEach items="${post.tagList}" var="tag">
                            <a href="<c:url value="/tag/?id=${tag.tagId}"/>">${tag.tagName}</a>
                          </c:forEach>
                        </div>
                    </div>
                </div>
            </article>
          </c:forEach>
          </c:if>
        </div>
      </c:forEach>
    </main>
</section>
<!-- Footer -->
<jsp:include page="/common/commonFooter.jsp"/>

</body>

</html>
