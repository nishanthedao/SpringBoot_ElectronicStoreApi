package com.nish.store.services;

import com.nish.store.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    // create
    public ProductDto createProduct(ProductDto productDto);

    // update
    public ProductDto updateProduct(ProductDto productDto, String productId);

    // delete
    public void deleteProduct(String productId);

    // get single
    public ProductDto getProduct(String productId);

    // get all
    public List<ProductDto> getAllProducts();

    // find by name
    public List<ProductDto> findByName(String productName);
}
