package com.kandigx.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.kandigx.project.helper.ResultBean;
import com.kandigx.project.service.MsgHandlerService;
import com.kandigx.project.service.ValidService;
import com.kandigx.project.util.CollectionUtil;
import com.kandigx.project.valid.validator.ValidList;
import com.kandigx.project.vo.OrderVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    private final MsgHandlerService msgHandlerService;
    private final int validListMaxSize;
    private final int validTimeOutMs;

    @Autowired
    public DataAcceptController(ValidService validService,
                                MsgHandlerService amqpHandlerService,
                                @Value("${valid.list-max-size}") int validListMaxSize,
                                @Value("${valid.time-out-ms}") int validTimeOutMs) {
        this.validService = validService;
        this.msgHandlerService = amqpHandlerService;
        this.validListMaxSize = validListMaxSize;
        this.validTimeOutMs = validTimeOutMs;
    }

    @GetMapping("order")
    public String accept() {

        return "nice to meet you ";
    }

    @PostMapping("dataEntry")
    public ResultBean dataEntry(@RequestBody ValidList<OrderVO> list) {

        //大于设定的数据量，则使用多线程
        if (list != null && list.size() > validListMaxSize) {

            //分成5片
            List<ValidList<OrderVO>> shardList = CollectionUtil.averageAssignValidList(list,5);
            List<Future<List<Map<String, String>>>> taskList = new ArrayList<>();
            List<Map<String, String>> result = new ArrayList<>();
            //遍历分片处理结果任务放到list中
            for (ValidList<OrderVO> orderVOS : shardList) {
                //无法在同一个类中调用 @Async 注释的多线程调用方法，因此此段代码放到 controller 中
                taskList.add(validService.processAsyncValid(orderVOS));
            }
            boolean emptyFlag = true;

            try {
                //遍历任务设置超时时间及获取结果
                for (Future<List<Map<String, String>>> task : taskList) {
                    List<Map<String, String>> taskResult = task.get(validTimeOutMs, TimeUnit.MILLISECONDS);
                    if (!taskResult.isEmpty()) {
                        emptyFlag = false;
                        result.addAll(taskResult);
                    }
                }
                //判断是否有校验失败的信息
                if (!emptyFlag) {
                    return ResultBean.validError(result);
                }

            } catch (ExecutionException | InterruptedException | TimeoutException e) {
                log.warn(e.toString());

                return ResultBean.validError("验证失败，请稍后重试！");
            }
        } else {
            //否则直接进行处理
            List<Map<String, String>> result = validService.processValid(list);
            if (!result.isEmpty()) {
                return ResultBean.validError(result);
            }
        }

        //发送数据到mq
        msgHandlerService.msgPub(JSONObject.toJSONString(list));

        return ResultBean.success();
    }


}
