package com.myapp.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myapp.spring.config.AppConfig;
import com.myapp.spring.model.CatalogItemEntity;
import com.myapp.spring.model.CustomerEntity;
import com.myapp.spring.model.OrderEntity;
import com.myapp.spring.model.OrderItemEntity;
import com.myapp.spring.service.OrderService;

public class AppInitializer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnnotationConfigApplicationContext ioc=
				new AnnotationConfigApplicationContext(AppConfig.class);
		
//GreetingService greetingService=	ioc.getBean(GreetingService.class);

//System.out.println(greetingService.display());
		
	OrderService orderService=ioc.getBean(OrderService.class);
		
		System.out.println("AOP Proxy "+AopUtils.isAopProxy(orderService));
		System.out.println("JDK Proxy "+AopUtils.isJdkDynamicProxy(orderService));
		System.out.println("CGLIB Proxy "+AopUtils.isCglibProxy(orderService));
		
		
		
		
		
		List<OrderItemEntity> orderItems=new ArrayList<>();
		
		
		CustomerEntity customerEntity=new CustomerEntity("Abcd", "wxyz","abc@xyz.com");
		
		OrderEntity orderEntity=new OrderEntity(new Date(), "A123", 
				new Date(), "New");
		OrderItemEntity orderItemEntity=new OrderItemEntity("New", 
				new BigDecimal(4567.5), new Date(), 10);
		
		Set<OrderItemEntity> orderItemEntities=new HashSet<>();
		orderItemEntities.add(orderItemEntity);
		orderEntity.setOrderItems(orderItemEntities);
		
		Set<OrderEntity> orders=new HashSet<>();
		
		orders.add(orderEntity);
		
		//orderItemEntity.setOrder(orderEntity);
		customerEntity.setOrders(orders);
		
		List<CustomerEntity> customers=new ArrayList<>();
		customers.add(customerEntity);
		orderService.save(customers);
		
		
		
		List<CatalogItemEntity> catalogItems=new ArrayList<>();
catalogItems.add(new CatalogItemEntity("023-788", "IPHONE", "Electronics"));
catalogItems.add(new CatalogItemEntity("222-5444788", "IPAD", "Electronics"));
catalogItems.add(new CatalogItemEntity("113-788", "Yellow Notepad", "Office Product"));
		//orderService.saveCatalogItems(catalogItems);

		
		
		
		//orderService.save(orderEntity);
		
		//orderService.getOrderDetails().forEach(System.out::println);
		
	ioc.close();
	}

}
