package com.jgdabc.controller;

import com.jgdabc.common.R_;
import com.jgdabc.entity.Orders;
import com.jgdabc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //用户下单
    @PostMapping("/submit")
    public R_<String> submit(@RequestBody Orders orders)
    {
        log.info("订单数据{}",orders);
        orderService.submit(orders);
        return  R_.success("下单成功");
    }
}
