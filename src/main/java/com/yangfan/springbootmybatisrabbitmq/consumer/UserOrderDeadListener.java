package com.yangfan.springbootmybatisrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.yangfan.springbootmybatisrabbitmq.entity.UserOrder;
import com.yangfan.springbootmybatisrabbitmq.mapper.UserOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserOrderDeadListener {
    private static final Logger log = LoggerFactory.getLogger(UserOrderDeadListener.class);
    @Autowired
    private UserOrderMapper userOrderMapper;

    @RabbitListener(queues = "deadOrderQueue")
    public void consumeMessage(Message message, Channel channel) {
        String Id = new String(message.getBody());
        int id = Integer.parseInt(Id);
        try {
            log.info("死信队列-用户下单超时未支付监听消息： {} ", id);

            UserOrder entity = userOrderMapper.selectByPkAndStatus(id, 1);
            if (entity != null) {
                entity.setStatus(3);
                entity.setUpdateTime(new Date());
                userOrderMapper.updateByPrimaryKeySelective(entity);
            } else {
                //TODO：已支付-可能需要异步 减库存-异步发送其他日志消息
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
