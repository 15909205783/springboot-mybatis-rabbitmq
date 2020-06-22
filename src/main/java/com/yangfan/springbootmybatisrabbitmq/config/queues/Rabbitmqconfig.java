package com.yangfan.springbootmybatisrabbitmq.config.queues;

import com.yangfan.springbootmybatisrabbitmq.consumer.UserOrderListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Rabbitmqconfig {
    @Autowired
    private Environment env;
    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue userOrderQueue() {
        return new Queue("userOrderQueue", true);
    }

    @Bean
    public TopicExchange userOrderExchange() {
        return new TopicExchange("userOrderExchange", true, false);
    }

    @Bean
    public Binding userOrderBinding() {
        return BindingBuilder.bind(userOrderQueue()).to(userOrderExchange()).with("order.A");
    }

//    @Autowired
//    private UserOrderListener userOrderListener;

    /*@Bean
    public SimpleMessageListenerContainer listenerContainerUserOrder(@Qualifier("userOrderQueue") Queue userOrderQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //container.setMessageConverter(new Jackson2JsonMessageConverter());

        //TODO：并发配置
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency", Integer.class));
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency", Integer.class));
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch", Integer.class));

        //TODO:消息确认机制
        container.setQueues(userOrderQueue);
        container.setMessageListener(userOrderListener);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return container;
    }*/

}
