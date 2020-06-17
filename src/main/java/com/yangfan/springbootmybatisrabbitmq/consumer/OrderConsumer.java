package com.yangfan.springbootmybatisrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderConsumer {

    //    @RabbitListener(queues = "orderQueue")
//    public void handler(Object msg) {
//        System.out.println("_________________________________________________________________________");
//        System.out.println(msg);
//    }
    @RabbitListener(queues = "orderQueue")
    @RabbitHandler
    public void handler(Channel channel, Message message) throws IOException {
        System.out.println("_________________________________________________________________________");
        System.out.print(message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
