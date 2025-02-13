package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    PageResult findAllByPage(DishPageQueryDTO dishPageQueryDTO);

    void saveDish(DishDTO dishDTO);

    void deleteDish(List<Long> ids);

    DishVO findById(Long id);

    void updateDish(DishDTO dishDTO);

    void updateStatus(Integer status, Long id);

    List<DishVO> findByCategoryId(Long categoryId);
}
