package com.anarghya.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_Module")
public class CustomerModel {
	
	@Id
	private Long customerId;
	private String customerName;
	private Long moblieNo;
	private String emailId;
	private String password;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getMoblieNo() {
		return moblieNo;
	}
	public void setMoblieNo(Long moblieNo) {
		this.moblieNo = moblieNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

