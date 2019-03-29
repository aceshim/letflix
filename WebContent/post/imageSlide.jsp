<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<!doctype html>
<html>
  <head>
    <title>Tiny Slide</title>
    <link href="../common/assets/css/tinyslide.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-1.11.2.min.js" /></script>
    <script src="../common/assets/js/tinyslide.js" /></script>
  </head>
  <body>

    <section id="tiny" class="tinyslide">
      <aside class="slides">
      <c:if test="${not empty post }">
     	 <c:forEach var="cnt" begin="1" end="${post.length }">
    		<figure>
    			<img src='<c:url value="/upload/${post.pptId }/"/><c:if test="${ cnt < 10 }">0</c:if>${cnt}.JPG'/>
    		</figure>
   		 </c:forEach>
   		</c:if>
      </aside>
    </section>
    <script>
      var tiny = $('#tiny').tiny().data('api_tiny');
    </script>

  </body>
</html>
