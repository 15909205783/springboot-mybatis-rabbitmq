package com.yangfan.springbootmybatisrabbitmq.config.queues;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserOrderDeadQueue {
    @Bean
    public Queue userOrderDeadQueues() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "deadOrderExchange");
        args.put("x-dead-letter-routing-key", "deadOrder.A");
        args.put("x-dead-letter-ttl", 10000);
        return new Queue("deadOrderQueue", true, false, false, args);
    }

    //绑定死信队列，面向生产端
    @Bean
    public TopicExchange userOrderDeadExchange() {
        return new TopicExchange("deadOrderExchange", true, false);
    }

    @Bean
    public Binding userOrderDeadBind() {
        return BindingBuilder.bind(userOrderDeadQueues()).to(userOrderDeadExchange()).with("deadOrder.A");
    }

    //创建监听消费队列，面向消费端
    @Bean
    public Queue userOrderConsumerQueue() {
        return new Queue("userOrderConsumerQueue",true);
    }
    @Bean
    public TopicExchange userOrderConsumerExchange(){
        return new TopicExchange("userOrderConsumerExchange",true,false);
    }
    @Bean
    public Binding userOrderConsumerBind(){
        return BindingBuilder.bind(userOrderConsumerQueue()).to(userOrderConsumerExchange()).with("consumerOrder.A");
    }



}
