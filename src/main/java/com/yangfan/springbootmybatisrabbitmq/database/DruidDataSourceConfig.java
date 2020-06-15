package com.yangfan.springbootmybatisrabbitmq.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
}
