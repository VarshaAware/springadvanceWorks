package com.myapp.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myapp.spring.config.AppConfig;
import com.myapp.spring.service.OrderService;

public class Appinitializer2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ioc=
				new AnnotationConfigApplicationContext(AppConfig.class);
		
OrderService orderService=		ioc.getBean(OrderService.class);
orderService.processOrderStatusUpdate
(orderService.getOrderDetails(), "Processing");
ioc.close();
	


	}

}
