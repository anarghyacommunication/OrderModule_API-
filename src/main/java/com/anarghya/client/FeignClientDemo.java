package com.anarghya.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anarghya.model.CustomerModel;

//@FeignClient(value="feignDemo",url="http://localhost:8081/api/v1/")
@FeignClient(value = "CustomerModule-API", url = "http://localhost:9092/api-v1")
public interface FeignClientDemo {
	
//	@GetMapping("/getcustomer")
//	public List<CustomerModel> getCustomerDetails();

//	@GetMapping("/get/{customerId}")
//	public CustomerModel getCustomerById(@PathVariable Integer customerId);
	
	@GetMapping("/customers/{customerId}")
	public CustomerModel viewCustomer(@PathVariable Long customerId);

	
}
