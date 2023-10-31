package com.anarghya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;
import com.anarghya.service.OrderModuleService;

@RestController
public class OrderModuleController {
	@Autowired
	private OrderModuleService service;

	@PostMapping("/order/addOrder/{cartId}/{customerId}")
	public ResponseEntity<OrderResponseModel> addOrder(@PathVariable Integer cartId,@PathVariable Long customerId) {
		OrderResponseModel OrderDetailsEntity = service.addOrder(cartId, customerId);
		return new ResponseEntity<>(OrderDetailsEntity,HttpStatus.OK);
	}
	
	@PostMapping("/order/{orderId}")
	public ResponseEntity<OrderDetailsEntity> updateOrder(@PathVariable Integer orderId,@RequestBody OrderDetailsEntity orderDetails) {
		OrderDetailsEntity updateOrder = service.updateOrder(orderId, orderDetails);
		return new ResponseEntity<>(updateOrder,HttpStatus.OK);

	}
	
	@PostMapping("/order/{orderId}/{status}")
	public ResponseEntity<OrderDetailsEntity> updateOrderStatus(@PathVariable Integer orderId,@PathVariable String status) {
		System.out.println(orderId);
		OrderDetailsEntity updateOrderStatus = service.updateOrderStatus(orderId, status);
		return new ResponseEntity<>(updateOrderStatus,HttpStatus.OK);
		
	}
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderDetailsEntity> cancelOrder(@PathVariable Integer orderId) {
	 OrderDetailsEntity cancelOrder = service.cancelOrder(orderId);
	 return new ResponseEntity<>(cancelOrder,HttpStatus.OK);
	}
	
	@GetMapping("/order/calAmt/{orderId}")
	public ResponseEntity<Double> calculateTotalCost(@PathVariable Integer orderId) {
		Double calculateTotalCost = service.calculateTotalCost(orderId);
		return new ResponseEntity<>(calculateTotalCost,HttpStatus.OK);
	}
	
	@GetMapping("/order/customers/{customerId}")
	public ResponseEntity<List<OrderDetailsEntity>> showOrderByCustomer(@PathVariable Long customerId){
		List<OrderDetailsEntity> showOrderByCustomer = service.showOrderByCustomer(customerId);
		return new ResponseEntity<>(showOrderByCustomer,HttpStatus.OK);
	}
	
	
	
	
	
//================================================================================
	@GetMapping("/getAll")
	public List<OrderResponseModel> getCustomerOrderDetails() {
		
		List<OrderResponseModel> mainResponse = service.getCustomerOrderDetails();
		
		return mainResponse;

	}
	
	@GetMapping("/get")
	public List<OrderDetailsEntity> getOrders(){
		return service.getOrderDetails();
	}
	
	

	@GetMapping("/orderDetails")
	public List<OrderDetailsEntity> getOrderDetails() {

		return service.getOrderDetails();

	}

	@PostMapping("/save")
	public ResponseEntity<String> saveDetails(@RequestBody OrderDetailsEntity orderDetails) {
		System.out.println(orderDetails);
		String addOrderDetails = service.upsertOrderDetails(orderDetails);

		return new ResponseEntity<String>(addOrderDetails, HttpStatus.CREATED);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<String> updateDetails(@RequestBody OrderDetailsEntity orderDettails) {
		String orderDetails = service.upsertOrderDetails(orderDettails);
		return new ResponseEntity<>(orderDetails, HttpStatus.CREATED);
	}

	@GetMapping("/edit/{orderId}")
	public OrderDetailsEntity getOrderById(@PathVariable Integer orderId) {

		return service.getOrderById(orderId);
	}

	@PutMapping("/update/{orderId}")
	public ResponseEntity<String> updateById(@PathVariable Integer orderId,
			@RequestBody OrderDetailsEntity updateOrderDetails) {

		String updatedDetails = service.update(updateOrderDetails, orderId);
		return new ResponseEntity<>(updatedDetails, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer orderId) {

		String deleteOrderById = service.deleteOrderById(orderId);

		return new ResponseEntity<>(deleteOrderById, HttpStatus.GONE);
	}
	
	
}
