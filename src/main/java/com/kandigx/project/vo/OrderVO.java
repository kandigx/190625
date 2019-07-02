package com.kandigx.project.vo;

import com.kandigx.project.valid.anno.Gender;
import com.kandigx.project.valid.anno.PositiveInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "产品编码列表不能为空")
    private List<String> productIdList;

    @NotBlank(message = "订单价格不能为空")
    private String price;

    @NotBlank(message = "客户编码不能为空")
    private String clientId;

    @NotBlank(message = "客户名称不能为空")
    @Length(message = "客户名称长度不超过50位",max = 50)
    private String clientName;

    @Gender
    private String gender;

    @NotBlank(message = "客户年龄不能为空")
    @PositiveInteger()
    @Max(message = "最大年龄不能超过150", value = 150)
    private String age;

    @NotBlank(message = "客户联系方式不能为空")
    @Length(message = "客户联系方式长度不超过50位",max = 50)
    private String telephone;

    @NotBlank(message = "客户邮箱不能为空")
    @Length(message = "客户邮箱长度不超过50位",max = 50)
    @Email
    private String email;

}
