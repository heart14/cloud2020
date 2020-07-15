package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: OrderFeignMain80
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/15 17:15
 * @Version: v1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderFeignMain80 {

    /**
     * 要点：
     *      1）@EnableEurekaClient注解向Eureka注册中心注册
     *      2）@EnableFeignClients开启Feign支持
     */

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
