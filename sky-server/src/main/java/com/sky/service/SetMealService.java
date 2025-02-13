package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {
    PageResult findAllByPage(SetmealPageQueryDTO setMealPageQueryDTO);

    void saveSetMeal(SetmealDTO setmealDTO);

    void updateSetMeal(SetmealDTO setmealDTO);

    SetmealVO findById(Long id);

    void updateStatus(Integer status, Long id);

    void deleteSetMeal(List<Long> ids);
}
