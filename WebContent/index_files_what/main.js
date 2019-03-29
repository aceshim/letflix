
(function ($) {
    "use strict";

    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');
    $('input[type=email]').focus();

	var x = document.getElementById("myAudio"); 
	
	function playAudio() { 
	  x.play(); 
	} 
	
	function pauseAudio() { 
	  x.pause(); 
	} 
	
    $('.login100-form-btn').on('click',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        $('.wrap-input100').slideUp();
        $('.container-login100-form-btn').slideUp();
        $('#loading').show();
        if (check){
          playAudio();
          setTimeout(function(){
            $('#loading').hide();
            $('.validate-form').submit();
          }, 3000)
        } else {
          setTimeout(function(){
            $('.wrap-input100').slideDown();
            $('.container-login100-form-btn').slideDown();
            $('#loading').hide();
          }, 300);
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }



})(jQuery);
