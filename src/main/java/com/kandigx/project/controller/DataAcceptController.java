package com.kandigx.project.controller;

import com.kandigx.project.helper.ResultBean;
import com.kandigx.project.helper.ValidRequestException;
import com.kandigx.project.vo.OrderVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public ResultBean dataEntry(@RequestBody @Valid OrderVO orderVO, BindingResult result) throws ValidRequestException  {

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (ObjectError error : result.getAllErrors()) {
                errorMap.put(error.getObjectName(), error.getDefaultMessage());
            }
            throw new ValidRequestException(errorMap);
        }



        return ResultBean.success();
    }


}
