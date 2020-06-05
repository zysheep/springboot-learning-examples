package cn.zysheep.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: RouterController
 * @Author: 三月三
 */
@Controller
public class RouterController {

    @GetMapping({"/", "index"})
    public String index() {
        return "index";
    }

    @GetMapping("/tologin")
    public String login() {
        return "views/login";
    }

    @GetMapping("/level1/{id}")
    public String level1(@PathVariable(value = "id") Integer id) {
        return "views/level1/" + id;
    }

    @GetMapping("/level2/{id}")
    public String level2(@PathVariable(value = "id") Integer id) {
        return "views/level2/" + id;
    }

    @GetMapping("/level3/{id}")
    public String level3(@PathVariable(value = "id") Integer id) {
        return "views/level3/" + id;
    }

}

