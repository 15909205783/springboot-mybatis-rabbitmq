package com.yangfan.springbootmybatisrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("UserOrderListener")
public class UserOrderListener {
    private static final Logger log = LoggerFactory.getLogger(UserOrderListener.class);
    //@Autowired

}
