$(document).ready(function(){
  $('#fullscreenIcon.open').click(function(){
    $('#imageSlide.fullscreen').show();
    $('#fullscreenIcon.close').show();
  })
  $('#fullscreenIcon.close').click(function(){
    $('#imageSlide.fullscreen').hide();
    $(this).hide();
  })
  $(document).keyup(function(e) {
     if (e.key === "Escape") { // escape key maps to keycode `27`
        $('#imageSlide.fullscreen').hide();
    }
});
})
