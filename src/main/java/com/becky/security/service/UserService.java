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

import com.becky.security.domain.UserDomain;
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
		UserDomain user = userMapper.select(userId);
		String userName = user.getUserId();
		String userPass = user.getPasswd();
		Character lockFlag = user.getLockFlag();
		logger.info(user.toString());
		
		Boolean enabled = true;
		Boolean accountNonExpired = true;
		Boolean credentialsNonExpired  = true;
		Boolean accountNonLocked = lockFlag.equals('Y') ? false:true;
		System.out.println("lock" + (accountNonLocked));
		
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		String[] groupAuthority = user.getGroupAuthority().toString().split(",");
		for(String role:groupAuthority){
			authorities.add(new SimpleGrantedAuthority(role));
		}
		UserDetails userDetail = new User(userName, userPass, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		
		logger.debug(user.toString());
	
		return userDetail;
	}



	public static void main(String[] args) {
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		System.out.println(roles.size());
		System.out.println(Arrays.toString(roles.toArray()));
	}


}

