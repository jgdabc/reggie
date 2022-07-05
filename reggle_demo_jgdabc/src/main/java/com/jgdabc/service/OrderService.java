package com.jgdabc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jgdabc.entity.Orders;
import org.springframework.core.annotation.Order;

public interface OrderService extends IService<Orders> {
    /*
    下单
     */
    public void submit(Orders orders);

}
