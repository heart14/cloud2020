package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: PaymentFeignService
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/15 17:17
 * @Version: v1.0
 */
@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /**
     * 要点：
     *      1）接口上要加@FeignClient注解，值为所要调用的服务的服务名
     *      2）接口上要加@Component或者@Service注解，在Spring容器中注入当前bean
     *      3）接口方法头要与所调用服务暴露出来的接口方法头完全一致，包括接口上的注解
     */

    /**
     * 调用CLOUD-PAYMENT-SERVICE服务提供者提供的接口
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
