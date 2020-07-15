package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PaymentController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/1 15:06
 * @Version: v1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        int result = paymentService.createPayment(payment);
        log.info("****插入结果 :{}", result);
        if (result > 0) {
            return new CommonResult(200, "Server Port[" + serverPort + "]插入成功", result);
        } else {
            return new CommonResult(444, "Server Port[" + serverPort + "]插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayment(@PathVariable Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("****查询结果 :{}", result);
        if (result != null) {
            return new CommonResult(200, "Server Port[" + serverPort + "]查询成功", result);
        } else {
            return new CommonResult(444, "Server Port[" + serverPort + "]查询失败", null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public CommonResult discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("****element :{}", element);
        }

        List<ServiceInstance> instances1 = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances1) {
            log.info("****CLOUD-PAYMENT-SERVICE instance :{}\t{}\t{}\t{}", instance.getServiceId(), instance.getPort(), instance.getHost(), instance.getUri());
        }

        List<ServiceInstance> instances2 = discoveryClient.getInstances("CLOUD-ORDER-SERVICE");
        for (ServiceInstance instance : instances2) {
            log.info("****CLOUD-ORDER-SERVICE instance :{}\t{}\t{}\t{}", instance.getServiceId(), instance.getPort(), instance.getHost(), instance.getUri());
        }

        return new CommonResult(200, "service discovery", discoveryClient);
    }

    @GetMapping(value = "/payment/lb")
    public String paymentLb() {
        return "service port :" + serverPort;
    }
}
