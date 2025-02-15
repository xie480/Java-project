package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/setmeal")
public class SetMealController {

    @Autowired
    private SetMealService setMealService;

    @RequestMapping("/page")
    public Result<PageResult> findAllByPage(SetmealPageQueryDTO setMealPageQueryDTO) {
        log.info("分页查询{}", setMealPageQueryDTO);
        return Result.success(setMealService.findAllByPage(setMealPageQueryDTO));
    }

    @PostMapping
    public Result saveSetMeal(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐{}", setmealDTO);
        setMealService.saveSetMeal(setmealDTO);
        return Result.success();
    }

    @PutMapping
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result updateSetMeal(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐{}", setmealDTO);
        setMealService.updateSetMeal(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根据id查询套餐{}", id);
        SetmealVO setmealVO = setMealService.findById(id);
        return Result.success(setmealVO);
    }

    @PostMapping("/status/{status}")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result updateStatus(@PathVariable Integer status, Long id) {
        log.info("修改套餐状态{}", status);
        setMealService.updateStatus(status, id);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteSetMeal(@RequestParam("ids") List<Long> ids) {
        log.info("删除套餐{}", ids);
        setMealService.deleteSetMeal(ids);
        return Result.success();
    }
}
