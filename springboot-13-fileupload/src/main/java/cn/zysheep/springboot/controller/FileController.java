package cn.zysheep.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: FileController
 * @Author: 三月三
 */
@Controller
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    /**
     * @author: 三月三
     * @description:  单个文件上传
     * @param: [file]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, Model model) {

        // 测试MultipartFile接口的各个方法
        System.out.println("文件类型ContentType=" + file.getContentType());
        System.out.println("文件组件名称Name=" + file.getName());
        System.out.println("文件原名称OriginalFileName=" + file.getOriginalFilename());
        System.out.println("文件大小Size=" + file.getSize()/1024 + "KB");
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }

            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);

            // 获取文件相对类路径
            String filePath = request.getServletContext().getRealPath("/");
            //文件绝对路径,项目中一般使用相对类路径,即使文件变更路径也会跟着变
            //String filePath = request.getServletContext().getRealPath("G:\\dev_workspace\\springboot-learning-examples\\springboot-13-fileupload\\src\\main\\resources\\static");
            System.out.println("path = " + filePath);
            //构造一个路径
            String newImg = UUID.randomUUID()+suffixName;
            String path = filePath + newImg;
            log.info("构造路径"+path);

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            model.addAttribute("msg","<font color=\"green\">上传成功</font>");
            model.addAttribute("img",newImg);
            return "upload";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("msg","<font color=\"green\">上传失败</font>");
        return "upload";
    }

    /**
     * @author: 三月三
     * @description: 多个文件上传
     * @param: [request]
     * @return: java.lang.String
     */
    @PostMapping("/batch")
    public String handleFileUpload(Model model,HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            //文件绝对路径,项目中一般使用相对类路径,即使文件变更路径也会跟着变
            String filePath = request.getServletContext().getRealPath("G:\\dev_workspace\\springboot-learning-examples\\springboot-13-fileupload\\src\\main\\resources\\static");
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;

                    model.addAttribute("msg", "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage());
                    return "upload";
                }
            } else {
                model.addAttribute("msg","第 " + i
                        + " 个文件上传失败因为文件为空");
                return "upload";
            }
        }
        model.addAttribute("msg","上传成功");
        return "upload";
    }

    /**
     * @author: 三月三
     * @description:  文件下载
     * @param: [model, request, response, fileName]
     * @return: java.lang.String
     */
    @PostMapping("/download")
    public String downloadFile(Model model,HttpServletRequest request, HttpServletResponse response,String fileName) {
        if (fileName != null) {
            //设置文件路径   真实环境是存放在数据库中的
           // String filePath = request.getServletContext().getRealPath("/");
            //文件绝对路径,项目中一般使用相对类路径,即使文件变更路径也会跟着变
            String filePath = request.getServletContext().getRealPath("G:\\dev_workspace\\springboot-learning-examples\\springboot-13-fileupload\\src\\main\\resources\\static");
            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);

                    bis = new BufferedInputStream(fis);
                    // 创建输出对象
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    model.addAttribute("msg","<font color=\"green\">下载成功</font>");
                    return "upload";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        model.addAttribute("msg","<font color=\"red\">下载失败</font>");
        return "upload";
    }
}
