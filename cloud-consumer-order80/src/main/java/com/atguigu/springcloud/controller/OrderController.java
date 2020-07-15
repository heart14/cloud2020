package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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

    //    public static final String PAYMENT_BASE_URL = "http://localhost:8001";
    public static final String PAYMENT_BASE_URL = "http://CLOUD-PAYMENT-SERVICE";

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

    @GetMapping(value = "/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable Long id) {
        log.info("消费者 查询... {}", id);
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(PAYMENT_BASE_URL + "/payment/get/" + id, CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("操作成功 :{}", responseEntity);
            return responseEntity.getBody();
        } else {
            log.error("操作失败 :{}", responseEntity);
            return new CommonResult<>(responseEntity.getStatusCodeValue(), "操作失败", null);
        }
    }

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLb() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstances == null || serviceInstances.size() <= 0) {
            return "no reachable service";
        }

        ServiceInstance serviceInstance = loadBalancer.instance(serviceInstances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb", String.class);

    }
}
