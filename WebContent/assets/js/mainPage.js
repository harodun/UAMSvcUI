/**
 * 
 */


$(document).ready(function(){
	$.ajaxSetup({ cache: false });
	$('input, textarea').placeholder();
	$("#loginErrorDiv").hide();
	 $('#loginModal').modal('show');
	 
	 $('#loginForm').validate({
			rules: {
				userId: "required",
				password: "required",
				userId: {
					required:true,
					email: true,
					minlength : 8
				},
				password: {
					required:true,
				},
			},
				messages: {
					userId: {
						required: "This field is required.",
						email: "Please enter a valid email address",
						minlength: "UserId must be at least 8 characters."
					},
					password: {
						required: "This field is required.",
					},
				},
					 submitHandler: function(form) {
							// var response = grecaptcha.getResponse();
							 //alert(response.length);
							 $.ajax({
									url : $(form).attr('action'),
									type : $(form).attr('method'),
									
									/*  beforeSend: function(){
									       waitingDialog.show('Custom message', {dialogSize: 'sm', progressType: 'warning'});
									    },*/ 
									contentType: "application/json",
									dataType : 'json',
									data : JSON.stringify(form2js('loginForm','.',true)),
									//data: $(form).serialize(),
									  success : function( data ) {
									        if(data.resultCode == 206 || data.resultCode == 208){
										        	$("#loginErrorDiv").html(data.resultString);
									        		$("#loginErrorDiv").show();
									        		  $(window).scrollTop(0);
									        	} 
										        else if(data.resultCode == 207){
										        	$("#loginErrorDiv").html(data.resultString);
									        		$("#loginErrorDiv").show();
									        		  $(window).scrollTop(0);
									        	} 
									        	else
									        	{
									        	 if(data.resultCode == 205){
									        	 	sessionStorage.lastName = data.user.lastName;
									        	 	sessionStorage.firstName = data.user.firstName;
									        	 	alert(data.user.lastLoggedin)
									        	 	sessionStorage.lastLoggedIn = data.user.lastLoggedin;
									        	 	sessionStorage.currentPage = "adminPage.html";	
										        	window.location.href = "/UASSvcUI/adminPage.html";
									        	} 
									        	}
									        },
									error : function(xhr, err, thrownError) {
										alert('Error in submission ' + err + xhr + thrownError);
										console.error("Error in Submission: "+ thrownError);
									}
								});
							  }
				}); 
});

$('#loginModal').modal({
    backdrop: 'static',
    keyboard: false
});