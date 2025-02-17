package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    List<DishVO> findAllByPage(DishPageQueryDTO dishPageQueryDTO);

    @AutoFill(value = OperationType.INSERT)
    void saveDish(Dish dish);

    Dish findById(Long i);

    List<Long> findByCategoryId(Long id);

    @Delete("DELETE from dish where id = #{id}")
    void deleteDish(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void updateDish(Dish dish);

    List<DishVO> findByCategoryIdToProject(Long categoryId);

    List<DishVO> findByCategoryIdToList(Dish dish);

    Integer countByMap(Map map);
}
