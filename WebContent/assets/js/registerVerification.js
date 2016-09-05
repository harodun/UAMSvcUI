/**
 * 
 */

$(document).ready(function(){
	$.ajaxSetup({ cache: false });
	$('input, textarea').placeholder();
	var param = window.location.search.substring(1).split('=')[1];
	$('#success').hide();
	$('#fail').hide();
	 
	$.get("/UASSvcUI/services/regValidationSVC?token="+param, function( data ) {
		if(data.resultCode == 203)
		{ 
			//window.open("invalidSession.html","_self");
			$('#success').show();
			$('#fail').hide();
		}
		else
		{
			$('#success').hide();
			$('#fail').show();
		}
	}); 
	
});