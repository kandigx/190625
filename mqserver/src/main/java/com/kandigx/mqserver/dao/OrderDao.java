package com.kandigx.mqserver.dao;

import com.kandigx.project.vo.OrderVO;

import java.util.List;

/**
 * @author kandigx
 * @date 2019-07-16 10:28
 */
public interface OrderDao {

    List<OrderVO> listOrder();

}
