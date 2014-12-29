package com.packt.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.UnitsInStockValidator;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UnitsInStockValidator unitsInStockValidator;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping(value = "/all")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping(value = "/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCatgeory) {
		List<Product> productsByCategory = productService.getProductsByCategory(productCatgeory);

		if (productsByCategory == null || productsByCategory.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", productCatgeory);
		return "products";
	}

	@RequestMapping(value = "/filter/{ByCriteria}")
	public String getProductsByFilter(Model model,
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	@RequestMapping(value = "/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	@RequestMapping(value = "/{category}/{price}")
	public String filterProducts(@PathVariable("category") String category,
			@MatrixVariable(pathVar = "price") Map<String, List<String>> filterPrice,
			@RequestParam("manufacturer") String manufacturer, Model model) {

		List<Product> productsByFilter = productService.getProductsByCategory(category);
		productsByFilter.retainAll(productService.getProductsByManufacturer(manufacturer));
		productsByFilter.retainAll(productService.getProductsByPriceFilter(filterPrice));

		model.addAttribute("products", productsByFilter);
		return "products";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "addProduct";
		}

		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields : "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		MultipartFile productImage = newProduct.getProductImage();

		if (productImage != null && !productImage.isEmpty()) {
			try {
				String rootDirectory = request.getSession().getServletContext().getRealPath("/");
				productImage.transferTo(new File(rootDirectory + "resources/images/" + newProduct.getProductId()
						+ ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Error saving the product image.", e);
			}
		}

		productService.addNewProduct(newProduct);
		return "redirect:/products";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setDisallowedFields("unitsInOrder", "discontinued");
		binder.setValidator(unitsInStockValidator);
	}

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException exception) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("invalidProductId", exception.getProductId());
		mv.addObject("exception", exception);
		mv.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		mv.setViewName("productNotFound");
		return mv;
	}

	@RequestMapping(value = "/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}
}
