package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetMealMapper {
    List<Long> findByCategoryId(Long id);

    List<SetmealVO> findAllByPage(SetmealPageQueryDTO setMealPageQueryDTO);

    @AutoFill(value = OperationType.INSERT)
    void saveSetMeal(Setmeal setmeal);

    SetmealVO findById(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void updateSetMeal(Setmeal setmeal);

    @Delete("DELETE FROM setmeal WHERE id = #{id}")
    void deleteSetMeal(Long id);

    List<Setmeal> findByCategoryIdToList(Setmeal setmeal);

    List<DishItemVO> findBySetmealId(Long id);

    Integer countByMap(Map map);
}
