package com.nish.store.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nish.store.dtos.ProductDto;
import com.nish.store.entities.Product;
import com.nish.store.exceptions.ResourceAlreadyExistsException;
import com.nish.store.exceptions.ResourceNotFoundException;
import com.nish.store.repositories.ProductRepository;
import com.nish.store.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository  productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        List<Product> byProductName = productRepository.findByProductName(productDto.getProductName().trim());
        if (byProductName != null && !byProductName.isEmpty()) {
            System.err.println(byProductName);
            throw new ResourceAlreadyExistsException("Product already exist with given name");
        }

        Product product = modelMapper.map(productDto, Product.class);
        product.setProductId(UUID.randomUUID().toString());
        product.setCreatedBy(new Date());
        Product createdProduct = productRepository.save(product);
        return modelMapper.map(createdProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {
        return null;
    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public ProductDto getProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with given id"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @Override
    public List<ProductDto> findByName(String productName) {
        return null;
    }
}
