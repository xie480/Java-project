package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.DishQueryService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/dish")
@Slf4j
public class DishQueryController {

    @Autowired
    private DishQueryService dishQueryService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    @Cacheable(cacheNames = "dishCache", key = "#categoryId")
    public Result<List<DishVO>> findByCategoryId(Long categoryId) {
        log.info("根据分类id查询菜品：{}", categoryId);
        List<DishVO> dishVOList = dishQueryService.findByCategoryId(categoryId);
        return Result.success(dishVOList);
    }

}
