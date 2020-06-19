package com.yangfan.springbootmybatisrabbitmq.config.task;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Rabbitmqconfig {

    @Bean(name = "userOrderQueue")
    public Queue userOrderQueue(){
        return new Queue("userOrderQueue.",true);
    }
    @Bean
    public TopicExchange userOrderExchange(){
        return new TopicExchange("userOrderExchange",true,false);
    }
    @Bean
    public Binding userOrderBinding(){
      return   BindingBuilder.bind(userOrderQueue()).to(userOrderExchange()).with("order.A");
    }

}
