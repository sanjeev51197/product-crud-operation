package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.ProductDto;
import com.product.entity.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	//CREATE
	
	public ProductDto saveProduct(ProductDto productDto)
	{
		Product product=new Product();
	
		//This line copies all matching fields from the DTO to the entity automatically.
		BeanUtils.copyProperties(productDto, product);
	Product savedProduct=productRepository.save(product);
		
	   ProductDto dto=new ProductDto();
	//   converting entity → DTO again so that the response doesn’t expose unnecessary DB details.
	   BeanUtils.copyProperties(savedProduct, dto);
		
		return dto;
	}
	
	 //getById
	
	 public Product getProductById(long id) 
	 {
	Product product=	productRepository.findById(id).orElse(null);
		 
		return product;
			
		}
	 
	//Get All 
	 
	 public List<Product> getAllProduct()
	 {
	List<Product> productList=	 productRepository.findAll();
	return productList;
	
	 }
	 
	 //Update
	 
	 public void updateproduct(ProductDto productDto)
	 {
		Product product=  productRepository.findById(productDto.getPid()).orElse(null);
		if(product==null)
		{
			throw new RuntimeException("Not found");
		}
		
		else {
			
			product.setName(productDto.getName());
			product.setBrand(productDto.getBrand());
			product.setPrice(productDto.getPrice());
			productRepository.save(product);
		}
		
	 }
	 
	 //Delete
	      
	 public void deleteProductById(long id)
	 {
	   	   productRepository.deleteById(id);
	 }
	 
	 
	 
	 }
     
	

