package com.yangfan.springbootmybatisrabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yangfan.springbootmybatisrabbitmq.mapper")
public class SpringbootMybatisRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisRabbitmqApplication.class, args);
	}

}
