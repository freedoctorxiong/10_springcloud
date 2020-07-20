package com.yscoco.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiong
 * @date 2020-07-10 10:42
 */
@RestController
public class IMemberService {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("getUser")
    public String getUser(Integer userId) {

        return "获取用户,端口号：" + serverPort;
    }
}
