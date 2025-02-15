package com.sky.controller.user;

import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetMealQueryService;
import com.sky.vo.DishItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/setmeal")
@Slf4j
public class SetMealQueryController {

    @Autowired
    private SetMealQueryService setMealQueryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "setmealCache", key = "#categoryId")
    public Result<List<Setmeal>> findByCategoryId(Long categoryId) {
        log.info("查询分类id为{}的套餐", categoryId);
        List<Setmeal> setmeals = setMealQueryService.findByCategoryId(categoryId);
        return Result.success(setmeals);
    }

    @GetMapping("/dish/{id}")
    @Cacheable(cacheNames = "setmealCache", key = "#id")
    public Result<List<DishItemVO>> findBySetmealId(@PathVariable Long id) {
        log.info("查询套餐id为{}的菜品", id);
        List<DishItemVO> dishItemVOS = setMealQueryService.findBySetmealId(id);
        return Result.success(dishItemVOS);
    }
}
