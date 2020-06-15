package com.yangfan.springbootmybatisrabbitmq.mapper;

import com.yangfan.springbootmybatisrabbitmq.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderMapper {
    void insert(Order order);
}
