function setMessage(messages) {
	if(!Array.isArray(messages)){
		$('.error-message-list').html(messages)
		popUpShow();
	}
	else if(messages && messages.length > 0){
		$('.error-message-list').html('');
		messages.forEach( (msg)=>{
			$('.error-message-list').append('<li class="error-message">'+msg+'</li>')
		})
	    //$('.error-message').html(message);
	    popUpShow();
	}
}

function popUpShow() {
    $('.error-popup').addClass('visible');
}

function popUpHide() {
    $('.error-popup').removeClass('visible');
}

$('#error-popup-btn').click(() => {
    $('#error-popup-wrapper').addClass('visible')
});
