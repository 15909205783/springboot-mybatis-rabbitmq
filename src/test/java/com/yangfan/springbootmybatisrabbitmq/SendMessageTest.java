package com.yangfan.springbootmybatisrabbitmq;

import com.yangfan.springbootmybatisrabbitmq.entity.Order;
import com.yangfan.springbootmybatisrabbitmq.order.service.OrderService;
import com.yangfan.springbootmybatisrabbitmq.consumer.RabbitOrderSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMessageTest {
    @Autowired
    private RabbitOrderSender rabbitOrderSender;
    @Autowired
    private OrderService orderService;
    @Test
    public void testSender2() throws Exception {
        Order order = new Order();
        order.setId("2018080400000007");
        order.setName("测试订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
       // orderService.createOrder(order);
        rabbitOrderSender.sendOrder(order);
    }


    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setId("2018080400000001");
        order.setName("测试创建订单");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        orderService.createOrder(order);
    }


}
