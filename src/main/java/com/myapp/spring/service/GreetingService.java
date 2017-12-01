package com.myapp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.myapp.spring.bean.GreetingBean;

@Service
public class GreetingService {
	
	@Autowired
	@Qualifier("greetingBeanImpl2")
	private GreetingBean greetingBean;
	
	public String display() {
		return greetingBean.greeting();
	}

}
