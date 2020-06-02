package cn.zysheep.springboot.controller;

import cn.zysheep.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: MainController
 * @Author: 三月三
 */
@Controller
public class MainController {

    @RequestMapping(value = {"/","thymeleaf/index"},method = RequestMethod.GET)
    public String index(Model model){
        User user = new User();
        user.setUsername("zysheep");
        user.setAge(20);
        model.addAttribute("user",user);

        return "index";
    }
}

