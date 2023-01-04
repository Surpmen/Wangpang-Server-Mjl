package com.keke.cloud.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*用户注册登陆数据表单*/
@Data
@ApiModel(value = "用户登录Vo")
public class UserLoginVo {
    @ApiModelProperty(value = "用户id", example = "1")
    private long userId;
    @ApiModelProperty(value = "用户名", example = "金戈铁马")
    private String username;
    @ApiModelProperty(value = "真实名", example = "大马士革")
    private String realname;
    @ApiModelProperty(value = "qq用户名", example = "北辰之芯")
    private String qqUsername;
    @ApiModelProperty(value = "qq用户头像", example = "https://thirdqq.qlogo.cn/g?b=oidb&k=qxLE4dibR9sic8kS7mHLxlLw&s=100&t=1557468980")
    private String qqImageUrl;
    @ApiModelProperty(value = "手机号", example = "188****6631")
    private String telephone;
    @ApiModelProperty(value = "邮箱", example = "328****8631@qq.com")
    private String email;
    @ApiModelProperty(value = "性别", example = "男")
    private String sex;
    @ApiModelProperty(value = "生日", example = "2001-03-022")
    private String birthday;
    @ApiModelProperty(value = "省", example = "云南省")
    private String addrProvince;
    @ApiModelProperty(value = "市", example = "昆明市")
    private String addrCity;
    @ApiModelProperty(value = "区", example = "呈贡区")
    private String addrArea;
    @ApiModelProperty(value = "行业", example = "学生")
    private String industry;
    @ApiModelProperty(value = "职位", example = "无")
    private String position;
    @ApiModelProperty(value = "个人介绍", example = "愿原力与你同在")
    private String intro;
    @ApiModelProperty(value = "用户头像地址", example = "\\upload\\20200405\\93811586079860974.png")
    private String imageUrl;
    @ApiModelProperty(value = "注册时间", example = "2022-12-28 14:21:52")
    private String registerTime;
    @ApiModelProperty(value = "最后登录时间", example = "2022-1-4 14:21:52")
    private String lastLoginTime;
    @ApiModelProperty(value = "Token 接口访问凭证")
    private String token;

}
