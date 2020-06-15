package com.yangfan.springbootmybatisrabbitmq;

import com.yangfan.springbootmybatisrabbitmq.entity.Order;
import com.yangfan.springbootmybatisrabbitmq.producer.RabbitOrderSender;
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

    @Test
    public void testSender2() throws Exception {
        Order order = new Order();
        order.setId("2018080400000001");
        order.setName("测试订单");
        order.setMessage_id(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
        rabbitOrderSender.sendOrder(order);
    }

}
