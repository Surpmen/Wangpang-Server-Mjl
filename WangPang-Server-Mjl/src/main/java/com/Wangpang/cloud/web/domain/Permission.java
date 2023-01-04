package com.keke.cloud.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 权限管理实体类
 */
@Data
@Table(name = "permission")
@Entity
@TableName("permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long permissionId;//主键.

    @Column
    private String name;//权限名称

    @Column
    private String resourceType;//资源类型，[menu|button]

    @Column
    private String url;//资源路径.
    @Column
    private String permission;//权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    @Column
    private Long parentId;//父编号

    @Column
    private String parentIds;//父编号列表

    @Column
    private Boolean available = Boolean.FALSE;//是否生效

    @Transient
    @TableField(exist = false)
    private List<Role> roles;//角色列表
}
