package com.keke.cloud.web.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件目录树节点管理实体类
 */
@Data
public class TreeNode {

    private Long id;//节点id

    private String label;//节点名

    private Long depth;//节点深度

    private String state = "closed";//节点是否被关闭

    private Map<String, String> attributes = new HashMap<>();//属性集合

    private List<TreeNode> children = new ArrayList<>();//子节点列表

}
