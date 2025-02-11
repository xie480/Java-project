package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<PageResult> findAllByPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResult pageResult = categoryService.findAllByPage(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result saveCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Long id) {
        categoryService.updateStatus(status, id);
        return Result.success();
    }

    @PutMapping
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> findByType(Integer type) {
        List<Category> list = categoryService.findByType(type);
        return Result.success(list);
    }
}
