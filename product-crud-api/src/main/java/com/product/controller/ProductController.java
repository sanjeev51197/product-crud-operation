package com.product.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductDto;
import com.product.entity.Product;
import com.product.response.ApiResponse;
import com.product.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@PostMapping("/save")
	public ResponseEntity<ApiResponse<ProductDto>> saveProduct(@RequestBody ProductDto productDto)
	{
	ProductDto savedProduct= productService.saveProduct(productDto);
	ApiResponse<ProductDto> response=new ApiResponse<>();
	response.setMessage("Product saved");
	response.setStatus(200);
	response.setData(savedProduct);
	return new ResponseEntity<ApiResponse<ProductDto>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable long id)
	{
	Product product= productService.getProductById(id);
	 ApiResponse<Product> response = new ApiResponse<>();

	    if (product == null) {
	        response.setMessage("Product not found");
	        response.setStatus(404);
	        response.setData(null);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    response.setMessage("Product found successfully");
	    response.setStatus(200);
	    response.setData(product);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<Product>>> getAllProducts()
	{
	 List<Product>  products=productService.getAllProduct();
	 ApiResponse<List<Product>> response=new ApiResponse<>();
	    response.setMessage("Product found successfully");
	    response.setStatus(200);
	    response.setData(products);
      
	   return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatus()));
	   	
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<String>> updateProduct(@RequestBody ProductDto dto)
	{
	
	 ApiResponse<String> response=new ApiResponse<>();
	 try {
	        productService.updateproduct(dto);
	        response.setMessage("Product updated successfully");
	        response.setStatus(200);
	        response.setData("Product details updated");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        response.setMessage(e.getMessage());
	        response.setStatus(404);
	        response.setData(null);
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}
	 
	
	
	}

