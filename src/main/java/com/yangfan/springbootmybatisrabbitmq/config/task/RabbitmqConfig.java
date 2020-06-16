//package com.yangfan.springbootmybatisrabbitmq.config.task;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@ConfigurationProperties(prefix = "rabbitmq")
//@Configuration
//public class RabbitmqConfig {
//    @Value("queueName")
//    private String queueName;
//    @Value("exchangeName")
//    private String exchangeName;
//    @Value("routingKey")
//    private String routingKey;
//    @Bean
//    public Queue OrderQueue() {
//        return new Queue(queueName);
//    }
//    @Bean
//    FanoutExchange fanoutExchange(){
//        return new FanoutExchange(exchangeName);
//    }
//    @Bean
//    Binding bindingExchangeA(Queue OrderQueue, FanoutExchange fanoutExchange){
//      return   BindingBuilder.bind(OrderQueue).to(fanoutExchange);
//    }
//}
