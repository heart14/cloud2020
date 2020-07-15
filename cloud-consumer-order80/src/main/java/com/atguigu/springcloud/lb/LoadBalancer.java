package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: LoadBalancer
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/15 14:24
 * @Version: v1.0
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstance);

}
