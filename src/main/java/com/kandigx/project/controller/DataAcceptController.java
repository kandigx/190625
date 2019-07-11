package com.kandigx.project.controller;

import com.kandigx.project.helper.ResultBean;
import com.kandigx.project.service.ValidService;
import com.kandigx.project.valid.validator.ValidList;
import com.kandigx.project.vo.OrderVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据接收 controller
 *
 * @author kandigx
 * @create 2019-06-27 18:35
 */
@RestController
@RequestMapping("accept")
public class DataAcceptController {

    private static final Log log = LogFactory.getLog(DataAcceptController.class);

    private final ValidService validService;


    @Autowired
    public DataAcceptController(ValidService validService) {
        this.validService = validService;

    }

    @GetMapping("order")
    public String accept() {

        return "nice to meet you ";
    }

    @PostMapping("dataEntry")
    public ResultBean dataEntry(@RequestBody ValidList<OrderVO> list) {

        return validService.validList(list);
    }


}
