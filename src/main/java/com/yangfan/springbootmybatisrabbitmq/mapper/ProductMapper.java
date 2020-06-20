package com.yangfan.springbootmybatisrabbitmq.mapper;

import com.yangfan.springbootmybatisrabbitmq.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByProductNo(@Param("productNo") String productNo);

    int updateTotal(Product record);
}
