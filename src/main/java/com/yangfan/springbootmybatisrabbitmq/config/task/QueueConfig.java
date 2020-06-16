package com.yangfan.springbootmybatisrabbitmq.config.task;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class QueueConfig {
    @Bean
    public Queue queueMessage() {
        return new Queue("orderQueue");
    }

//    @Bean
//    public Queue queueMessages() {
//        return new Queue(TopicRabbitConfig.messages);
//    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("orderExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, DirectExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("order.A");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, DirectExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("order.A");
    }

}
