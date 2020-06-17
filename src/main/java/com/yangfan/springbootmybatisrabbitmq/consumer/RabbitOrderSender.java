package com.yangfan.springbootmybatisrabbitmq.consumer;

import com.rabbitmq.client.MessageProperties;
import com.yangfan.springbootmybatisrabbitmq.constant.Constants;
import com.yangfan.springbootmybatisrabbitmq.entity.Order;
import com.yangfan.springbootmybatisrabbitmq.mapper.BrokerMessageLogMapper;
import org.springframework.amqp.core.Message;
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
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {

        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("+============================================");
            System.out.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
            System.out.println("+============================================");
        }
    };

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
        MessageProperties mp = new MessageProperties();
        //在生产环境中这里不用Message，而是使用 fastJson 等工具将对象转换为 json 格式发送
       // Message msg = new Message(message.toString().getBytes(),mp);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("orderExchange", "order.A", order, correlationData);
    }
}
