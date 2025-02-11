package com.sky.mapper;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> findAllByPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void saveCategory(Category category);

    void updateCategory(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteCategory(Long id);

    List<Category> findByType(Integer type);
}
