package com.kandigx.project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 产品信息VO
 *
 * @author kandigx
 * @create 2019-07-01 09:57
 */
@Getter
@Setter
@ToString
public class ProductInfoVO {

    private String productId;
    private String productName;
    private String productType;
    private String category;
    private String brand;
    private String price;
    private String weight;
    private String height;
    private String length;
    private String width;

}
