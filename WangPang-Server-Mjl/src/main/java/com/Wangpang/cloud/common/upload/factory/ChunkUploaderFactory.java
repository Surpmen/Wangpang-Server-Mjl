package com.keke.cloud.common.upload.factory;


import com.keke.cloud.common.domain.UploadFile;
import com.keke.cloud.common.upload.Uploader;
import com.keke.cloud.common.upload.product.ChunkUploader;
/*文件上传抽象工厂接口实现类*/
public class ChunkUploaderFactory implements UploaderFactory {

    @Override
    public Uploader getUploader() {
        return new ChunkUploader();
    }

    @Override
    public Uploader getUploader(UploadFile uploadFile) {
        return new ChunkUploader(uploadFile);
    }

}
