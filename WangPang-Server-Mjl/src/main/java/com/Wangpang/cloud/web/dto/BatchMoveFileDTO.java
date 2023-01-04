package com.keke.cloud.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*批量移动文件数据传输对象类*/
@Data
@ApiModel(value = "批量移动文件DTO")
public class BatchMoveFileDTO {
    @ApiModelProperty(value="文件集合")
    private String files;
    @ApiModelProperty(value="文件路径")
    private String filePath;


}
