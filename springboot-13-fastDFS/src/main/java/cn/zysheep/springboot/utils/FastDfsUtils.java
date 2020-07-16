package cn.zysheep.springboot.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: FastDfsUtils
 * @desc
 * @Author: 三月三
 */
@Component
@Slf4j
public class FastDfsUtils {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 文件上传, byte 流类型
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        System.out.println(storePath.getGroup() + "===" + storePath.getPath() + "======" + storePath.getFullPath());
        return storePath.getFullPath();

    }

    /**
     * MultipartFile类型的文件上传ַ
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * 普通文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath path = fastFileStorageClient.uploadFile(inputStream, file.length(),
                FilenameUtils.getExtension(file.getName()), null);
        return getResAccessUrl(path);
    }

    /**
     * 带输入流形式的文件上传
     *
     * @param inputStream
     * @param size
     * @param fileName
     * @return
     */
    public String uploadFile(InputStream inputStream, long size, String fileName) {
        StorePath path = fastFileStorageClient.uploadFile(inputStream, size, fileName, null);
        return getResAccessUrl(path);
    }

    /**
     * 将一段文本文件写到fastdfs的服务器上
     *
     * @param content
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath path = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
        return getResAccessUrl(path);
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     */
    public byte[] downloadFile(String fileUrl) {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        return fastFileStorageClient.downloadFile(group, path, downloadByteArray);
    }

    public void deleteFile(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 封装文件完整URL地址
     */
    private String getResAccessUrl(StorePath storePath) {
        return storePath.getFullPath();
    }
}