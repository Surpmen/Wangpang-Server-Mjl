package com.keke.cloud.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*解开压缩文件数据传输对象类*/
@Data
@ApiModel(value = "解压缩文件DTO")
public class UnzipFileDTO {
    @ApiModelProperty(value = "文件url")
    private String fileUrl;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
}
