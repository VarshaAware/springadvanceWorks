package com.myapp.spring.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.spring.model.CustomerEntity;
import com.myapp.spring.model.OrderEntity;
import com.myapp.spring.service.OrderService;

@RestController
public class OrderRestAPI {

	@Autowired
	private OrderService orderService;

	// http://localhost:8080/context/orderHome
	@GetMapping("/orders")
	public ResponseEntity<List<OrderEntity>> getOrders() {
		return new ResponseEntity<List<OrderEntity>>(orderService.getOrderDetails(), HttpStatus.OK);
	}
	@PostMapping("/customers")
	public  ResponseEntity<String> addNewCustomers(@RequestBody List<CustomerEntity> customers){
		orderService.save(customers);
		return new ResponseEntity<String>("Customer Added",HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerEntity> findCustomerByid(@PathVariable("id")int id,@RequestBody CustomerEntity customerEntity) throws IllegalAccessException, InvocationTargetException {
		
		orderService.updateCustomer(id, customerEntity);
		return new ResponseEntity<CustomerEntity>(customerEntity,HttpStatus.OK);	
	}
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerEntity> findCustomer(@PathVariable("id") int id){
		return new ResponseEntity<CustomerEntity>(orderService.findCustomer(id),HttpStatus.OK);
	}

}
