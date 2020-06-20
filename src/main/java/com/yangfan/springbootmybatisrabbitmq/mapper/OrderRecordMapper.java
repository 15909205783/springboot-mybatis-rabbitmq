package com.yangfan.springbootmybatisrabbitmq.mapper;

import com.yangfan.springbootmybatisrabbitmq.entity.OrderRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRecord record);

    int insertSelective(OrderRecord record);

    OrderRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRecord record);

    int updateByPrimaryKey(OrderRecord record);

    List<OrderRecord> selectAll();
}
