<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">


</head>
<body>

<div class="container">
	<div class="row">
		<div class="col s12 m12 l12">
			<a class="btn waves-effect waves=default modal-trigger" href="#modal1">click!</a>
		</div>
	</div>
</div>

<!-- Modal Popup -->
<div class="modal" id="modal1">
	<div class="modal-content">
		<h4>메일 보내기</h4>
		<div class="container">
			<div class="row">			
				<div class="col s3">
					<img alt="sender" class="circle responsive-img" src="./user/profile_img/man.jpg" />
				</div>
				<div class="col s6">
					<div class="col s3">
					
					</div>
					
					<div class="col s6">
						<img class="responsive-img" src="./common/assets/img/mail-send.png" />
					</div>
					<div class="col s3">
					
					</div>
					
					
				</div>
				<div class="col s3">
					<img alt="receiver" class="circle responsive-img" src="./user/profile_img/woman.jpg" />
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
</body>
</html>