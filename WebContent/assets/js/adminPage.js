/**
 * 
 */

$(document).ready(function(){
	$.ajaxSetup({ cache: false });
	$('input, textarea').placeholder();
	document.getElementById("userName").innerHTML = (sessionStorage.firstName+', '+sessionStorage.lastName);
	
	document.getElementById("lastLoggedIn").innerHTML = sessionStorage.lastLoggedIn;
	
});