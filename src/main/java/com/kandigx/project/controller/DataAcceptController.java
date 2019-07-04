package com.kandigx.project.controller;

import com.kandigx.project.helper.ResultBean;
import com.kandigx.project.helper.ValidRequestException;
import com.kandigx.project.valid.validator.ValidList;
import com.kandigx.project.vo.OrderVO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("dataEntry")
    public ResultBean dataEntry(@RequestBody @Valid ValidList<OrderVO> list) throws ValidRequestException  {

        return ResultBean.success();
    }


}
