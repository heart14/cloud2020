package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/10 17:28
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderController {

    public static final String INVOKE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zk")
    public CommonResult paymentInfo() {
        CommonResult result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", CommonResult.class);
        log.info("****zookeeper 调用8004服务 :{}", result);
        return result;
    }
}
