package cn.zysheep.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @version v1.0.0
 * @ProjectName: springboot-examples
 * @ClassName: IndexController
 * @Author: 三月三
 */
@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
}
