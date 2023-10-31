package com.anarghya.service;

import java.util.List;

import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;

public interface OrderModuleService {

	public OrderResponseModel addOrder(Integer cartId,Long customerId);
	
	public OrderDetailsEntity updateOrder(Integer orderId,OrderDetailsEntity orderDetails);
	
	public OrderDetailsEntity updateOrderStatus(Integer orderId,String status);
	
	public OrderDetailsEntity cancelOrder(Integer orderId);
	
	public Double calculateTotalCost(Integer orderId);
	
	public OrderDetailsEntity getOrderById(Integer orderId);
	
	public List<OrderDetailsEntity> showOrderByCustomer(Long customerId);
	
	
	public List<OrderDetailsEntity> getOrderDetails();

//===================================================================
	
	public String upsertOrderDetails(OrderDetailsEntity orderDetails);

	public String update(OrderDetailsEntity orderDetails, Integer orderId);

	

	public String deleteOrderById(Integer orderId);
	
	public List<OrderResponseModel> getCustomerOrderDetails();

}
