package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    @Override
    public PageResult findAllByPage(SetmealPageQueryDTO setMealPageQueryDTO) {
        PageHelper.startPage(setMealPageQueryDTO.getPage(), setMealPageQueryDTO.getPageSize());
        List<SetmealVO> list = setMealMapper.findAllByPage(setMealPageQueryDTO);
        Page<SetmealVO> p = (Page<SetmealVO>) list;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Transactional
    @Override
    public void saveSetMeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setMealMapper.saveSetMeal(setmeal);

        Long dishId = setmeal.getId();
        List<SetmealDish> setMealDishes = setmealDTO.getSetmealDishes();
        if (setMealDishes != null && !setMealDishes.isEmpty()) {
            setMealDishes.forEach(setMealDish -> {
                setMealDish.setSetmealId(dishId);
            });
            setMealDishMapper.saveSetMealDish(setMealDishes);
        }
    }

    @Transactional
    @Override
    public void updateSetMeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setMealMapper.updateSetMeal(setmeal);

        setMealDishMapper.deleteByDishId(setmealDTO.getId());
        List<SetmealDish> setMealDishes = setmealDTO.getSetmealDishes();
        if (setMealDishes != null && !setMealDishes.isEmpty()) {
            setMealDishes.forEach(setMealDish -> {
                setMealDish.setSetmealId(setmealDTO.getId());
            });
            setMealDishMapper.saveSetMealDish(setMealDishes);
        }
    }

    @Override
    public SetmealVO findById(Long id) {
        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setMealMapper.findById(id), setmealVO);
        setmealVO.setSetmealDishes(setMealDishMapper.findByDishId(id));
        return setmealVO;
    }

    @Override
    public void updateStatus(Integer status, Long id) {
        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .build();
        setMealMapper.updateSetMeal(setmeal);
    }

    @Transactional
    @Override
    public void deleteSetMeal(List<Long> ids) {
        for (Long id : ids) {
            SetmealVO setmealVO = setMealMapper.findById(id);
            if (Objects.equals(setmealVO.getStatus(), StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }
        for (Long id : ids) {
            setMealMapper.deleteSetMeal(id);
            setMealDishMapper.deleteByDishId(id);
        }
    }
}
