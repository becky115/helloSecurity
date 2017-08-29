package com.becky.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.becky.security.domain.UserDomain;
import com.becky.security.service.MenuService;
import com.becky.security.service.UserService;

public class FilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	
	private static final Logger logger = LoggerFactory.getLogger(FilterSecurityMetadataSource.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	
	public FilterSecurityMetadataSource() {
		// TODO Auto-generated constructor stub
		System.out.println("TestMetadataSource");
	}
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//		System.out.println("getAttributes");
//		System.out.println("getAttributes");
//		System.out.println("getAttributes");
//		System.out.println("getAttributes");
//		final HttpServletRequest request = ((FilterInvocation) object).getRequest(); 
//		final String httpMethod = request.getMethod().toUpperCase(); 
		//String requestUrl = request.getRequestURI().substring(request.getContextPath().length()); 
//		final String key = String.format("%s %s", httpMethod, requestUrl); 
//
//		System.out.println(httpMethod);
//		System.out.println(requestUrl);
//		System.out.println(key);
//			
//			
//		String[] roles = new String[] { "ROLE_ADMIN"};
//		return SecurityConfig.createList(roles);

		
		FilterInvocation fI = (FilterInvocation) object;
		Object principal = null;
		UserDetails user = null;
		if(SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null){
			System.out.println("An Authentication object was not found in the SecurityContext");
			System.out.println("An Authentication object was not found in the SecurityContext");
			System.out.println("An Authentication object was not found in the SecurityContext");
			System.out.println("An Authentication object was not found in the SecurityContext");
			System.out.println("An Authentication object was not found in the SecurityContext");
			throw new AuthenticationCredentialsNotFoundException("An Authentication object was not found in the SecurityContext");
		}else{
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			

			System.out.println("principal " + principal);
		
			if(principal instanceof UserDetails){
				
				user = (UserDetails) principal;
				System.out.println("---------");
				System.out.println(user.getUsername());
				System.out.println(user.getAuthorities().toString());
			}
			
		}

		
		
		
		//to strip of query string from url.
		String url = fI.getRequestUrl();
//		fI.getHttpRequest()
		logger.info("url>>>> " + url);
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		
		
		if(menuService != null){
			if(user != null){
				HttpSession session = fI.getRequest().getSession();
				UserDomain userDomain = userService.getUserDomain(session);
				Integer groupSeq = null;
				if(userDomain != null){
					groupSeq = userDomain.getGroupSeq();
				}
				boolean test = groupSeq == null ? false:menuService.checkAuth(groupSeq, url);
				logger.info("groupSeq " + groupSeq + " test " + test +","+ (url.equals("/") || test));
				if(url.equals("/") || test){
					for (GrantedAuthority auth : user.getAuthorities()) {
						System.out.println(auth.getAuthority());
						if(auth.getAuthority().equals("ROLE_ADMIN") || auth.getAuthority().equals("ROLE_USER"))
						attributes.add(new SecurityConfig(auth.getAuthority().toString()));
					}
					
				}else{
					System.out.println("제한.......됨");
					attributes.add(new SecurityConfig("ROLE_RESTRICTED"));
				}
			}
		}
		
		
		System.out.println("attributes>> " + attributes.size());
		if(attributes.isEmpty()){
			 attributes.add(new SecurityConfig("IS_AUTHENTICATED_FULLY"));
		}

		return attributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		System.out.println("getAllConfigAttributes");
		System.out.println("getAllConfigAttributes");
		System.out.println("getAllConfigAttributes");
		System.out.println("getAllConfigAttributes");
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("supports");
		System.out.println("supports");
		System.out.println("supports");
		System.out.println("supports "  + clazz.getName());
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	
	public static void main(String[] args) {
		String url = "/asdasd";
		Boolean test = false;
		if(url.equals("/") || test){
			System.out.println("sada11s");
		}else{
			System.out.println("sadas");
		}
	}

}
