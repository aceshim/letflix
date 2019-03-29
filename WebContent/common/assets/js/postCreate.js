$(document).ready(function(){
    var $input = $("#tagInput input"),
        $appendHere = $(".tagHere");

  $('.content article').click(function(e){
    e.preventDefault();
    $('.content article.selected').toggleClass('selected');
    $(this).toggleClass('selected');
    var title = $.trim($(this).find('h2').text());
//    $('#post .post-title input').val(title);
    $('.tag').remove();
    $(this).find('.tags a').each(function(){
      var tag = $.trim($(this).text());
      addTag2(tag);
    })
    $('#pptId').val($(this).find('.pptId').val());
  })

  function addTag2(element) {
    var $tag = $("<div />"), $a = $("<a class='tagDelete' href='#' />"), $span = $("<span />");
    $tag.addClass('tag');
    $('<i class="fa fa-times" aria-hidden="true"></i>').appendTo($a);
    $span.text(' # '+element);
    $a.bind('click', function(){
      $(this).parent().remove();
      $(this).unbind('click');
    });
    $span.appendTo($tag);
    $a.appendTo($tag);
    $tag.appendTo($appendHere);
  }

	$('#create').click(function(e){
		e.preventDefault();
		if ($('#fileName').val() == ""  ){
			alert("파일 이름을 입력해주세요!!");
			return;
		}

		
		if ($('#pptId').val()=="" ){
			alert("파워포인트를 등록해주세요!!");
			return;
		}
		

		
		$.post("/letflix/post/postCreate",
				{
					"postId": $('#postId').val(),
					"pptId": $('#pptId').val(),
					"title": $('#post .post-title input').val(),
					"description": $('#descriptionForm textarea').val()
				}, function(data){
					window.location="/letflix/post/postList";
				})
	})
  
	$('#submitButton').click(function(e){
		e.preventDefault();
		if ($('#fileName').val() == ""  ){
			alert("파일 이름을 입력해주세요!!");
			return;
		}

		
		if ($('#pptId').val()=="" ){
			alert("파워포인트를 등록해주세요!!");
			return;
		}
		
		$.post("/letflix/post/postUpdate",
				{
					"postId": $('#postId').val(),
					"pptId": $('#pptId').val(),
					"title": $('#post .post-title input').val(),
					"description": $('#descriptionForm textarea').val()
				}, function(data){
					window.location="/letflix/post/postDetail?id="+$('#postId').val();
				})
	})
	$('#deleteButton').click(function(e){
		e.preventDefault();
		if(confirm("이 포스트를 삭제하시겠습니까?")){
			$.post("/letflix/post/postDelete",
					{
						"postId": $('#postId').val()
					}, function(data){
						window.location="/letflix/post/postList";
					});
		}
		
	})
	
	$('#addPpt').click(function(e){
		e.preventDefault();
		window.location="/letflix/ppt/myPpt";
	})
})
