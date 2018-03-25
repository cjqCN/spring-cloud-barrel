package com.cjq.springcloud.barrel.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jqChan
 * @date 2018/3/25
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "hello world";
    }
}
