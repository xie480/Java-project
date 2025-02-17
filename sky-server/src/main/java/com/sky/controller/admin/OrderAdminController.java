package com.sky.controller.admin;

import com.sky.dto.OrdersCancelDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersRejectionDTO;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderAdminService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/order")
@Slf4j
public class OrderAdminController {
    @Autowired
    private OrderAdminService orderAdminService;

    @GetMapping("/conditionSearch")
    public Result<PageResult> findByPage(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderAdminService.findByPage(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> statistics() {
        log.info("订单统计");
        return Result.success(orderAdminService.statistics());
    }

    @GetMapping("/details/{id}")
    public Result<OrderVO> findDetails(@PathVariable("id") Long id) {
        log.info("查询订单详情:{}", id);
        return Result.success(orderAdminService.findDetails(id));
    }

    @PutMapping("/confirm")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        log.info("接单:{}", ordersConfirmDTO);
        orderAdminService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    @PutMapping("/rejection")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        log.info("拒单:{}", ordersRejectionDTO);
        orderAdminService.rejection(ordersRejectionDTO);
        return Result.success();
    }

    @PutMapping("/cancel")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        log.info("取消订单:{}", ordersCancelDTO);
        orderAdminService.cancel(ordersCancelDTO);
        return Result.success();
    }

    @PutMapping("/delivery/{id}")
    public Result delivery(@PathVariable("id") Long id) {
        log.info("订单:{}开始配送", id);
        orderAdminService.delivery(id);
        return Result.success();
    }

    @PutMapping("/complete/{id}")
    public Result complete(@PathVariable("id") Long id) {
        log.info("订单:{}完成", id);
        orderAdminService.complete(id);
        return Result.success();
    }
}
