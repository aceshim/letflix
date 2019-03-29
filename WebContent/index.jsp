<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<!-- saved from url=(0048)https://colorlib.com/etc/lf/Login_v12/index.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LETFLIX</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="./common/favicon.ico">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./index_files_what/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./index_files_what/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./index_files_what/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./index_files_what/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./index_files_what/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./index_files_what/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="./index_files_what/util.css">
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="./index_files_what/main.css">
<!--===============================================================================================-->
</head>
<body>

	<div class="limiter">
		<div class="container-login100" style="background-image: url('<c:url value="/common/assets/intro_background.png"/>');">
			<div class="wrap-login100 p-t-190 p-b-30">
				<form method="post" action="./Login" class="login100-form validate-form">
					<div>
						<img style="max-width: 90vw" src="./common/assets/img/logo.png" alt="AVATAR">
					</div>

					<span class="login100-form-title p-t-20 p-b-45">
						<c:if test="${not empty message}">
						${message}
						</c:if>
					</span>

					<div class="wrap-input100 validate-input m-b-10" data-validate="Username is required">
						<input autocomplete="off" class="input100" type="email" name="id" placeholder="Email">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope"></i>
						</span>
					</div>  

					<div class="wrap-input100 validate-input m-b-10" data-validate="Password is required">
						<input class="input100" type="password" name="pwd" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock"></i>
						</span>
					</div>

					<div class="container-login100-form-btn p-t-10">
						<button class="login100-form-btn">
							Login
						</button>
					</div>

					<div  class="text-center w-full p-t-25 p-b-230" >
							<div id="loading" class="lds-ring" style="display:none"><div></div><div></div><div></div><div></div></div>
					</div>

					<!-- <div class="text-center w-full">
						<a class="txt1" href="https://colorlib.com/etc/lf/Login_v12/index.html#">
							Create new account
							<i class="fa fa-arrow-right"></i>
						</a>
					</div> -->
				</form>
			</div>
		</div>
	</div>
	<audio style="display: none" id="myAudio">
	  <source src="<c:url value="/common/assets/intro.mp3"/>" type="audio/mpeg">
	  Your browser does not support the audio element.
	</audio>


<!--===============================================================================================-->
	</script><script src="./index_files_what/jquery-3.2.1.min.js" type="text/javascript"></script>
<!--===============================================================================================-->
	<script src="./index_files_what/popper.js" type="text/javascript"></script>
	<script src="./index_files_what/bootstrap.min.js" type="text/javascript"></script>
<!--===============================================================================================-->
	<script src="./index_files_what/select2.min.js" type="text/javascript"></script>
<!--===============================================================================================-->
	<script src="./index_files_what/main.js" type="text/javascript"></script>


</body></html>
