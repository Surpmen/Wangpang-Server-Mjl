package com.keke.cloud.common.domain;

import lombok.Data;
/*
* 引用上传文件的组件类
* */
@Data
public class UploadFile {
    private String fileName;//文件名
    private String fileType;//文件类型
    private long fileSize;//文件大小
    private String timeStampName;//最后一次修改时间
    private int success;//是否上传成功
    private String message;//文件信息
    private String url;//请求地址
    private Integer isOss;
    //切片上传相关参数如下：
    private String taskId;//上传任务ID
    private int chunkNumber;//切片数目
    private long chunkSize;//每片大小
    private int totalChunks;//总切片数
    private String identifier;//文件唯一标识
    private long totalSize;//文件总大小
    private long currentChunkSize;//具体切片大小
}
