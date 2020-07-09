package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @ClassName: PaymentService
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/1 15:03
 * @Version: v1.0
 */
public interface PaymentService {

    /**
     * 创建payment
     *
     * @param payment
     * @return
     */
    int createPayment(Payment payment);

    /**
     * 查询payment
     *
     * @param id
     * @return
     */
    Payment getPaymentById(Long id);
}
