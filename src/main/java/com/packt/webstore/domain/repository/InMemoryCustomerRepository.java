package com.packt.webstore.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	private List<Customer> listOfCustomers = new ArrayList<Customer>();

	public InMemoryCustomerRepository() {
		// Customer custA = new Customer("C0001", "Customer A");
		// custA.setBillingAddress("Mumbai");
		// custA.setNoOfOrdersMade(105L);
		// listOfCustomers.add(custA);
		//
		// Customer custB = new Customer("C0002", "Customer B");
		// custB.setAddress("Madras");
		// custB.setNoOfOrdersMade(1000L);
		// listOfCustomers.add(custB);
		//
		// Customer custC = new Customer("C0003", "Customer C");
		// custC.setAddress("Delhi");
		// custC.setNoOfOrdersMade(0L);
		// listOfCustomers.add(custC);
	}

	public List<Customer> getAllCustomers() {
		return listOfCustomers;
	}

	public void saveCustomer(Customer customer) {
		listOfCustomers.add(customer);
	}

	public Customer getCustomer(String customerId) {
		for (Customer customer : listOfCustomers) {
			if (customer.getCustomerId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	public Boolean isExistingCustomer(String customerId) {
		for (Customer customer : listOfCustomers) {
			if (customer.getCustomerId().equals(customerId)) {
				return true;
			}
		}
		return false;
	}

}
