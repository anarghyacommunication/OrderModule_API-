package com.anarghya.model;

public class OrderResponseModel{

	private OrderDetailsEntity orderEntity;
	
	
	private CustomerModel customerModel;
	
	
	private CartModuleEntityRequest cartModule;
	
	public CartModuleEntityRequest getCartModule() {
		return cartModule;
	}

	public void setCartModule(CartModuleEntityRequest cartModule) {
		this.cartModule = cartModule;
	}

	public CustomerModel getCustomerModel() {
		return customerModel;
	}

	public void setCustomerModel(CustomerModel customerModel) {
		this.customerModel = customerModel;
	}

	public OrderDetailsEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderDetailsEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	
	

}
