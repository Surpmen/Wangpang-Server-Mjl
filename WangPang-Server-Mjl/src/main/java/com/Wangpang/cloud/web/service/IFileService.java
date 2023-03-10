package com.keke.cloud.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.keke.cloud.web.domain.FileBean;
/*文件服务接口*/
public interface IFileService extends IService<FileBean> {
    void increaseFilePointCount(Long fileId);

    void decreaseFilePointCount(Long fileId);

    void deleteLocalFile(FileBean fileBean);
}
