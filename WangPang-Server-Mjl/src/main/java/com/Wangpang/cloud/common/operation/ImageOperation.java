package com.keke.cloud.common.operation;
import net.coobird.thumbnailator.Thumbnails;
import java.io.File;
import java.io.IOException;

/*图片文件操作通用组件类*/
public class ImageOperation {
    /**
     * 将进行向左旋
     * @param inFile 源文件
     * @param outFile 目的文件
     * @param angle 角度
     * @throws IOException io异常
     */
    public static void leftTotation(File inFile, File outFile, int angle) throws IOException {
        Thumbnails.of(inFile).scale(1).outputQuality(1).rotate(-angle).toFile(outFile);
    }

    /**
     * 将进行向右旋
     * @param inFile 源文件
     * @param outFile 目的文件
     * @param angle 角度
     * @throws IOException io异常
     */
    public static void rightTotation(File inFile, File outFile, int angle) throws IOException {
        Thumbnails.of(inFile).scale(1).outputQuality(1).rotate(angle).toFile(outFile);
    }

    /**
     * 将图片进行压缩
     * @param inFile 源文件
     * @param outFile 目的文件
     * @throws IOException io异常
     */
    public static void thumbnailsImage(File inFile, File outFile, int imageSize) throws IOException {

        Thumbnails.of(inFile).size(imageSize, imageSize)
                .toFile(outFile);

    }
}
