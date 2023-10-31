package com.anarghya.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.anarghya.model.CartModuleEntityRequest;


@FeignClient(name = "feigncart", url = "http://localhost:8085/api/v1")
public interface FeignCartClient {
	
	@GetMapping("/cart/cartInfo/{cartId}")
	public ResponseEntity<CartModuleEntityRequest> viewCartInfo(@PathVariable Integer cartId);

}
