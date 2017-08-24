package com.becky.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername" + userId);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map<String, Object> map = userMapper.select(userId);
		String userName = (String) map.get("user_id");
		String userPass = (String) map.get("passwd");
		logger.info(map.toString());
		
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		
		String[] groupAuthority = map.get("group_authority").toString().split(",");
		for(String role:groupAuthority){
			roles.add(new SimpleGrantedAuthority(role));
		}
		UserDetails user = new User(userName, userPass, roles);
		System.out.println(user.toString());
		System.out.println(user.getAuthorities());
		System.out.println(user.getUsername());
		logger.debug(user.toString());
		return user;
	}



	public static void main(String[] args) {
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		System.out.println(roles.size());
		System.out.println(Arrays.toString(roles.toArray()));
	}


}

