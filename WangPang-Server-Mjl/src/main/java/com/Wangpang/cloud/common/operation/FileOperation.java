package com.keke.cloud.common.operation;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.keke.cloud.common.util.FileUtil;
import com.keke.cloud.common.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件操作通用组件类
 */
public class FileOperation {
    private static Logger logger = LoggerFactory.getLogger(FileOperation.class);

    /**
     * 创建文件
     * @param fileUrl 文件路径
     * @return 新文件
     */
    public static File newFile(String fileUrl) {
        File file = new File(fileUrl);
        return file;
    }

    /**
     * 删除文件
     * @param file 文件
     * @return 是否删除成功
     */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        } else {
            for (File newfile : file.listFiles()) {
                deleteFile(newfile);
            }
        }
        return file.delete();
    }

    /**
     * 删除文件
     * @param fileUrl 文件路径
     * @return 删除是否成功
     */
    public static boolean deleteFile(String fileUrl) {
        File file = newFile(fileUrl);
        return deleteFile(file);
    }
    /**
     * 得到文件大小
     * @param fileUrl 文件路径
     * @return 文件大小
     */
    public static long getFileSize(String fileUrl) {
        File file = newFile(fileUrl);
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }
    /**
     * 得到文件大小
     * @param file 文件
     * @return 文件大小
     */
    public static long getFileSize(File file) {
        if (file == null) {
            return 0;
        }
        return file.length();
    }
    /**
     * 创建目录
     * @param file 文件
     * @return 是否创建成功
     */
    public static boolean mkdir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    /**
     * 创建目录
     * @param fileUrl 文件路径
     * @return 是否创建成功
     */
    public static boolean mkdir(String fileUrl) {
        if (fileUrl == null) {
            return false;
        }
        File file = newFile(fileUrl);
        if (file.exists()) {
            return true;
        }

        return file.mkdirs();
    }

    /**
     * 拷贝文件
     * @param fileInputStream  文件输入流
     * @param fileOutputStream 文件输出流
     * @throws IOException io异常
     */
    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws IOException {
        try {
            byte[] buf = new byte[4096];  //8k的缓冲区

            int len = fileInputStream.read(buf); //数据在buf中，len代表向buf中放了多少个字节的数据，-1代表读不到
            while (len != -1) {

                fileOutputStream.write(buf, 0, len); //读多少个字节，写多少个字节

                len = fileInputStream.read(buf);
            }

        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    /**
     * 拷贝文件
     * @param src  源文件
     * @param dest 目的文件
     * @throws IOException io异常
     */
    public static void copyFile(File src, File dest) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dest);

        copyFile(in, out);


    }

    /**
     * 拷贝文件
     * @param srcUrl  源路径
     * @param destUrl 目的路径
     * @throws IOException io异常
     */
    public static void copyFile(String srcUrl, String destUrl) throws IOException {
        if (srcUrl == null || destUrl == null) {
            return;
        }
        File srcFile = newFile(srcUrl);
        File descFile = newFile(destUrl);
        copyFile(srcFile, descFile);
    }
    /**
     * 文件解压缩
     * @param sourceFile   需要解压的文件
     * @param destDirPath 目的路径
     * @return 解压目录列表
     */
    public static List<String> unzip(File sourceFile, String destDirPath) {
        ZipFile zipFile = null;
        Set<String> set = new HashSet<String>();
        // set.add("/");
        List<String> fileEntryNameList = new ArrayList<>();
        try {
            zipFile = new ZipFile(sourceFile, Charset.forName("GBK"));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                String[] nameStrArr = entry.getName().split("/");

                String nameStr = "/";
                for (int i = 0; i < nameStrArr.length; i++) {
                    if (!"".equals(nameStrArr[i])) {
                        nameStr = nameStr + "/" + nameStrArr[i];
                        set.add(nameStr);
                    }

                }
                logger.info("解压" + entry.getName());
                String zipPath = "/" + entry.getName();


                fileEntryNameList.add(zipPath);
                //如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + File.separator + entry.getName();
                    File dir = FileOperation.newFile(dirPath);

                    dir.mkdir();
                } else {
                    //如果是文件，就先创建一个文件，然后用io流把内容拷过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[2048];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("无法将文件进行解压", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (String zipPath : fileEntryNameList) {
            if (FileUtil.isImageFile(FileUtil.getFileType(zipPath))) {
                File file = new File(destDirPath + zipPath);
                File minFile = new File(destDirPath + FileUtil.getFileNameNotExtend(zipPath) + "_min." + FileUtil.getFileType(zipPath));
                try {
                    ImageOperation.thumbnailsImage(file, minFile, 300);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<String> res = new ArrayList<>(set);
        return res;
    }

    /**
     * 解压rar
     * @param sourceFile        需要解压的文件
     * @param destDirPath 目的路径
     * @throws Exception
     */
    public static List<String> unrar(File sourceFile, String destDirPath) throws Exception {
        File destDir = new File(destDirPath);
        Set<String> set = new HashSet<String>();
        Archive archive = null;
        FileOutputStream fos = null;
        System.out.println("Starting 开始解压...");
        try {
            archive = new Archive(sourceFile);
            FileHeader fh = archive.nextFileHeader();
            int count = 0;
            File destFileName = null;
            while (fh != null) {
                set.add("/" + fh.getFileName());
                System.out.println((++count) + ") " + fh.getFileName());
                String compressFileName = fh.getFileName().trim();
                destFileName = new File(destDir.getAbsolutePath() + "/" + compressFileName);
                if (fh.isDirectory()) {
                    if (!destFileName.exists()) {
                        destFileName.mkdirs();
                    }
                    fh = archive.nextFileHeader();
                    continue;
                }
                if (!destFileName.getParentFile().exists()) {
                    destFileName.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(destFileName);
                archive.extractFile(fh, fos);
                fos.close();
                fos = null;
                fh = archive.nextFileHeader();
            }
            archive.close();
            archive = null;
            System.out.println("Finished 解压完成!");
        } catch (Exception e) {
            throw e;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos = null;
                } catch (Exception e) {
                }
            }
            if (archive != null) {
                try {
                    archive.close();
                    archive = null;
                } catch (Exception e) {
                }
            }
        }
        List<String> res = new ArrayList<>(set);
        return res;
    }
    public static long deleteFileFromDisk(String fileurl) {
        String fileUrl = PathUtil.getStaticPath() + fileurl;
        String extendName = FileUtil.getFileType(fileUrl);
        String minFileUrl = fileUrl.replace("." + extendName, "_min." + extendName);
        long filesize = getFileSize(fileUrl);

        FileOperation.deleteFile(fileUrl);
        FileOperation.deleteFile(minFileUrl);

        return filesize;
    }
}
