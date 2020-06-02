package cn.zysheep.springboot.controller;

import cn.zysheep.springboot.entity.Employee;
import cn.zysheep.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: EmplyeeController
 * @Author: 三月三
 */
@RestController
public class EmplyeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/emp/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id) {
        return employeeMapper.getEmployeeById(id);
    }

    @GetMapping("/emp")
    public Employee insertEmployee(Employee employee) {
        employeeMapper.insertEmployee(employee);
        return employee;
    }
}
