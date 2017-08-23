package com.becky.security.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:conf/mysql.properties")
public class AppConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	private @Value("${jdbc.datasource.url}") String url;
	private @Value("${jdbc.datasource.username}") String username;
	private @Value("${jdbc.datasource.password}") String password;
	private @Value("${jdbc.driverClass}") String driverClass;

	@Bean(name="dataSource")
	public DataSource dataSource() {
		logger.debug(url);
		logger.debug(driverClass);
		logger.debug(username);
		logger.debug(password);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
	
		return dataSource;
	}
}
