package com.packt.webstore.service;

import java.util.List;

import com.packt.webstore.domain.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(String customerId);

	public Boolean isExistingCustomer(String customerId);
}
