package com.kandigx.project.service.impl;

import com.kandigx.project.service.ValidService;
import com.kandigx.project.valid.validator.ValidList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.concurrent.Future;

/**
 * @author kandigx
 * @date 2019-07-10 15:59
 */
@Service
public class ValidServiceImpl implements ValidService {

    private static final Log log = LogFactory.getLog(ValidServiceImpl.class);

    private final Validator validator;
    private final int validListMaxSize;
    private final int validTimeOutMs;

    /**
     * 构造方法注入属性
     * @param validator
     * @param validListMaxSize
     * @param validTimeOutMs
     */
    @Autowired
    public ValidServiceImpl(Validator validator,
                            @Value("${valid.list-max-size}") int validListMaxSize,
                            @Value("${valid.time-out-ms}") int validTimeOutMs) {
        this.validator = validator;
        this.validListMaxSize = validListMaxSize;
        this.validTimeOutMs = validTimeOutMs;
    }


    /**
     * 调用线程池，使用异步方法进行验证
     * @param list
     * @return
     */
    @Async
    @Override
    public Future<List<Map<String, String>>> processAsyncValid(ValidList list) {
        log.info("async thread");
        return new AsyncResult<>(processValid(list));
    }

    /**
     * 验证数据 list
     * @param list
     * @return
     */
    @Override
    public List<Map<String, String>> processValid(ValidList list) {
        long startTime = System.currentTimeMillis();
        log.info("begin valid，list size : " + list.size());
        //进行验证，获取验证结果
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(list);

        //验证结果不为空的时候，包装结果信息
        if (constraintViolations != null && constraintViolations.size() > 0) {
            //返回的验证结果list
            Map<String, Map<String, String>> validResultMap = new HashMap<>();
            HashMap<String, String> fieldMap;
            String field,indexKey,propertyKey;

            //遍历验证失败结果
            for (ConstraintViolation violation : constraintViolations) {
                //判断是否为list
                if ((field = violation.getPropertyPath().toString()).contains("].")) {
                    propertyKey = field.substring(field.lastIndexOf("].") + 2);
                    if (validResultMap.containsKey((indexKey = field.substring(0, field.indexOf("].") + 1)))) {
                        validResultMap.get(indexKey).put(propertyKey, violation.getMessage());
                    } else {
                        fieldMap = new HashMap<>();
                        fieldMap.put(propertyKey, violation.getMessage());
                        validResultMap.put(indexKey, fieldMap);
                    }
                } else {
                    if (validResultMap.containsKey("key")) {
                        validResultMap.get("key").put(field, violation.getMessage());
                    } else {
                        fieldMap = new HashMap<>();
                        fieldMap.put(field, violation.getMessage());
                        validResultMap.put("key", fieldMap);
                    }
                }
            }

            long endTime = System.currentTimeMillis();
            log.info("end valid...");
            log.info("cost time : " + (endTime - startTime));

            return new ArrayList<>(validResultMap.values());
        }

        //验证成功则返回空list
        return new ArrayList<>();
    }

}
