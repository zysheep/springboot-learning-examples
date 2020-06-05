package cn.zysheep.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UploadController
 * @Author: 三月三
 */
@Controller
public class UploadController {

    @GetMapping("/")
    public String index() {
        return "upload";
    }

}
