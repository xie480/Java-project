package com.sky.service;

import com.sky.entity.Category;

import java.util.List;

public interface CategoryQueryService {
    List<Category> findByType(Integer type);
}
