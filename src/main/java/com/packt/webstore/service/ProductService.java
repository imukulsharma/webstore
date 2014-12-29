package com.packt.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.packt.webstore.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();

	Product getProductById(String productId);

	List<Product> getProductsByCategory(String category);

	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	List<Product> getProductsByManufacturer(String manufacturer);

	List<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilter);

	void addNewProduct(Product product);
}