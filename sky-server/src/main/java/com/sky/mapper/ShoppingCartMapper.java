package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    List<ShoppingCart> findByUserIdAndType(ShoppingCart shoppingCart);

    void updateShoppingCart(ShoppingCart shoppingCart);

    void saveShoppingCart(ShoppingCart shoppingCart);

    void deleteAllByUserId(Long currentId);

    void deleteByDishIdOrSetmealId(ShoppingCart shoppingCart);

    void saveShoppingCartWithList(List<ShoppingCart> shoppingCartList);
}
