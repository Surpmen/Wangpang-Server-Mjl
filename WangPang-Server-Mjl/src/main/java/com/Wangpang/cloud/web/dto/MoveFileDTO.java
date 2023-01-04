package com.keke.cloud.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*移动文件数据传输对象类*/
@Data
@ApiModel(value = "移动文件DTO")
public class MoveFileDTO {

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "旧文件名")
    private String oldFilePath;
    @ApiModelProperty(value = "扩展名")
    private String extendName;

}
