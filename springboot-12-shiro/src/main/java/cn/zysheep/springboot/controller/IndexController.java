package cn.zysheep.springboot.controller;

import cn.zysheep.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: IndexController
 * @Author: 三月三
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping({"/","/index"})
    public String  index(Model model){
        model.addAttribute("msg","Shrio Hello world!");
        return "index";
    }

    @GetMapping("/user/add")
    public String  add(Model model){
        return "user/add";
    }

    @GetMapping("/logout")
    public String  logout(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println(currentUser.getPrincipal());
        currentUser.logout();
        return "index";
    }

    @GetMapping("/user/update")
    public String  update(Model model){

        return "user/update";
    }

    @GetMapping("/tologin")
    public String  tologin(Model model){

        return "login";
    }


    @PostMapping("/login")
    public String  login(String username ,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);//执行登录方法.如果没有异常就说明成功了
            return "index";
        }catch (UnknownAccountException e){//用户名不存在
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){//密码不存在
            model.addAttribute("msg","用户名错误");
            return "login";
        }


    }

    @GetMapping("/uazd")
    @ResponseBody
    public String  Unauthorized(Model model){
        return "未授权";
    }
}
