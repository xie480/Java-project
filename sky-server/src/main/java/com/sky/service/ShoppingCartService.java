package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void saveShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> findAll();

    void deleteAll();

    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
