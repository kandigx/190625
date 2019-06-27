package com.kandigx.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据接收 controller
 *
 * @author kandigx
 * @create 2019-06-27 18:35
 */
@RestController
@RequestMapping("accept")
public class DataAcceptController {

    @GetMapping("order")
    public String accept() {

        return "nice to meet you ";
    }

}
