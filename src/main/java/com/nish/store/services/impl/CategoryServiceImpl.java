package com.nish.store.services.impl;

import com.nish.store.dtos.CategoryDto;
import com.nish.store.dtos.PageableResponse;
import com.nish.store.entities.Category;
import com.nish.store.exceptions.ResourceNotFoundException;
import com.nish.store.helpers.Helper;
import com.nish.store.repositories.CategoryRepository;
import com.nish.store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        // Create category id randomly
        String catId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(catId);
        Category category = mapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);
        return mapper.map(saveCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        // get Category by id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found !!"));

        //update category details
        category.builder()
                .title(categoryDto.getTitle())
                .description(categoryDto.getDescription())
                .coverImage(categoryDto.getCoverImage()).build();
        Category updateCategory = categoryRepository.save(category);
        return mapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        // get Category by id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found !!"));
        categoryRepository.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy)).descending() : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> vPageableResponse = Helper.getVPageableResponse(page, CategoryDto.class);
        return vPageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found !!"));
        return mapper.map(category, CategoryDto.class);
    }
}
