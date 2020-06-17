package com.yangfan.springbootmybatisrabbitmq.config.task;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitmqConfig implements RabbitTemplate.ReturnCallback {
    @Autowired
//    private AmqpTemplate rabbitTemplate;
    private RabbitTemplate rabbitTemplate;


    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("-------------------------1-----------------------------");
        System.out.println("sender return success" + message.toString() + "===" + i + "===" + s1 + "===" + s2);
        System.out.println("-------------------------2-----------------------------");
    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//        System.out.println("sender success");
//    }
}
