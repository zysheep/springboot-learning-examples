package cn.zysheep.springboot.controller;

import cn.zysheep.springboot.entity.Department;
import cn.zysheep.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: DeptController
 * @Author: 三月三
 */
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id) {
        return deptService.getDeptById(id);
    }


    @GetMapping("/depts/{id}")
    public Department getDepts(@PathVariable("id") Integer id) {
        return deptService.getDeptByIdManager(id);
    }
}
