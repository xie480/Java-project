package com.sky.service;

import com.sky.vo.DishVO;

import java.util.List;

public interface DishQueryService {
    List<DishVO> findByCategoryId(Long categoryId);
}
