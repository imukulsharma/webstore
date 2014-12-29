package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Customer;

public interface CustomerRepository {
	List<Customer> getAllCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(String customerId);

	public Boolean isExistingCustomer(String customerId);
}
