package com.soch.uam.ui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.soch.uam.dto.AddressDTO;
import com.soch.uam.dto.SecurityQADTO;
import com.soch.uam.dto.UserDTO;

public class ConstructJSONObject {
	
	/*public static JsonObject createJSON(RegistrationRequest registrationRequest)
	{
		//JSONObject jsonObj = new JSONObject();
		 JsonObjectBuilder userjsonObj = Json.createObjectBuilder();
		 userjsonObj.add("user", createUserJSON(registrationRequest.getUser()) );
		 if(registrationRequest.getUser().getSecurityQAList() != null)
		 {
			 System.out.println(registrationRequest.getSecurityQA().size());
			 //userjsonObj.add("securityQA", createSecurityQAJSON(registrationRequest.getSecurityQA()) );
		 }
		return      userjsonObj.build();
	}*/
	
	public static JsonObject createRequestJSON(UserDTO user)
	{
		//JSONObject jsonObj = new JSONObject();
		 JsonObjectBuilder userjsonObj = Json.createObjectBuilder();
		 userjsonObj.add("user", createUserJSON(user));
		 
		return      userjsonObj.build();
	}
	
	private static JsonArrayBuilder  createAddressJSON(AddressDTO  address) {
		
		JsonArrayBuilder addressArray = Json.createArrayBuilder();
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		jsonBuilder.add("addressLine1",address.getAddressLine1());
		jsonBuilder.add("city",address.getCity());
		jsonBuilder.add("state",address.getState());
		jsonBuilder.add("zipCode",address.getZipcode());
		
		if(address.getAddressLine2() != null)
			jsonBuilder.add("addressLine2",address.getAddressLine2());
		
		
		addressArray.add(jsonBuilder);
			
		return addressArray;
	}
	
	private static JsonArrayBuilder  createAddressArrJSON(Set<AddressDTO> addressDTOs) {
		
		JsonArrayBuilder addressArray = Json.createArrayBuilder();
		for(AddressDTO address : addressDTOs)
		{
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		jsonBuilder.add("addressLine1",address.getAddressLine1());
		jsonBuilder.add("city",address.getCity());
		jsonBuilder.add("state",address.getState());
		jsonBuilder.add("zipCode",address.getZipcode());
		
		if(address.getAddressLine2() != null)
			jsonBuilder.add("addressLine2",address.getAddressLine2());
		
		
		addressArray.add(jsonBuilder);
		}
			
		return addressArray;
	}
	
	
	private static JsonArrayBuilder createSecurityQAJSON(Set<SecurityQADTO> securityQASet)
	{
		//jsonObj.add("securityQA", factory.createArrayBuilder();
		JsonArrayBuilder secQAArray = Json.createArrayBuilder();

		for(SecurityQADTO securityQA : securityQASet)
		{
			secQAArray.add(Json.createObjectBuilder()
					.add("question", securityQA.getQuestion())
					.add("answer", securityQA.getAnswer()));
		}
		
		
		
		return secQAArray;
	}

	
	private static JsonArrayBuilder createAddressListJSON(Set<AddressDTO> addressDTOs)
	{
		//jsonObj.add("securityQA", factory.createArrayBuilder();
		JsonArrayBuilder secQAArray = Json.createArrayBuilder();

		for(AddressDTO addressDTO : addressDTOs)
		{
			secQAArray.add(createAddressJSON(addressDTO));
		}
		
		
		
		return secQAArray;
	}

	public static JsonObject createUserJSON(UserDTO user)
	{
		JsonObjectBuilder jsonObj = Json.createObjectBuilder();
		if(user.getUserId() != null)
		jsonObj.add("userId", user.getUserId());
		
		if(user.getId() != 0)
			jsonObj.add("id", user.getId());
		
		if(user.getFirstName() != null)
		jsonObj.add("firstName", user.getFirstName());
		
		if(user.getLastName() != null)
		jsonObj.add("lastName",user.getLastName());
		
		if(user.getPassword() != null)
		jsonObj.add("password",user.getPassword());
		
		if(user.getEmailId() != null)
			jsonObj.add("emailId",user.getEmailId());
		
		if(user.getMiddleName() != null)
			jsonObj.add("middleName",user.getMiddleName());
		
		
		if(user.getSSN() != null)
			jsonObj.add("ssn",user.getSSN());
		
		if(user.getPhoneNumber() != null)
			jsonObj.add("phoneNumber",user.getPhoneNumber());
		
		if(user.getDateOfBirth() != null)
		{
			jsonObj.add("dateOfBirth",user.getDateOfBirth());
		}
		
		if(user.getAddress() != null && !user.getAddress().isEmpty())
		{
			jsonObj.add("address", createAddressArrJSON(user.getAddress()) );
		}
		
		if(user.getSecurityQA() != null && !user.getSecurityQA().isEmpty())
		{
			jsonObj.add("securityQA", createSecurityQAJSON(user.getSecurityQA()) );
		}

		/*if(user.getOtpPassword() != null)
			jsonObj.add("otpPassword",user.getOtpPassword());
		
		if(user.getAuthToken() != null)
			jsonObj.add("authToken",user.getAuthToken());

		jsonObj.add("otpPreference",user.getOtpPreference());*/
		
		return jsonObj.build();
	}
	
	/*private static JSONObject createUserJSON(User user, )
	{
		JSONObject userJsonObj = new JSONObject();
		userJsonObj.put("userId", user.getUserId());
		userJsonObj.put("firstName", user.getFirstName());
		userJsonObj.put("lastName",user.getLastName());
		userJsonObj.put("password",user.getPassword());
		
		if(user.getEmailId() != null)
			userJsonObj.put("emailId",user.getEmailId());
		
		if(user.getMiddleName() != null)
			userJsonObj.put("middleName",user.getMiddleName());
		
		if(user.getSuffix() != null)
			userJsonObj.put("suffix",user.getSuffix());

		if(user.getDriverLicence() != null)
			userJsonObj.put("driverLicence",user.getDriverLicence());
		
		if(user.getSsn() != null)
			userJsonObj.put("ssn",user.getSsn());
		
		if(user.getPhoneNumber() != null)
			userJsonObj.put("phoneNumber",user.getPhoneNumber());
		
		if(user.getDateOfBirth() != null)
			userJsonObj.put("dateOfBirth",user.getDateOfBirth());
		
		
		return userJsonObj;
	}*/
	
	private static String  converJavaDatetoJSON(String javaDate)
	{
		SimpleDateFormat javaDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat JSONDateFormat = new SimpleDateFormat("yyy-MM-dd");
		try {
			Date date = javaDateFormat.parse(javaDate);
			date.setHours(10);
			return JSONDateFormat.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
