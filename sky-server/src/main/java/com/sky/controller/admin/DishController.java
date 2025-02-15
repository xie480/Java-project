package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

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

    @DeleteMapping
    public Result deleteDish(@RequestParam("ids") List<Long> ids) {
        log.info("批量删除菜品");
        dishService.deleteDish(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable("id") Long id) {
        log.info("根据id查询菜品");
        DishVO dishVO = dishService.findById(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @CacheEvict(cacheNames = "dishCache",allEntries = true)
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品");
        dishService.updateDish(dishDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @CacheEvict(cacheNames = "dishCache",allEntries = true)
    public Result updateStatus(@PathVariable Integer status, Long id) {
        log.info("修改菜品状态：{}", status);
        log.info("修改菜品id：{}", id);
        dishService.updateStatus(status, id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<DishVO>> findByCategoryId(Long categoryId) {
        log.info("根据分类id查询菜品：{}", categoryId);
        List<DishVO> dishVOList = dishService.findByCategoryId(categoryId);
        return Result.success(dishVOList);
    }

}
