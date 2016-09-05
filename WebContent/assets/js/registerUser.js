/**
 * 
 */

$("#sinupPageSubmitBtn")
.click(
		function(evt) {
		$("#QAErrorDiv").hide();
			var errMsg = "";
								
			if (validateSecurityQA(this)) {
				 errMsg = "Security Questions or Answers cannot be same." 
				$("#QAErrorDiv").html(
						"Security Questions or Answers cannot be same.");
						}
		/* else  if (pwd == rPwd) {
		 		
				$("#registerUserForm").submit();
			} else {
				errMsg = "Password and repeat password don't match"
			}*/
			
			if(errMsg != "")
			{
				
				 evt.preventDefault();
				$("#QAErrorDiv").html(errMsg);
				$("#QAErrorDiv").show();
				//$(window).scrollTop(0);
				document.getElementById('QAErrorDiv').scrollIntoView();
			}
		});


function validateSecurityQA()
{
	var q1 = document.getElementById("securityQA[0].question").value;
	var q2 = document.getElementById("securityQA[1].question").value;
	var q3 = document.getElementById("securityQA[2].question").value;
	
	var a1 = document.getElementById("securityQA[0].answer").value;
	var a2 = document.getElementById("securityQA[1].answer").value;
	var a3 = document.getElementById("securityQA[2].answer").value;
	if(q1 == '' || q2 == '' || q3 =='' || a1 == '' || a2=='' || a3=='')
		return false;
	
	if (((q1 == q2)||
			 (q2 == q3) ||
			 (q1 == q3)) ||((a1 == a2)||
			 (a2 == a3) ||
			 (a1 == a3))  ) {
			return true;
	} else {
		return false;
		
	}
}

$(document).ready(function(){
	$.ajaxSetup({ cache: false });
	$('input, textarea').placeholder();
	
	 $('#registerUserForm').validate({
			rules: {
				userId: "required",
				password: "required",
				confirmPassword: "required",
				firstName: "required",
				lastName: "required",
				emailId: "required",
				
				userId: {
						required:true,
						email: true,
						minlength : 8
					},
					password: {
						required:true,
						pwcheck: true,
						minlength : 8
					},
					confirmPassword: {
						required:true,
						pwcheck: true,
						minlength: 8,
						equalTo: "#password"
					},
					firstName: {
						required:true
					},
					lastName: {
						required:true
					},
					'securityQA[0].answer': {
						required:true,
					},
					'securityQA[1].answer': {
						required:true,
					},
					'securityQA[2].answer': {
						required:true,
					},
					'securityQA[0].question': {
						required:true,
					},
					'securityQA[1].question': {
						required:true,
					},
					'securityQA[2].question': {
						required:true,
					},
					emailId: {
						required:true,
						email: true
					},
			},
			 groups: {
			        seqAnswers: "securityQA[0].answer securityQA[1].answer securityQA[2].answer"
			    },
					messages: {
						userId: {
							required: "This field is required.",
							email: "Please enter a valid email address",
							minlength: "UserId must be at least 8 characters."
						},
						password: {
							required: "This field is required.",
							 pwcheck: "Invalid Password format. ",
							minlength: "Password must be at least 8 characters."
						},
						confirmPassword: {
							required: "This field is required.",
							 pwcheck: "Invalid Password format. ",
							minlength: "Password must be at least 8 characters.",
							equalTo: "Password does not match."
						},
						firstName: {
							required: "This field is required.",
						},
						lastName: {
							required: "This field is required.",
						},
						emailId: {
							required: "This field is required.",
							email: "Please enter a valid email address"
						},
						'securityQA[0].answer': {
							required: "This field is required.",
							notEqualToGroup:  "Please enter a Unique Value."
						},
						'securityQA[1].answer': {
							required: "This field is required.",
							notEqualToGroup:  "Please enter a Unique Value."
						},
						'securityQA[2].answer': {
							required: "This field is required.",
							notEqualToGroup: "Please enter a Unique Value."
						},
						'securityQA[0].question': {
							required: "This field is required.",
						},
						'securityQA[1].question': {
							required: "This field is required.",
						},
						'securityQA[2].question': {
							required: "This field is required.",
						},
						seqAnswers: {
							notEqualToGroup : "Should not be same"
						}
						
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
								data : JSON.stringify(form2js('registerUserForm','.',true)),
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
								        	 	sessionStorage.userId =  $('#userId').val();
								        	 	sessionStorage.id = data.user.id;
								        	 	sessionStorage.currentPage = "personalDetailsPage.html";	
									        	window.location.href = "/UASSvcUI/personalDetailsPage.html";
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
	
	 $.validator.addMethod("pwcheck",
          function(value, element) {
              return /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.\-_*])([a-zA-Z0-9@#$%^&+=*.\-_]){3,}$/.test(value);
      });
	 
	 $.validator.addMethod("userIdheck",
          function(value, element) {
		 
              return /^[a-z0-9._]{6,}$/.test(value);
      });
	 
	 $.validator.addMethod("notEqualToGroup", function() {
			alert();
			});
		
	
});



$('#userId').change(function(){
	$.get("/UASSvcUI/services/checkUseravailablility?userId="+$('#userId').val(), function( data ) {
						if(data.resultCode == 202)
						{ 
							//window.open("invalidSession.html","_self");
							$("#userIdErrorDiv").html(data.result);
							$("#userIdErrorDiv").show();
							$('#userId').focus();
						}
						else
						{
							$("#userIdErrorDiv").hide();
						}
					}); 
});


var	 toggleBtn = true;

$("#acceptTerms").click(function(){
	if(toggleBtn)
		$("#sinupPageSubmitBtn").removeAttr("disabled");
		
	else
		$("#sinupPageSubmitBtn").attr("disabled", "disabled");

	toggleBtn = !toggleBtn;
			
});

