package com.keke.cloud.web.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*批量删除文件数据传输对象类*/
@Data
@ApiModel(value = "批量删除文件DTO")
public class BatchDeleteFileDTO {
    @ApiModelProperty(value="文件集合")
    private String files;

}
