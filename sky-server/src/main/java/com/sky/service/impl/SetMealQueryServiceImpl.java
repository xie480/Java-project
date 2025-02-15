package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetMealMapper;
import com.sky.service.SetMealQueryService;
import com.sky.vo.DishItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetMealQueryServiceImpl implements SetMealQueryService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    public List<Setmeal> findByCategoryId(Long categoryId) {
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);
        return setMealMapper.findByCategoryIdToList(setmeal);
    }

    @Override
    public List<DishItemVO> findBySetmealId(Long id) {
        return setMealMapper.findBySetmealId(id);
    }
}
