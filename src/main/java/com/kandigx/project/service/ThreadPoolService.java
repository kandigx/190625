package com.kandigx.project.service;

import com.kandigx.project.valid.validator.ValidList;

import java.util.Map;

/**
 * 线程池
 *
 * @author kandigx
 * @date 2019-07-10 15:53
 */
public interface ThreadPoolService {

    Map<String, String> executeValidTask(ValidList list);

}
