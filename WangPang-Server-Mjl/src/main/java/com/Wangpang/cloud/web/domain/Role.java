package com.keke.cloud.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色管理实体类
 */
@Data
@Table(name = "role")
@Entity
@TableName("role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long roleId; //角色id 编号

    private String role; // 角色名，角色标识程序中判断使用,如"admin",这个是唯一的:

    private String description; // 角色描述,UI界面显示使用

    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissions;//权限列表，立即从数据库中进行加载数据

}
