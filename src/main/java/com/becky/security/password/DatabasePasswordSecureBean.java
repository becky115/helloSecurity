package com.becky.security.password;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
//import org.springframework.security.authentication.encoding.PasswordEncoder;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.becky.security.domain.UserDomain;
import com.becky.security.service.UserService;


public class DatabasePasswordSecureBean extends JdbcDaoSupport{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Transactional(rollbackFor = { Exception.class })
	public void secureDatabase() throws Exception{
		List<UserDomain> list = userService.selectList();
		System.out.println("list " + list.size());

		if(list != null & list.size() > 0){
			for(int i=0; i<list.size(); i++){
				UserDomain user = list.get(i);
				String userId = user.getUserId();
				String userPassword = user.getPasswd();
				String encodedPassword = passwordEncoder.encode(userPassword);
				
				int updateResult = userService.update(userId, encodedPassword);
				System.out.println("updateResult: " + updateResult);
			}
		}
		
		//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
}
