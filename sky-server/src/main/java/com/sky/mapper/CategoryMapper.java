package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> findAllByPage(CategoryPageQueryDTO categoryPageQueryDTO);

    @AutoFill(value = OperationType.INSERT)
    void saveCategory(Category category);

    @AutoFill(value = OperationType.UPDATE)
    void updateCategory(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteCategory(Long id);

    List<Category> findByType(Integer type);
}
