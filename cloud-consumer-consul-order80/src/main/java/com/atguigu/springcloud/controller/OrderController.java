package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/13 15:37
 * @Version: v1.0
 */
@RestController
@Slf4j
public class OrderController {

    public static final String INVOKE_URL = "http://cloud-payment-service-consul";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public CommonResult paymentConsul() {
        CommonResult result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", CommonResult.class);
        log.info("通过Consul注册中心 调用Payment8006服务 :{}",result);
        return result;
    }
}
