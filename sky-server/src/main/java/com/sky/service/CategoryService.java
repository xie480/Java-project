package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    PageResult findAllByPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void saveCategory(CategoryDTO categoryDTO);

    void updateStatus(Integer status, Long id);

    void updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);

    List<Category> findByType(Integer type);
}
