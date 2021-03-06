package com.packt.webstore.domain.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	private List<Product> listOfProducts = new ArrayList<Product>();

	public InMemoryProductRepository() {
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
		iphone.setCategory("Smart Phone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000L);

		Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000L);

		Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet_Nexus
				.setDescription("Google Nexus 7 is the lightest 7 inch tablet With a quad-core Qualcomm Snapdragon™ S4 Pro processor");
		tablet_Nexus.setCategory("Tablet");
		tablet_Nexus.setManufacturer("Google");
		tablet_Nexus.setUnitsInStock(1000L);

		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_Nexus);
	}

	public List<Product> getAllProducts() {
		return listOfProducts;
	}

	public Product getProductById(String productID) {
		Product productById = null;

		for (Product product : listOfProducts) {
			if (product != null && product.getProductId() != null && product.getProductId().equals(productID)) {
				productById = product;
				break;
			}
		}

		if (null == productById) {
			throw new ProductNotFoundException(productID);
		}
		return productById;
	}

	public List<Product> getProductsByCategory(String category) {

		List<Product> productsByCategory = new ArrayList<Product>();
		for (Product product : listOfProducts) {
			if (category.equalsIgnoreCase(product.getCategory())) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();

		Set<String> criterias = filterParams.keySet();

		if (criterias.contains("brand")) {
			for (String brandName : filterParams.get("brand")) {
				for (Product product : listOfProducts) {
					if (brandName.equalsIgnoreCase(product.getManufacturer())) {
						productsByBrand.add(product);
					}
				}
			}
		}

		if (criterias.contains("category")) {
			for (String category : filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}

		productsByCategory.retainAll(productsByBrand);

		return productsByCategory;
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		List<Product> productsByManufacturer = new ArrayList<Product>();
		for (Product product : listOfProducts) {
			if (manufacturer.equalsIgnoreCase(product.getManufacturer())) {
				productsByManufacturer.add(product);
			}
		}
		return productsByManufacturer;
	}

	public List<Product> getProductsByPriceFilter(Map<String, List<String>> priceFilter) {

		BigDecimal min = new BigDecimal(0.0);
		BigDecimal max = new BigDecimal(0.0);
		if (priceFilter.containsKey("low")) {
			min = new BigDecimal(priceFilter.get("low").get(0));
		}

		if (priceFilter.containsKey("high")) {
			max = new BigDecimal(priceFilter.get("high").get(0));
		}

		List<Product> productsByPrice = new ArrayList<Product>();
		for (Product product : getAllProducts()) {
			BigDecimal price = product.getUnitPrice();

			if (price.compareTo(min) > -1 && price.compareTo(max) != 1) {
				productsByPrice.add(product);
			}
		}

		return productsByPrice;
	}

	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
}
