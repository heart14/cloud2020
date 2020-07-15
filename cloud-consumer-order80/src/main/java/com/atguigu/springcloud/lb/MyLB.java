package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MyLB
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/15 14:26
 * @Version: v1.0
 */
@Component
@Slf4j
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;

        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));

        log.info("[MyLB] the {} time call the service", next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        if (serviceInstances == null || serviceInstances.size() <= 0) {
            log.error("no reachable service");
        }
        int serviceIndex = getAndIncrement() % serviceInstances.size();
        ServiceInstance serviceInstance = serviceInstances.get(serviceIndex);
        log.info("[MyLB] get service instance :{}", serviceInstance.getServiceId());
        return serviceInstance;
    }

}
