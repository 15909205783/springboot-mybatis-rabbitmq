package com.yangfan.springbootmybatisrabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.yangfan.springbootmybatisrabbitmq.mapper.UserOrderMapper;
import com.yangfan.springbootmybatisrabbitmq.service.ConcurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserOrderListener  {
    private static final Logger log = LoggerFactory.getLogger(UserOrderListener.class);
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private ConcurrencyService concurrencyService;

    @RabbitListener(queues = "userOrderQueue")
    @RabbitHandler
    public void handler(Message message, Channel channel) throws Exception {
        long tag=message.getMessageProperties().getDeliveryTag();
        try {
            byte[] body=message.getBody();
            /*UserOrderDto entity=objectMapper.readValue(body, UserOrderDto.class);
            log.info("用户商城抢单监听到消息： {} ",entity);

            UserOrder userOrder=new UserOrder();
            BeanUtils.copyProperties(entity,userOrder);
            userOrder.setStatus(1);
            userOrderMapper.insertSelective(userOrder);*/

            String mobile=new String(body,"UTF-8");
            log.info("监听到抢单手机号： {} ",mobile);

            concurrencyService.manageRobbing(String.valueOf(mobile));
            channel.basicAck(tag,true);
        }catch (Exception e){
            log.error("用户商城下单 发生异常：",e.fillInStackTrace());
            channel.basicReject(tag,false);
        }
    }
}
