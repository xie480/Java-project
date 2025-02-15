package com.sky.service.impl;

import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.service.CategoryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByType(Integer type) {
        return categoryMapper.findByType(type);
    }
}
