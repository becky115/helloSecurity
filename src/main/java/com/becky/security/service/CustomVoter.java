package com.becky.security.service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;

import com.becky.security.domain.UserDomain;
import com.becky.security.filter.FilterSecurityMetadataSource;

public class CustomVoter implements AccessDecisionVoter{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomVoter.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		System.out.println("AccessDecisionVoter support");
		System.out.println("AccessDecisionVoter support");
		System.out.println("AccessDecisionVoter support");
		return true;
	}

	@Override
	public boolean supports(Class clazz) {
		 //return MethodInvocation.class.isAssignableFrom(clazz);
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection attributes) {
		
		
		
		String userId = authentication.getName();
		UserDetails user = null;
		Object principal = authentication.getPrincipal();
		
		FilterInvocation fI = (FilterInvocation) object;
		String url = fI.getRequestUrl();
		

		
		
		
		logger.info("principal" + principal);
		logger.info("vote url>>>> " + url);
		//if(attributes.isEmpty()){
			if(principal instanceof UserDetails){
				user = (UserDetails) principal;
				logger.info("userId:  " + userId);
				
				
				HttpSession session = fI.getRequest().getSession();
				System.out.println("session" + session);
				UserDomain userDomain = userService.getUserDomain(session);
				System.out.println(userDomain);
				Integer groupSeq = null;
				if(userDomain != null){
					groupSeq = userDomain.getGroupSeq();
					
					
					boolean test = groupSeq == null ? false:menuService.checkAuth(groupSeq, url);
					logger.info("test " + test);
					if(test){
						return AccessDecisionVoter.ACCESS_GRANTED;
					}else{
						return AccessDecisionVoter.ACCESS_DENIED;
					}
				}
	
			}
	//	}

		return AccessDecisionVoter.ACCESS_GRANTED;
	}

}
