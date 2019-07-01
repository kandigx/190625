package com.kandigx.project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author kandigx
 * @create 2019-06-28 11:14
 */
@Setter
@Getter
@ToString
public class OrderVO {

    @NotBlank(message = "订单编号不能为空")
    private String orderId;

    @NotBlank(message = "订单时间不能为空")
    private String orderTime;

    @NotBlank(message = "产品编码列表不能为空")
    private List<String> productIdList;

    @NotBlank(message = "订单价格不能为空")
    private String price;

    @NotBlank(message = "客户编码不能为空")
    private String clientId;

    @NotBlank(message = "客户名称不能为空")
    private String clientName;

    @NotBlank(message = "客户年龄不能为空")
    private String age;

    @NotBlank(message = "客户联系方式不能为空")
    private String telephone;

    @NotBlank(message = "客户邮箱不能为空")
    @Email
    private String email;

}
