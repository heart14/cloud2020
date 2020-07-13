package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/13 15:27
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @GetMapping(value = "/payment/consul")
    public CommonResult paymentConsul() {
        return new CommonResult(200, "consul success", "consul " + UUID.randomUUID().toString().toUpperCase());
    }
}
