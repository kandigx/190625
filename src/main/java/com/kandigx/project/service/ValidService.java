package com.kandigx.project.service;

import com.kandigx.project.valid.validator.ValidList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 验证接口
 *
 * @author kandigx
 * @date 2019-07-11 09:19
 */
public interface ValidService {

    Future<List<Map<String, String>>> processAsyncValid(ValidList list);

    List<Map<String, String>> processValid(ValidList list);

}
