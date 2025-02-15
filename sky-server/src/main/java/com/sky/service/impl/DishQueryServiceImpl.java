package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishQueryService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishQueryServiceImpl implements DishQueryService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Transactional
    @Override
    public List<DishVO> findByCategoryId(Long categoryId) {
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);
        List<DishVO> dishVOList = dishMapper.findByCategoryIdToList(dish);
        dishVOList.forEach(dishVO -> {
            dishVO.setFlavors(dishFlavorMapper.findByDishId(dishVO.getId()));
        });
        return dishVOList;
    }
}
