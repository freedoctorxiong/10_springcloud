package com.yscoco.controller;

import com.netflix.loadbalancer.ILoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author Xiong
 * @date 2020-07-10 10:59
 */
@RestController
@RequestMapping("order")
@RefreshScope
public class OrderController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancer loadBalancer;

    @Value("${yscoco.name}")
    private String userName;

    @RequestMapping("/getConfig")
    public String getConfig() {
        return userName;
    }


    @RequestMapping("orderToMember")
    public Object orderToMember() {
        List<ServiceInstance> memeber = discoveryClient.getInstances("memeber");
        ServiceInstance singleAddres = loadBalancer.getSingleAddres(memeber);
        URI uri = singleAddres.getUri();
        String forObject = restTemplate.getForObject(uri + "getUser", String.class);
        return forObject;
    }
}
