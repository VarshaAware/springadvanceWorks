package com.myapp.spring;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.myapp.spring.model.OrderEntity;

public class TestRestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		List<OrderEntity> orders=
		restTemplate.exchange("http://localhost:8081/spring-advanced/orders", HttpMethod.GET,null,new ParameterizedTypeReference<List<OrderEntity>>() {
		}).getBody();
		orders.forEach(System.out::println);

	}

}
