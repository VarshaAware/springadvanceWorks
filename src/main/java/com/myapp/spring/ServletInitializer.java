package com.myapp.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.myapp.spring.config.AppConfig;

// Instead of web.xml WebApplication Initializer Class will be used
public class ServletInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		// TODO Auto-generated method stub
		
		// IOC Container
		
		AnnotationConfigWebApplicationContext rootContext=new
				AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		rootContext.setServletContext(arg0);
		ServletRegistration.Dynamic dispatcher=
				arg0.addServlet("dispatcher", new DispatcherServlet(rootContext));
		
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	

}
