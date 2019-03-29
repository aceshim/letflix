<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<html lang="en" itemscope="" itemtype="http://schema.org/BlogPosting">
<head>
<!-- Remember to include jQuery :) -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<style>
#like {
	float: right;
	top: 43px;
	right: 60px;
	position: relative;
	color: red;
}
</style>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp" />
<section class="post">

	<article role="article" id="post" class="post-content"
		itemprop="articleBody">
		<p>
			<a href="javascript:history.back()">< 뒤로가기</a>
			<c:if test="${not empty user }">
				<c:if test="${post.userId eq user.userId }">
					<a style="float: right"
						href='<c:url value="/post/postUpdateForm?id=${post.postId}"/>'>수정하기</a>
				</c:if>
			</c:if>
		</p>
		<p itemprop="post-info" class="post-info">
			<svg id="date" class="icon-calendar">
				<use xlink:href="#icon-calendar"></use></svg>
			<time itemprop="datePublished" datetime="2018-11-22T12:26:40+00:00"
				class="date"> ${post.createDate } </time>
			<svg id="clock" class="icon-clock">
				<use xlink:href="#icon-clock"></use></svg>
			<span>3 min to read</span>
		</p>
		<h1 class="post-title" itemprop="name">${post.title }</h1>
		<p>
			<span style="color: #E50914">${post.userCompany }</span> <span
				style="color: #eee">${post.userName }</span> <span
				style="color: #eee">${post.userPosition }</span>
		</p>
		<p itemprop="description" class="subtitle">
			<c:if test="${ not empty post.tagList }">
				<c:forEach items="${post.tagList}" var="tag">
					<a
						href="<c:url value="/search/searchBtn?id=${tag.tagId}&name=${tag.tagName }"/>">${tag.tagName}</a>
				</c:forEach>
			</c:if>





			<!--  <svg class="product__star" width="24" height="24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M12 21.593c-5.63-5.539-11-10.297-11-14.402 0-3.791 3.068-5.191 5.281-5.191 1.312 0 4.151.501 5.719 4.457 1.59-3.968 4.464-4.447 5.726-4.447 2.54 0 5.274 1.621 5.274 5.181 0 4.069-5.136 8.625-11 14.402m5.726-20.583c-2.203 0-4.446 1.042-5.726 3.238-1.285-2.206-3.522-3.248-5.719-3.248-3.183 0-6.281 2.187-6.281 6.191 0 4.661 5.571 9.429 12 15.809 6.43-6.38 12-11.148 12-15.809 0-4.011-3.095-6.181-6.274-6.181"/></svg> -->
			<!-- <svg class="product__star" height="20" viewBox="0 0 98 94" width="20">
                    <polygon points="48 6 65 30 92 37 75 60 75 88 48 80 22 88 22 60 6 37 32 30" style="stroke-width:10"></polygon>
                    <clippath id="star">
                      <polygon points="48 6 65 30 92 37 75 60 75 88 48 80 22 88 22 60 6 37 32 30" style="stroke-width:10">

                      </polygon>
                    </clippath>
                    <g clip-path="url(#star)">
                      <rect height="100%" width="100%">
                      </rect>
                    </g>
                  </svg> -->
            <c:if test="${ liked eq 1 }">
			<i id="like" class="fas fa-heart"></i>
			<script>
				$('#like').val("Like");
			</script>
		</c:if>
		<c:if test="${ liked eq 0 }">
			<i id="like" class="far fa-heart"></i>
		</c:if>
			<svg id="fullscreenIcon" class="open"
				xmlns="http://www.w3.org/2000/svg" width="24" height="24"
				viewBox="0 0 24 24">
                    <path
					d="M24 9h-2v-5h-7v-2h9v7zm-9 13v-2h7v-5h2v7h-9zm-15-7h2v5h7v2h-9v-7zm9-13v2h-7v5h-2v-7h9z" />
                  </svg>


		</p>


		<iframe id="imageSlide"
			src="http://localhost:8080/letflix/post/ImageSlide?id=${post.postId }"
			frameborder=0></iframe>

		


		<p id="howManyLikes"><i style="color: red" class="fas fa-heart"></i> <span id="likeCount">${likes }</span>개</p>
		<p id="views">조회수${viewCount} 개</p>



		<p>${post.description }</p>
</section>

<svg id="fullscreenIcon" class="close" style="display: none"
	xmlns="http://www.w3.org/2000/svg" width="24" height="24"
	viewBox="0 0 24 24">
          <path
		d="M24 9h-2v-5h-7v-2h9v7zm-9 13v-2h7v-5h2v7h-9zm-15-7h2v5h7v2h-9v-7zm9-13v2h-7v5h-2v-7h9z" />
        </svg>

<!-- Remember to include jQuery :) -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<!-- <script src="./js/postHeart.js"></script> -->
<script>
	$('#like').click(function() {
		$('#like').toggleClass("fas").toggleClass("far");
		if ($(this).val() == "Like") { //unlike
			$(this).val('Unlike');
			$.post("<c:url value="/unlikeServlet"/>", {
				pptId : "${post.pptId }"
			}).done(function(responseText) {
				$('#likeCount').text(responseText);
			});

		} else { //좋아요 
			$(this).val('Like');
			$.post("<c:url value="/likeServlet"/>", {
				pptId : "${post.pptId }"
			}).done(function(responseText) {
				$('#likeCount').text(responseText);
			});

		}

	});

	$('#fullscreenIcon').click(function() {
		$.post("<c:url value="/clickPpt"/>", {
			pptId : "${post.pptId }"
		});
	});
</script>




<!-- 메뉴 바 -->
<jsp:include page="/common/commonFooter.jsp" />
<script src="../common/assets/js/detailView.js"></script>
<iframe id="imageSlide" class="fullscreen" style="display: none"
	src="http://localhost:8080/letflix/post/ImageSlide?id=${post.postId }"
	frameborder=0 webkitallowfullscreen></iframe>
</body>
</html>

