package com.yangfan.springbootmybatisrabbitmq.mapper;

import com.yangfan.springbootmybatisrabbitmq.entity.ProductRobbingRecord;
import org.springframework.stereotype.Component;

@Component
public interface ProductRobbingRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRobbingRecord record);

    int insertSelective(ProductRobbingRecord record);

    ProductRobbingRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRobbingRecord record);

    int updateByPrimaryKey(ProductRobbingRecord record);
}

