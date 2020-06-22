package com.yangfan.springbootmybatisrabbitmq.controller;

import com.yangfan.springbootmybatisrabbitmq.entity.UserOrder;
import com.yangfan.springbootmybatisrabbitmq.mapper.UserOrderMapper;
import com.yangfan.springbootmybatisrabbitmq.response.BaseResponse;
import com.yangfan.springbootmybatisrabbitmq.response.StatusCode;
import dto.UserOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class UserOrderController {
    private static final Logger log = LoggerFactory.getLogger(UserOrderController.class);

    private static final String Prefix = "user/order";

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private UserOrderMapper userOrderMapper;

    /**
     * 用户商城下单
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = Prefix + "/push/dead/queue")
    public BaseResponse pushUserOrderV2(@RequestBody UserOrderDto dto) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        UserOrder userOrder = new UserOrder();
        try {
            BeanUtils.copyProperties(dto, userOrder);
            userOrder.setStatus(1);
            userOrderMapper.insertSelective(userOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Integer id = userOrder.getId();

            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//            rabbitTemplate.setExchange(env.getProperty("user.order.dead.produce.exchange.name"));
//            rabbitTemplate.setRoutingKey(env.getProperty("user.order.dead.produce.routing.key.name"));

            rabbitTemplate.convertAndSend(id, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties properties = message.getMessageProperties();
                    properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    properties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Integer.class);
                    return message;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 用户商城下单-动态TTL设置
     *
     * @return
     */
    @RequestMapping(value = "getDeadQueue")
    public BaseResponse pushUserOrderV3() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        UserOrder userOrder = new UserOrder();
        try {
            // BeanUtils.copyProperties(dto, userOrder);
            userOrder.setStatus(1);
            userOrder.setId(198);
           userOrder.setUserId(1);
            userOrder.setOrderNo("198");
           // userOrder.setCreateTime();
            userOrderMapper.insertSelective(userOrder);
            log.info("用户商城下单成功!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Integer id = userOrder.getId();

            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange("deadOrderExchange");
            rabbitTemplate.setRoutingKey("deadOrder.A");

            Long ttl = 15000L; //可以用随机数替代

            rabbitTemplate.convertAndSend(id, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties properties = message.getMessageProperties();
                    properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    properties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Integer.class);

                    properties.setExpiration(String.valueOf(ttl));
                    return message;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
