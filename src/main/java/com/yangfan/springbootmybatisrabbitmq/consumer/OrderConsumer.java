package com.yangfan.springbootmybatisrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @RabbitListener(queues = "orderQueue")
    public void handler(Object msg) {
        System.out.println("_________________________________________________________________________");
        System.out.println(msg);
    }
}
