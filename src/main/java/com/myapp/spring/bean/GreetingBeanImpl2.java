package com.myapp.spring.bean;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class GreetingBeanImpl2 implements GreetingBean {
	


	@Override
	public String greeting() {
		// TODO Auto-generated method stub
		return LocalDateTime.of(2017, 11, 27, 12, 35).toString();
	}

}
