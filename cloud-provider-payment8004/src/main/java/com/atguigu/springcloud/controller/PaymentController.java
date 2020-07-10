package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/10 16:47
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public CommonResult paymentZk(){
        log.info("**** PaymentController.paymentZk()");
        return new CommonResult(200, "zookeeper success", "spring cloud with zookeeper :" + serverPort + "    " + UUID.randomUUID().toString());
    }
}
