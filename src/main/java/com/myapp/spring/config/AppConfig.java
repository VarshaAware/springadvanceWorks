package com.myapp.spring.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@ComponentScan(basePackages="com.myapp.spring")
public class AppConfig {
	
	// Database Connection pool
	@Bean
	public DataSource dataSource() throws SQLException {
		
		OracleDataSource dataSource=new OracleDataSource();
		dataSource.setUser("Orclexi06");
		dataSource.setPassword("Orclexi06");
		dataSource.setURL("jdbc:oracle:thin:@10.2.0.28:1521:orcl");
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;
		
	}
	
	
	// Configure The Entitymanagerfactory 
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
		LocalContainerEntityManagerFactoryBean factory=
				new LocalContainerEntityManagerFactoryBean();
		
		// Enable Hibernate as The ORM vendor
		final HibernateJpaVendorAdapter jpaVendorAdapter=
				new HibernateJpaVendorAdapter();
		
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.Oracle10gDialect");
		jpaVendorAdapter.setShowSql(true);
		//jpaVendorAdapter.setGenerateDdl(true);
		
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		Properties hibernateProps=new Properties();
		hibernateProps.put("hibernate.hbm2ddl.auto", "update");
		
		factory.setDataSource(dataSource());
		//factory.setJpaProperties(hibernateProps);
		factory.setPackagesToScan("com.myapp.spring.model");
		return factory;
	}

	// Transaction Properties for Container Managed Transaction
	
	
	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {
		JpaTransactionManager transactionManager=new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	
	@Bean
	public ViewResolver viewResolver() {
		
		final TilesViewResolver resolver=new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
	@Bean
	public TilesConfigurer tileConfigurer() {
		
		TilesConfigurer tilesConfigurer=new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/jsp/tiles.xml");
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	
}











