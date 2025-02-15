package com.sky.service;

import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;

import java.util.List;

public interface SetMealQueryService {
    List<Setmeal> findByCategoryId(Long categoryId);

    List<DishItemVO> findBySetmealId(Long id);
}
