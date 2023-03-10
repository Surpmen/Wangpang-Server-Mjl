package com.keke.cloud.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*文件重命名数据传输对象类*/
@Data
@ApiModel(value = "重命名文件DTO")
public class RenameFileDTO {
    private Long userFileId;
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    @ApiModelProperty(value = "文件名")
    private String fileName;
    @ApiModelProperty(value = "是否是目录")
    private Integer isDir;
    @ApiModelProperty(value = "旧文件名")
    private String oldFileName;
    @ApiModelProperty(value = "是否是OSS")
    private Integer isOss;
}
