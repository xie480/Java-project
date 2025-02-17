package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.exception.OrderBusinessException;
import com.sky.mapper.OrderDetailMapper;
import com.sky.mapper.OrderMapper;
import com.sky.result.PageResult;
import com.sky.service.OrderAdminService;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderAdminServiceImpl implements OrderAdminService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public PageResult findByPage(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());
        List<Orders> list = orderMapper.findByPage(ordersPageQueryDTO);
        Page<Orders> p = (Page<Orders>) list;
        List<OrderVO> result = list.stream().map(x -> {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(x, orderVO);
            List<OrderDetail> orderDetailList = orderDetailMapper.getByOrderId(x.getId());
            List<String> orderDishesList = orderDetailList.stream().map(y -> y.getName() + "*" + y.getNumber() + ";").collect(Collectors.toList());
            String orderDishes = String.join("", orderDishesList);
            orderVO.setOrderDishes(orderDishes);
            return orderVO;
        }).collect(Collectors.toList());
        return new PageResult(p.getTotal(), result);
    }

    @Override
    public OrderStatisticsVO statistics() {
        Integer toBeConfirmed = orderMapper.statistics(Orders.TO_BE_CONFIRMED);
        Integer confirmed = orderMapper.statistics(Orders.CONFIRMED);
        Integer deliveryInProgress = orderMapper.statistics(Orders.DELIVERY_IN_PROGRESS);

        return OrderStatisticsVO.builder()
                .toBeConfirmed(toBeConfirmed)
                .confirmed(confirmed)
                .deliveryInProgress(deliveryInProgress)
                .build();
    }

    @Override
    public OrderVO findDetails(Long id) {
        OrderVO orderVO = new OrderVO();
        Orders orders = orderMapper.findById(id);
        BeanUtils.copyProperties(orders, orderVO);
        List<OrderDetail> orderDetails = orderDetailMapper.getByOrderId(orders.getId());
        orderVO.setOrderDetailList(orderDetails);
        return orderVO;
    }

    @Override
    public void confirm(OrdersConfirmDTO ordersConfirmDTO) {
        Orders orders = Orders.builder()
                .id(ordersConfirmDTO.getId())
                .status(Orders.CONFIRMED)
                .build();
        orderMapper.update(orders);
    }

    @Override
    public void rejection(OrdersRejectionDTO ordersRejectionDTO) {
        Orders orders = orderMapper.findById(ordersRejectionDTO.getId());
        if(!Objects.equals(orders.getStatus(), Orders.TO_BE_CONFIRMED)){
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR);
        }
        Integer payStatus = orders.getPayStatus();
        if(Objects.equals(payStatus, Orders.PAID)){
//            String refund = weChatPayUtil.refund(
//                    ordersDB.getNumber(),
//                    ordersDB.getNumber(),
//                    new BigDecimal(0.01),
//                    new BigDecimal(0.01));
        }
        orders.setStatus(Orders.CANCELLED);
        orders.setRejectionReason(ordersRejectionDTO.getRejectionReason());
        orders.setCancelTime(LocalDateTime.now());
        orderMapper.update(orders);
    }

    @Override
    public void cancel(OrdersCancelDTO ordersCancelDTO) {
        Orders orders = orderMapper.findById(ordersCancelDTO.getId());
        Integer payStatus = orders.getPayStatus();
        if(Objects.equals(payStatus, Orders.PAID)){
//            String refund = weChatPayUtil.refund(
//                    ordersDB.getNumber(),
//                    ordersDB.getNumber(),
//                    new BigDecimal(0.01),
//                    new BigDecimal(0.01));
        }
        orders.setStatus(Orders.CANCELLED);
        orders.setRejectionReason(ordersCancelDTO.getCancelReason());
        orders.setCancelTime(LocalDateTime.now());
        orderMapper.update(orders);

    }

    @Override
    public void delivery(Long id) {
        Orders orders = orderMapper.findById(id);
        if(orders == null || !Objects.equals(orders.getStatus(), Orders.CONFIRMED)){
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR_DELIVERY);
        }
        orders.setStatus(Orders.DELIVERY_IN_PROGRESS);
        orderMapper.update(orders);
    }

    @Override
    public void complete(Long id) {
        Orders orders = orderMapper.findById(id);
        if(orders == null || !Objects.equals(orders.getStatus(), Orders.DELIVERY_IN_PROGRESS)){
            throw new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR_COMPLETED);
        }
        orders.setStatus(Orders.COMPLETED);
        orders.setDeliveryTime(LocalDateTime.now());
        orderMapper.update(orders);
    }
}
