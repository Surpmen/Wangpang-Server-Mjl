package com.keke.cloud.web.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 用户基础信息管理实体类
 */
@Data
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "open_id_index", columnNames = {"open_id"})
})
@Entity
@TableName("user")
public class UserBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) comment '用户id'")
    private Long userId;

    @Column(name = "open_id",columnDefinition = "varchar(30) comment 'openId qq登录使用'")
    private String openId;

    @Column(columnDefinition = "varchar(30) comment '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(30) comment '真实名'")
    private String realname;

    @Column(columnDefinition = "varchar(35) comment '密码'")
    private String password;

    @Column(columnDefinition = "varchar(35) comment 'qq密码'")
    private String qqPassword;

    @Column(columnDefinition = "varchar(15) comment '手机号码'")
    private String telephone;

    @Column(columnDefinition = "varchar(100) comment '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(3) comment '年龄'")
    private String sex;


    @Column(columnDefinition = "varchar(30) comment '生日'")
    private String birthday;

    @Column(columnDefinition = "varchar(10) comment '省'")
    private String addrProvince;

    @Column(columnDefinition = "varchar(10) comment '市'")
    private String addrCity;

    @Column(columnDefinition = "varchar(10) comment '区'")
    private String addrArea;

    @Column(columnDefinition = "varchar(50) comment '行业'")
    private String industry;

    @Column(columnDefinition = "varchar(50) comment '职位'")
    private String position;

    @Column(columnDefinition = "varchar(5000) comment '介绍'")
    private String intro;

    @Column(columnDefinition = "varchar(20) comment '盐值'")
    private String salt;//加密密码的盐

    @Column(columnDefinition = "varchar(100) comment '用户头像URL'")
    private String imageUrl;

    @Column(columnDefinition = "varchar(30) comment '注册时间'")
    private String registerTime;
    @Transient
    @TableField(exist = false)
    private String verificationCode;

    @Transient
    @TableField(exist = false)
    private String token;
    @Transient
    @TableField(exist = false)
    private String downloadDomain;
    @Transient
    @TableField(exist = false)
    private String viewDomain;
    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @TableField(exist = false)
    private List<Role> roleList;// 一个用户具有多个角色
}
