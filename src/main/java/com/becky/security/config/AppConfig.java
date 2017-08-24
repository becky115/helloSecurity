package com.becky.security.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/conf/mysql.properties")
public class AppConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Value("${jdbc.datasource.url}")
	private String url;
	
	@Value("${jdbc.datasource.username}")
	private String username;
	
	@Value("${jdbc.datasource.password}")
	private String password;
	
	@Value("${jdbc.datasource.driverClass}")
	private String driverClass;
	
	@Value("${jdbc.datasource.validation-query}")
	private String vaildationQuery;
	
	@Value("${jdbc.datasource.testWhileIdle}")
	private Boolean testWhileIdle;
	
	@Value("${jdbc.datasource.timeBetweenEvictionRunsMillis}")
	private Long timeBetweenEvictionRunsMillis;

	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		 System.out.println("property source");
		 return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name="dataSource")
	public DataSource dataSource() {
		logger.info(url);
		logger.info(driverClass);
		logger.info(username);
		logger.info(password);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setValidationQuery(vaildationQuery);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		
		logger.info("dataSource" + dataSource);

		return dataSource;
	}

}
