package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void saveBatch(List<DishFlavor> flavors);

    @Delete("DELETE from dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long id);

    List<DishFlavor> findByDishId(Long id);
}
