package com.yangfan.springbootmybatisrabbitmq.service;

import com.yangfan.springbootmybatisrabbitmq.entity.Product;
import com.yangfan.springbootmybatisrabbitmq.mapper.ProductMapper;
import com.yangfan.springbootmybatisrabbitmq.mapper.ProductRobbingRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcurrencyService {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyService.class);
    private static final String ProductNo = "product_10010";
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRobbingRecordMapper productRobbingRecordMapper;

    public void manageRobbing(String mobile) {
        try {
            Product product = productMapper.selectByProductNo(ProductNo);
            if (product != null && product.getTotal() > 0) {
                log.info("当前手机号：{} 恭喜您抢到单了!", mobile);
            } else {
                log.error("当前手机号：{} 抢不到单!", mobile);
            }
        } catch (Exception e) {
            log.error("处理抢单发生异常：mobile={} ", mobile);
        }
    }


}
