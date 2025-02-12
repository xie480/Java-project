package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    List<Dish> findAllByPage(DishPageQueryDTO dishPageQueryDTO);

    @AutoFill(value = OperationType.INSERT)
    void saveDish(Dish dish);
}
