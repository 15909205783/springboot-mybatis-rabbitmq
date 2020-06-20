package com.yangfan.springbootmybatisrabbitmq.service;

import com.rabbitmq.client.impl.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonMqService {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送抢单信息入队列
     *
     * @param mobile
     */
    public void sendRobbingMsg(String mobile) {
        try {
            rabbitTemplate.setExchange("userOrderExchange");
            rabbitTemplate.setRoutingKey("order.A");

            Message message = MessageBuilder.withBody(mobile.getBytes("UTF-8")).setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.send(message);
        } catch (Exception e) {
            log.error("发送抢单信息入队列 发生异常： mobile={} ", mobile);
        }
    }

    public void sendRobbingMsgV2(String mobile) {
        try {
            rabbitTemplate.setExchange("userOrderExchange");
            rabbitTemplate.setRoutingKey("order.A");
            Message message = MessageBuilder.withBody(mobile.getBytes("UTF-8")).setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.send(message);
        } catch (Exception e) {
            log.error("发送抢单信息入队列V2 发生异常： mobile={} ", mobile);
        }
    }
}
