package com.sky.task;

import com.sky.constant.StatusConstant;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void outOfTimeOrder(){
        log.info("取消超时订单");
        LocalDateTime outTime = LocalDateTime.now().plusMinutes(-15);
        List<Orders> outOfTimeOrders = orderMapper.findOutOfTimeOrders(Orders.PENDING_PAYMENT,outTime);
        if(outOfTimeOrders != null && !outOfTimeOrders.isEmpty()){
            outOfTimeOrders.forEach(orders -> {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            });
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void outOfTimeConfirm(){
        log.info("自动确认收货");
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> ordersList = orderMapper.findOutOfTimeOrders(Orders.DELIVERY_IN_PROGRESS, time);
        if(ordersList != null && !ordersList.isEmpty()){
            ordersList.forEach(orders -> {
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            });
        }
    }
}
