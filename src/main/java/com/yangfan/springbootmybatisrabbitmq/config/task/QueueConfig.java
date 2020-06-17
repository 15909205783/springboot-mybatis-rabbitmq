package com.yangfan.springbootmybatisrabbitmq.config.task;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {
    @Bean
    public Queue queueMessage() {
        return new Queue("orderQueue");
    }

//    @Bean
//    public Queue queueMessages() {
//        return new Queue(TopicRabbitConfig.messages);
//    }

    /*  @Bean
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
      }*/
    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("orderExchange");
    }
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange);
    }
}
