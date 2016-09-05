package com.soch.uam.ui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.soch.uam.dto.UserDTO;
import com.soch.uam.response.UAMUIResponse;
import com.soch.uam.response.UserSVCResp;
import com.soch.uam.ui.util.ConstructJSONObject;


@Controller
@PropertySource(value = { "file:c:\\harish\\application.properties"})
public class UAMUIServiceController {
	
	@Autowired
	MessageSource messageSource;
	
	 @Autowired
	 private Environment environment;
	
	@RequestMapping(value="/signUp", method = RequestMethod.POST )
	@ResponseBody
    public UAMUIResponse registerUser(@RequestBody  UserDTO user)
	{
		   	MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
	        headers.add("Accept", "application/json");
	        headers.add("Content-Type", "application/json");
	        UAMUIResponse UAMUIResponse = new UAMUIResponse();
	        JsonObject jsonObj = ConstructJSONObject.createRequestJSON(user);
	        System.out.println("jsonObj.toString() " +jsonObj.toString());
	        org.springframework.http.HttpEntity request = new org.springframework.http.HttpEntity(jsonObj.toString(), headers);
		    RestTemplate restTemplate = new RestTemplate();
		    
		    //System.out.println(ConfigManager.getPropertyValue("hostName"));
		    UserSVCResp userSVCResp = restTemplate.postForObject(environment.getRequiredProperty("hostName")+"/UASWebservices/signup", request, UserSVCResp.class);
		    UAMUIResponse.setResultCode(userSVCResp.getResultCode());
		    UAMUIResponse.setresultString(userSVCResp.getresultString());
		    UAMUIResponse.setUser(userSVCResp.getUser());
       
		return UAMUIResponse;
    }
	
	//checkUseravailablility
	
	@RequestMapping(value="/checkUseravailablility", method = RequestMethod.GET )
	@ResponseBody
    public UAMUIResponse checkUseravailablility(@RequestParam(value="userId") String userId)
	{
		   	MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
	        headers.add("Accept", "application/json");
	        headers.add("Content-Type", "application/json");
	        UAMUIResponse UAMUIResponse = new UAMUIResponse();
		    RestTemplate restTemplate = new RestTemplate();
		    
		    //System.out.println(ConfigManager.getPropertyValue("hostName"));
		    UserSVCResp userSVCResp = restTemplate.getForObject(environment.getRequiredProperty("hostName")+"/UASWebservices/checkDuplicateId?userId={userId}", UserSVCResp.class, userId);
		    UAMUIResponse.setResultCode(userSVCResp.getResultCode());
		    UAMUIResponse.setresultString(userSVCResp.getresultString());
	        System.out.println(UAMUIResponse.getResultCode());
       
		return UAMUIResponse;
    }
	
	@RequestMapping(value="/regValidationSVC", method = RequestMethod.GET )
	@ResponseBody
    public UAMUIResponse regValidtion(@RequestParam(value="token") String token)
	{
	        UAMUIResponse UAMUIResponse = new UAMUIResponse();
		    RestTemplate restTemplate = new RestTemplate();
		    
		    //System.out.println(ConfigManager.getPropertyValue("hostName"));
		    UserSVCResp userSVCResp = restTemplate.getForObject(environment.getRequiredProperty("hostName")+"/UASWebservices/validateRegisterSVC?token={token}", UserSVCResp.class, token);
		    UAMUIResponse.setResultCode(userSVCResp.getResultCode());
		    UAMUIResponse.setresultString(userSVCResp.getresultString());
	        System.out.println(UAMUIResponse.getResultCode());
       
		return UAMUIResponse;
    }
	
	
	@RequestMapping(value="/signIn", method = RequestMethod.POST )
	@ResponseBody
    public UAMUIResponse signIn(@RequestBody  UserDTO user)
	{
		   	MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
	        headers.add("Accept", "application/json");
	        headers.add("Content-Type", "application/json");
	        UAMUIResponse UAMUIResponse = new UAMUIResponse();
	        JsonObject jsonObj = ConstructJSONObject.createRequestJSON(user);
	        System.out.println("jsonObj.toString() " +jsonObj.toString());
	        org.springframework.http.HttpEntity request = new org.springframework.http.HttpEntity(jsonObj.toString(), headers);
		    RestTemplate restTemplate = new RestTemplate();
		    
		    //System.out.println(ConfigManager.getPropertyValue("hostName"));
		    UserSVCResp userSVCResp = restTemplate.postForObject(environment.getRequiredProperty("hostName")+"/UASWebservices/signIn", request, UserSVCResp.class);
		    System.out.println(userSVCResp.getResultCode());
		    UAMUIResponse.setResultCode(userSVCResp.getResultCode());
		    UAMUIResponse.setresultString(userSVCResp.getresultString());
		    UAMUIResponse.setUser(userSVCResp.getUser());
       
		return UAMUIResponse;
    }
	
	
    /*
    public UASUIResponse regValidtion(@RequestBody  UserDTO user)
	{
		   	MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
	        headers.add("Accept", "application/json");
	        headers.add("Content-Type", "application/json");
	        UASUIResponse UASUIResponse = new UASUIResponse();
		    RestTemplate restTemplate = new RestTemplate();
		    
		    System.out.println("token "+user.getToken());
		    //System.out.println(ConfigManager.getPropertyValue("hostName"));
		    Map<String, String> params = new HashMap<String, String>();
		    params.put("token", user.getToken());
		    params.put("userId", user.getUserId());

		    String userId = restTemplate.getForObject(environment.getRequiredProperty("hostName")+"/UASWebservices/validateRegisterSVC", String.class, params);
		    if(userId != null && userId.equalsIgnoreCase(user.getUserId()))
		    {
		    	UASUIResponse.setResultCode(200);
		    }
		    else
		    {
		    	UASUIResponse.setResultCode(201);
		    }
		    UASUIResponse.setResultCode(userSVCResp.getResultCode());
		    UASUIResponse.setresultString(userSVCResp.getresultString());
	        System.out.println(UASUIResponse.getResultCode());
       
		return UASUIResponse;
    }*/

}
