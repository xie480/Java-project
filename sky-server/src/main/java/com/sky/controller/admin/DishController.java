package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public Result<PageResult> findAllByPage(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品");
        PageResult pageResult = dishService.findAllByPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result saveDish(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品");
        dishService.saveDish(dishDTO);
        return Result.success();
    }
}
