package com.yangfan.springbootmybatisrabbitmq.producer;

import com.sun.tools.corba.se.idl.constExpr.Or;
import com.yangfan.springbootmybatisrabbitmq.constant.Constants;
import com.yangfan.springbootmybatisrabbitmq.entity.Order;
import com.yangfan.springbootmybatisrabbitmq.mapper.BrokerMessageLogMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RabbitOrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            String messageId = correlationData.getId();
            if (ack) {

                //如果回调成功，则更改消息的状态
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                System.err.println("异常处理...");
            }
        }
    };

    public void sendOrder(Order order) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getId());
        rabbitTemplate.convertAndSend("order-exchange", "order.ABC", order, correlationData);
    }
}
