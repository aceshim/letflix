<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<html lang="en" itemscope="" itemtype="http://schema.org/WebPage">

<head>

    
    <!-- Apple Touch Icons  및 기타 ? commonHeader에 안들어있음  -->
    <link rel="apple-touch-icon" href="../common/assets/img/icons/apple-touch-icon.png">

    <link rel="stylesheet" href="../common/assets/css/horizontal.css">

    <script src="../common/assets/js/main.js"/></script>
    <script src="http://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="../common/assets/js/sly.min.js"></script>
    <script src="../common/assets/js/horizontal.js"></script>

	<!-- 메뉴 바 -->
	<jsp:include page="/common/commonHeader.jsp" />
	
    <section class="content">
        <main class="home" id="post" role="main" itemprop="mainContentOfPage" itemscope="itemscope" itemtype="http://schema.org/Blog">
        <c:if test="${ empty groupListGroup }">
        	<h1 style="text-align: center; color: white;">NOTHING HERE!</h1>
        </c:if>
          <c:forEach items="${groupListGroup}" var="groupList">
          
   			<c:if test="${ not empty groupList.list }">
            <div class="groupname" style="color:white">
              ${groupList.name} >
            </div>
            <div id="grid" class="row flex-grid horizontal">
              <c:forEach items="${groupList.list}" var="group">
                <article class="box-item shown" itemscope="itemscope" itemtype="http://schema.org/BlogPosting" itemprop="blogPost">
                    <div class="box-body">
                        <div class="cover">
                            <a href="<c:url value="/group/?id=${group.groupId}/"/>">
                                <img src="<c:url value="/upload/group/${group.groupId}.JPG"/>" data-url="<c:url value="/upload/group/${group.groupId}.JPG"/>" class="preload">
                            </a>
                        </div>
                        <div class="box-info">
                            <a class="post-link" href="<c:url value="/group/?id=${group.groupId}/"/>">
                                <h2 class="post-title" itemprop="name"> ${group.groupName}
                                </h2>
                            </a>
                            <span class="category">
                            	<img src="../img/ic_person.png" />
                              	<span><font style="font-size:22px" color="#E50914">${group.memberNum}  </font>  </span>
                              
                            </span>
                            
                        </div>
                    </div>
                </article>
              </c:forEach>
              </c:if>
            </div>
          </c:forEach>
        </main>
    </section>
    <jsp:include page="/common/commonFooter.jsp" />

