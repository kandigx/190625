package com.kandigx.project.service.impl;

import com.kandigx.project.service.ThreadPoolService;
import com.kandigx.project.valid.validator.ValidList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * @author kandigx
 * @date 2019-07-10 15:59
 */
@Service
public class ValidThreadPoolServiceImpl implements ThreadPoolService {

    private final ExecutorService executorService;
    private final Validator validator;

    @Autowired
    public ValidThreadPoolServiceImpl(ExecutorService executorService, Validator validator) {
        this.executorService = executorService;
        this.validator = validator;
    }

    /**
     * 验证任务，异步执行
     * @param list
     * @return
     */
    @Async
    @Override
    public Map<String, String> executeValidTask(ValidList list) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(list);

        Map<String, String> validResult = new HashMap<>(15);
        if (constraintViolations != null && constraintViolations.size() > 0) {
            for (ConstraintViolation constraintViolation : constraintViolations) {
                validResult.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }

        return validResult;
    }

}
