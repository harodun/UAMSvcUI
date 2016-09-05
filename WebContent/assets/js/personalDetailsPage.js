/**
 * 
 */

$(document).ready(function(){
	$.ajaxSetup({ cache: false });
	$('input, textarea').placeholder();
	$('#userId').val(sessionStorage.userId);
	$('#id').val(sessionStorage.id);
	 $('#personalDetailsForm').validate({
			rules: {
				firstName: "required",
				lastName: "required",
				
					firstName: {
						required:true
					},
					lastName: {
						required:true
					},
					dateOfBirth: {
						required:true
					},
					ssn: {
						required:true
					},
					gender: {
						required:true
					},
					clienId: {
						required:true
					},
					emailId: {
						required:true,
						email: true
					},
			},
					messages: {
						
						firstName: {
							required: "This field is required.",
						},
						lastName: {
							required: "This field is required.",
						},
						dateOfBirth: {
							required: "This field is required.",
						},
						ssn: {
							required: "This field is required.",
						},
						gender: {
							required: "This field is required.",
						},
						clienId: {
							required: "This field is required.",
						},
						emailId: {
							required: "This field is required.",
							email: "Please enter a valid email address"
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
								data : JSON.stringify(form2js('personalDetailsForm','.',true)),
								//data: $(form).serialize(),
								  success : function( data ) {
								        if(data.resultCode == 400){
									        	$("#errorDiv").html(data.result);
								        		$("#errorDiv").show();
								        		  $(window).scrollTop(0);
								        	} 
								        	else
								        	{
								        	 if(data.resultCode == 200){
								        	 	sessionStorage.userId = userId;	
								        	 	sessionStorage.currentPage = "regSuccess.html";	
									        	window.location.href = "/UASSvcUI/registerConfirmation.html";
								        	} 
								        	}
								        },
								error : function(xhr, err, thrownError) {
									alert('Error in submission ' + err + xhr + thrownError);
									console.error("Error in Submission: "+ thrownError);
								}
							});
						  },
						  invalidHandler: function(form, validator) {
						        var errors = validator.numberOfInvalids();
						        if (errors) {                    
						        	 var firstInvalidElement = $(validator.errorList[0].element);
						                $('html,body').scrollTop(firstInvalidElement.offset().top);
						                firstInvalidElement.focus();
						        }
						    }
					 
			}); 
});