package com.yangfan.springbootmybatisrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "orderQueue")
public class OrderConsumer {

    @RabbitHandler
    public void handler(Object msg) {
        System.out.println(msg);
    }
}
