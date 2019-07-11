package com.kandigx.project.util;

import com.kandigx.project.valid.validator.ValidList;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合类工具类
 *
 * @author kandigx
 * @date 2019-07-11 16:24
 */
public class CollectionUtil {

    /**
     * list 分片
     * @param source 源list
     * @param n 分片个数
     * @param <T>
     * @return
     */
    public static <T> List<ValidList<T>> averageAssignValidList(ValidList<T> source, int n){
        List<ValidList<T>> result = new ArrayList<>();
        int remain = source.size() % n;
        int shardNum = source.size() / n;
        int offset = 0;

        ValidList<T> shardList;
        for (int i = 0; i < n; i++) {
            if (remain > 0) {
                shardList = source.subValidList(i * shardNum + offset, (i + 1) * shardNum + offset + i);
                remain--;
                offset++;
            } else {
                shardList = source.subValidList(i * shardNum + offset, (i + 1) * shardNum + offset);
            }
            result.add(shardList);
        }

        return result;
    }

}
