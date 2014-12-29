package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productReposiory;

	public List<Product> getAllProducts() {
		return productReposiory.getAllProducts();
	}

	public List<Product> getProductsByCategory(String category) {
		return productReposiory.getProductsByCategory(category);
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productReposiory.getProductsByFilter(filterParams);
	}

	public Product getProductById(String productId) {
		return productReposiory.getProductById(productId);
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		return productReposiory.getProductsByManufacturer(manufacturer);
	}

	public List<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilter) {
		return productReposiory.getProductsByPriceFilter(priceFilter);
	}

	public void addNewProduct(Product product) {
		productReposiory.addProduct(product);
	}
}