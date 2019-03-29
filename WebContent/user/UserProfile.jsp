<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	max-width: 300px;
	margin: auto;
	text-align: center;
	font-family: arial;
	background
	color
	:
	white;
}

.title {
	color: grey;
	font-size: 18px;
}

button {
	border: none;
	outline: 0;
	display: inline-block;
	padding: 8px;
	color: white;
	background-color: #000;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-size: 18px;
}

a {
	text-decoration: none;
	font-size: 22px;
	color: black;
}

button:hover {
	opacity: 0.7
}
</style>
<!-- 메뉴 바 -->
<jsp:include page="/common/commonHeader.jsp"/>	
<section class="post">
	<div class="card" >
		<br/>
		<h2 style="text-align: center">회원 프로필</h2>
		<div class=" column green">
		<img src="../img/ihatemondays.jpg" alt="userProfilePic" style="width: 100%">
		</div>
		
		
		<h1>${user.userName}</h1>
		<p>${user.userCompany }</p>
		<p>${user.userTeam }</p>
		<p class="title">${user.userPosition }</p>
		<p>${user.userPhone }</p>
		<p>${user.userId }</p>
		<a class="modal-trigger" href="#email-modal">CONTACT</a>

	</div>
</section>

<!-- Modal Popup -->
<div class="modal" id="email-modal">
	<div class="modal-content">
		<h4>메일 보내기</h4>
		<div class="container">
			<div class="row">			
				<div class="col s3">
					<img alt="sender" class="circle responsive-img" src="../user/profile_img/man.jpg" />
				</div>
				<div class="col s6">
					<div class="col s3">
					
					</div>
					
					<div class="col s6">
						<img class="responsive-img" src="../common/assets/img/mail-send.png" />
					</div>
					<div class="col s3">
					
					</div>
					
					
				</div>
				<div class="col s3">
					<img alt="receiver" class="circle responsive-img" src="../user/profile_img/woman.jpg" />
				</div>
			</div>
			
			<div class="row">
				<div class="input-field col s12">				
				<label for="email-content">내용</label>
				<textarea id="email-content" class="materialize-textarea"></textarea>
				</div>
			</div>
		</div>
		
		
		
	</div>
	<div class="modal-footer">
		<a href="#!" class="modal-close waves-effect waves-green btn-flat">보내기</a>
		<a href="#!" class="btn-flat modal-action modal-close waves-effect waves-default">close</a>
	</div>
</div>
<!--===============================================================================================-->
 <script type="text/javascript" src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.modal').modal();
	});
</script>


	
<!-- 메뉴 바 -->
<jsp:include page="/common/commonFooter.jsp"/>

</body>
</html>