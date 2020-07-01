package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/1 15:59
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_BASE_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("消费者 插入... {}", payment);
        return restTemplate.postForObject(PAYMENT_BASE_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id) {
        log.info("消费者 查询... {}", id);
        return restTemplate.getForObject(PAYMENT_BASE_URL + "/payment/get/" + id, CommonResult.class);
    }
}
