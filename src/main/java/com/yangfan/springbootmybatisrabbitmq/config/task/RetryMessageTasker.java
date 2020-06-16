package com.yangfan.springbootmybatisrabbitmq.config.task;

import com.alibaba.fastjson.JSONObject;
import com.yangfan.springbootmybatisrabbitmq.constant.Constants;
import com.yangfan.springbootmybatisrabbitmq.entity.BrokerMessageLog;
import com.yangfan.springbootmybatisrabbitmq.entity.Order;
import com.yangfan.springbootmybatisrabbitmq.mapper.BrokerMessageLogMapper;
import com.yangfan.springbootmybatisrabbitmq.producer.RabbitOrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class RetryMessageTasker {
    @Autowired
    private RabbitOrderSender rabbitOrderSender;
    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend() {
        List<BrokerMessageLog> brokerMessageLogs = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        brokerMessageLogs.forEach(messagelog -> {
            if (messagelog.getTryCount() >= 3) {
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messagelog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
            } else {
                brokerMessageLogMapper.update4ReSend(messagelog.getMessageId(), new Date());
                Order order = JSONObject.parseObject(messagelog.getMessage(), Order.class);
                try {
                    rabbitOrderSender.sendOrder(order);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("-----------异常处理-----------");
                }
            }
        });
    }
}
