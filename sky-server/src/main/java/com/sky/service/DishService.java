package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

public interface DishService {
    PageResult findAllByPage(DishPageQueryDTO dishPageQueryDTO);

    void saveDish(DishDTO dishDTO);
}
