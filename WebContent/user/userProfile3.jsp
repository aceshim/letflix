<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../common/taglib.jsp"%>
<html>
  <head>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
  <link rel="stylesheet" href="./css/profile.css">
  
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp"/>	
    <input style="display:none" id="slider" class="customSlider" type="checkbox">
    <label style="display:none" for="slider"></label>

    <div class="wrapper">
    	<div class="top-icons">
    		<i class="fas fa-arrow-left"></i>
    		<!-- <i class="fas fa-ellipsis-v"></i> -->
    		<i id="like" class="fas fa-heart"></i>
    	</div>

    	<div class="profile">
    		<img style="object-fit:cover" src="<c:url value="/user/profile_img/${sUser.userPic }" />" class="thumbnail">
    		<h3 class="name">${sUser.userName } ${sUser.userPosition }</h3>
    		<p class="title">${sUser.userCompany } ${sUser.userTeam }</p>
    		<p class="description"></p>
    		<div class="tags">
    			<c:forEach items="${userTagList }" var="tag" >
    			
                	<a rel="modal:open" href="#profile-modal">#${tag.tagName }</a>
    			</c:forEach>
            </div>
            <c:if test="${user.userId ne sUser.userId }" >
       			<button class="btn" id="modalOpen"><i class="fas fa-envelope"></i> Message </button>
            </c:if>
            <c:if test="${user.userId eq sUser.userId }">
            	<button class="btn" id="edit-tag">관심사 수정</button>
            </c:if>
		<a id="modalOpenTrigger" style="display:none" rel="modal:open" href="#profile-modal"></a>
    	</div>
      <div class="social-icons">
        <div class="icon">
          <a href="#"><i class="fas fa-user-friends"></i></a>
          <h4>36</h4>
          <p>Groups</p>
        </div>

        <div class="icon">
          <a href="#"><i class="fas fa-file-powerpoint"></i></a>
          <h4>49</h4>
          <p>Posts</p>
        </div>

        <div class="icon">
          <a href="#"><i class="fas fa-thumbs-up"></i></a>
          <h4>3,324</h4>
          <p>Likes</p>
        </div>

    	</div>
    </div>
    
 <!-- Modal Popup
<div style="display:none" class="modal" id="profile-modal">
	 -->
</div>
<!-- Remember to include jQuery :) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<script src="./js/profile.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.modal').modal();
		$('#modalOpen').click(function(){
			console.log("hello");
			$('#modalOpenTrigger').click();
		})
		$('#edit-tag').click(function(){
			window.location="/letflix/user/selectTag"
		})
		$('.fa-arrow-left').click(function(){
		    javascript:history.back();
		});
		
		
	});
</script>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonFooter.jsp" />
  </body>
</html>
