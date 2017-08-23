package com.becky.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.becky.security.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername" + userId);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(userId);
		System.out.println(sqlSession);
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map<String, Object> map = userMapper.select(userId);
		String userName = (String) map.get("user_id");
		String userPass = (String) map.get("passwd");
		//String roleId = (String) map.get("role_id");
		logger.debug(map.toString());
		
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

		UserDetails user = new User(userName, userPass, roles);
		return user;
	}
	
	public static void main(String[] args) {
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		System.out.println(roles.size());
		System.out.println(Arrays.toString(roles.toArray()));
	}


}

