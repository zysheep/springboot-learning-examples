package cn.zysheep.springboot.controller;

import cn.zysheep.springboot.utils.FastDfsUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: TestController
 * @desc
 * @Author: 三月三
 */
@Controller
public class TestController {

    @Autowired
    private FastDfsUtils dfsUtils;

    @Value("${fdfs.addr}")
    private String serverPath;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/upload1")
    @ResponseBody
    public String uploadFile(MultipartFile file, Map map) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = file.getName();
        long fileSize = file.getSize();
        System.out.println(originalFileName + "==========" + fileName + "===========" + fileSize + "===============" + extension + "====" + bytes.length);
        String newImg = dfsUtils.uploadFile(bytes, fileSize, extension);
       // map.put("newImg",newImg);
        return newImg;
    }

    @RequestMapping("/download")
    public void downloadFile(String fileUrl, HttpServletResponse response) throws IOException {
        byte[] bytes = dfsUtils.downloadFile(fileUrl);

        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        // 文件扩展名为.*（ 二进制流，不知道下载文件类型）设置为application/octet-stream
        response.setHeader("content-type", "application/octet-stream");
        // 这里只是为了整合fastdfs，所以写死了文件格式。需要在上传的时候保存文件名。下载的时候使用对应的格式
        // 告诉浏览器以下载对方式打开
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = null;

        try {

            // 创建输出对象
            outputStream = response.getOutputStream();
            outputStream.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传文件
     */
    @PostMapping(value = "/upload2")
    public String upload(MultipartFile file) throws Exception {
        return dfsUtils.uploadFile(file);
    }
}