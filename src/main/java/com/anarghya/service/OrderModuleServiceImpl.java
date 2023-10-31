package com.anarghya.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anarghya.client.FeignCartClient;
import com.anarghya.client.FeignClientDemo;
import com.anarghya.model.CartModuleEntityRequest;
import com.anarghya.model.CustomerModel;
import com.anarghya.model.MedicineModule;
import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;
import com.anarghya.repository.OrderModuleRepo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class OrderModuleServiceImpl implements OrderModuleService {

	@Autowired
	private OrderModuleRepo orderRepo;

	@Autowired
	private FeignClientDemo feign;

	@Autowired
	private FeignCartClient feignCart;

	@Override
	public String upsertOrderDetails(OrderDetailsEntity orderDetails) {

		if (orderDetails != null) {
			orderRepo.save(orderDetails);
			return "OrderPlaced";
		} else {
			return "OrderNotPlaced";
		}

	}

	public String update(OrderDetailsEntity orderDetails, Integer orderId) {
		orderRepo.save(orderDetails);
		return "Order Updated";

	}

	public OrderDetailsEntity getOrderById(Integer orderId) {
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if (findById.isPresent()) {
			return findById.get();
		}

		return null;
	}

	@Override
	public String deleteOrderById(Integer orderId) {
		// TODO Auto-generated method stub

		if (orderRepo.existsById(orderId)) {
			orderRepo.deleteById(orderId);
			return "Record orderId : " + orderId + " is Deleted";
		} else {
			return "Record is not deleted";
		}

	}

	@Override
	public List<OrderResponseModel> getCustomerOrderDetails() {
		List<OrderResponseModel> mainResponse = new ArrayList<OrderResponseModel>();
		OrderResponseModel orderResponseModel = new OrderResponseModel();
		CustomerModel customer = new CustomerModel();

//		List<OrderDetailsEntity> orderDetails = new ArrayList<OrderDetailsEntity>();
//				orderRepo.findAll().forEach(p -> orderDetails.add(p));
//
//		List<CustomerModel> customerModel = feign.getCustomerDetails();
//		
//		for (OrderDetailsEntity orderModel : orderDetails) {
//			orderResponseModel.setOrderId(orderModel.getOrderId());
//			orderResponseModel.setOrderDate(orderModel.getOrderDate());
//			orderResponseModel.setOrderDispatchDate(orderModel.getOrderDispatchDate());
//			orderResponseModel.setCost(orderModel.getCost());
//			orderResponseModel.setStatus(orderModel.getStatus());
//			orderResponseModel.setTotalCost(orderModel.getTotalCost());
//			mainResponse.add(orderResponseModel);
//		}
//		
//		for (CustomerModel customerData : customerModel) {
//			customer.setCustomerId(customerData.getCustomerId());
//			customer.setCustomerName(customerData.getCustomerName());
//			customer.setEmailId(customerData.getEmailId());
//			customer.setMoblieNo(customerData.getMoblieNo());
//			customer.setPassword(customerData.getPassword());
//			orderResponseModel.setCustomerModel(customerModel);
//			
//		}
		return mainResponse;
	}

//===================================================================================	

	@Override
	public List<OrderDetailsEntity> getOrderDetails() {
		List<OrderDetailsEntity> entity = new ArrayList<OrderDetailsEntity>();
		orderRepo.findAll().forEach(p -> entity.add(p));
		// TODO Auto-generated method stub
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderResponseModel addOrder(Integer cartId, Long customerId) {
		CartModuleEntityRequest cartModuleEntity = new CartModuleEntityRequest();

		OrderResponseModel orderResponse = new OrderResponseModel();
		
		ArrayList<MedicineModule> medicineList=new ArrayList<>();
		OrderDetailsEntity orderEntity = new OrderDetailsEntity();
		CustomerModel customer = new CustomerModel();
		MedicineModule medicineEntity = new MedicineModule();

		if (cartId != null) {
			CartModuleEntityRequest cartEntity = feignCart.viewCartInfo(cartId).getBody();
			System.out.println(cartEntity.getMedicines().toString());
			CustomerModel customerDetails = feign.viewCustomer(customerId);
			customer.setCustomerId(customerDetails.getCustomerId());
			customer.setCustomerName(customerDetails.getCustomerName());
			customer.setEmailId(customerDetails.getEmailId());
			customer.setMoblieNo(customerDetails.getMoblieNo());

			cartModuleEntity.setCartId(cartEntity.getCartId());

			cartModuleEntity.setCost(cartEntity.getCost());

			cartModuleEntity.setQuantity(cartEntity.getQuantity());
			List<MedicineModule> medicines = cartEntity.getMedicines();
//			System.out.println(medicines);
			for (MedicineModule medicine : medicines) {
				medicineEntity.setId(medicine.getId());
				System.out.println(medicine.getId());
				medicineEntity.setName(medicine.getName());
				
				medicineEntity.setCompany(medicine.getCompany());
				medicineEntity.setExpiryDate(medicine.getExpiryDate());
				medicineEntity.setMfdDate(medicine.getMfdDate());
				medicineEntity.setCost(medicine.getCost());
				medicineEntity.setQuantity(medicine.getQuantity());
				medicineEntity.setBatchCode(medicine.getBatchCode());
				medicineEntity.setCategory(medicine.getCategory());
				medicineEntity.setDescription(medicine.getDescription());
				medicineEntity.setFormula(medicine.getFormula());
				medicineList.add(medicineEntity);
				System.out.println(medicineEntity);
				cartModuleEntity.setMedicines(medicineList);
				
			}
//			 objectmapper om = new objectmapper()
//				        .registermodule(new javatimemodule())
//				        .configure(serializationfeature.write_date_timestamps_as_nanoseconds, false)
//				        .configure(serializationfeature.fail_on_empty_beans, false)
//				        .configure(deserializationfeature.fail_on_unknown_properties, false)
//				        .configure(deserializationfeature.read_date_timestamps_as_nanoseconds, false)
//				        .setserializationinclusion(jsoninclude.include.non_null);
//				    string json = om.writevalueasstring(instant.now());
//				    om.readvalue(json, instant.class);
		
			
			orderEntity.setCustomerId(customerDetails.getCustomerId());
//			orderEntity.setTotalCost(cartEntity.getCost());
			orderEntity.setCost(cartEntity.getCost());
			orderEntity.setOrderDate(LocalDate.now());
			orderEntity.setStatus("Approved");

			orderEntity.setCustomerEmailId(customerDetails.getEmailId());

			orderResponse.setCartModule(cartModuleEntity);
			orderResponse.setOrderEntity(orderEntity);
//			orderResponse.setCustomerModel(customer);

			orderRepo.save(orderEntity);

			return orderResponse;

		} else {
			return null;
		}

	}

	@Override
	public OrderDetailsEntity updateOrder(Integer orderId, OrderDetailsEntity orderDetails) {
		
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if(findById.isPresent()) {
			
			
			return orderRepo.save(orderDetails);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailsEntity updateOrderStatus(Integer orderId, String status) {
		System.out.println(orderId);
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if(findById.isPresent()) {
			OrderDetailsEntity orderDetailsEntity = findById.get();
			orderDetailsEntity.setStatus(status);
			orderRepo.save(orderDetailsEntity);
			return orderDetailsEntity;
		}
			
		else {
			return null;
		}
		
		
	}

	@Override
	public OrderDetailsEntity cancelOrder(Integer orderId) {
//		OrderDetailsEntity findByCustomerId = orderRepo.findByCustomerId(customerId);
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if(findById.isPresent()) {
			OrderDetailsEntity orderDetailsEntity = findById.get();
			String status = orderDetailsEntity.getStatus();
			if(status.contains("pending")||status.contains("approved")) {
				orderDetailsEntity.setStatus("cancel");
				return orderDetailsEntity;
				
			}else {
				
			}
			return null;
		}else {
			
		}
		return null;
	}
		
	@Override
	public Double calculateTotalCost(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetailsEntity> showOrderByCustomer(Long customerId) {
			ArrayList<OrderDetailsEntity> orders=new ArrayList<>();
		 List<OrderDetailsEntity> findByCustomerId = orderRepo.findByCustomerId(customerId);
		if(findByCustomerId.isEmpty()) {
			return null;
	
		}else {
			findByCustomerId.forEach(c->orders.add(c));
			
			return orders;
		}
		
		
		
		
	}

}
