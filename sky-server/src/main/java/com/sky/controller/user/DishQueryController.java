package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.DishQueryService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result<List<DishVO>> findByCategoryId(Long categoryId) {
        log.info("根据分类id查询菜品：{}", categoryId);
        String key = "dish_" + categoryId;
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if(list != null && !list.isEmpty()) {
            log.info("缓存中存在菜品数据，直接返回");
            return Result.success(list);
        }
        log.info("缓存中不存在菜品数据，查询数据库");
        List<DishVO> dishVOList = dishQueryService.findByCategoryId(categoryId);
        log.info("查询数据库后，将菜品数据保存到缓存中");
        redisTemplate.opsForValue().set(key, dishVOList);
        return Result.success(dishVOList);
    }

}
