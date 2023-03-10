package com.keke.cloud.common.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*注册数据传输对象类*/
@Data
@ApiModel(value = "用户注册DTO")
public class RegisterDTO {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String telephone;
    @ApiModelProperty(value = "密码")
    private String password;
}
