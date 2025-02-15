package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public Result saveShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车：{}", shoppingCartDTO);
        shoppingCartService.saveShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<ShoppingCart>> findAll(){
        log.info("查询购物车");
        return Result.success(shoppingCartService.findAll());
    }

    @DeleteMapping("/clean")
    public Result deleteAll(){
        log.info("清空购物车");
        shoppingCartService.deleteAll();
        return Result.success();
    }

    @PostMapping("/sub")
    public Result subShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("减少购物车：{}", shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }
}
