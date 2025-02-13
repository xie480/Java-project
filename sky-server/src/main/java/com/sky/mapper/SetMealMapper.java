package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealMapper {
    List<Long> findByCategoryId(Long id);
}
