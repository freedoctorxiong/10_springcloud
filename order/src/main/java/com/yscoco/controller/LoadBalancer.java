package com.yscoco.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Xiong
 * @date 2020-07-10 11:21
 */
public interface LoadBalancer {
    /**
     * 根据多个不同的地址 返回单个调用rpc地址
     *
     * @param serviceInstances
     * @return
     */
    ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances);


    @Component
    public class RotationLoadBalancer implements LoadBalancer {
        private AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public ServiceInstance getSingleAddres(List<ServiceInstance> serviceInstances) {

            int index = atomicInteger.incrementAndGet() % serviceInstances.size();
            ServiceInstance serviceInstance = serviceInstances.get(index);
            return serviceInstance;
        }
    }
}