package cn.zysheep.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: HelloController
 * @Author: 三月三
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "你好");
        return "success";
    }
}
