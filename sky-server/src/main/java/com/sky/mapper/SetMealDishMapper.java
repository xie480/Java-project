package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    List<Long> findByDishIds(List<Long> ids);

    void saveSetMealDish(List<SetmealDish> setMealDishes);

    List<SetmealDish> findByDishId(Long id);

    @Delete("DELETE FROM setmeal_dish WHERE setmeal_id = #{id}")
    void deleteByDishId(Long id);
}
