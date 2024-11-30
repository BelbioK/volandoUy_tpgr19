const profileElement = document.getElementById('profile');
if(profileElement){
	profileElement.addEventListener('click', function() {
	    document.getElementById('profile-menu').classList.toggle('hide');
	});	
}