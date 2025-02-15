package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    public void saveShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());

        List<ShoppingCart> shoppingCartList = shoppingCartMapper.findByUserIdAndType(shoppingCart);

        if(shoppingCartList != null && shoppingCartList.size() == 1){
            shoppingCart = shoppingCartList.get(0);
            shoppingCart.setNumber(shoppingCart.getNumber() + 1);
            shoppingCartMapper.updateShoppingCart(shoppingCart);
        }else{
            Long dishId = shoppingCart.getDishId();
            if(dishId != null){
                Dish dish = dishMapper.findById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            }else{
                SetmealVO setmealVO = setMealMapper.findById(shoppingCart.getSetmealId());
                shoppingCart.setName(setmealVO.getName());
                shoppingCart.setImage(setmealVO.getImage());
                shoppingCart.setAmount(setmealVO.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.saveShoppingCart(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCart> findAll() {
        return shoppingCartMapper.findByUserIdAndType(ShoppingCart.builder().userId(BaseContext.getCurrentId()).build());
    }

    @Override
    public void deleteAll() {
        shoppingCartMapper.deleteAllByUserId(BaseContext.getCurrentId());
    }

    @Override
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.findByUserIdAndType(shoppingCart);
        if(shoppingCartList != null && shoppingCartList.size() == 1){
            shoppingCart = shoppingCartList.get(0);
            if(shoppingCart.getNumber() > 1){
                shoppingCart.setNumber(shoppingCart.getNumber() - 1);
                shoppingCartMapper.updateShoppingCart(shoppingCart);
            }else{
                shoppingCartMapper.deleteByDishIdOrSetmealId(shoppingCart);
            }
        }

    }
}
