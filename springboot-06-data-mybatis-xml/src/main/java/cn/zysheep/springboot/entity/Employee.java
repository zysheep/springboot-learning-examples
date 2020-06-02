package cn.zysheep.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Employee
 * @Author: 三月三
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;
    private Integer gender;
    private String email;
    private Integer dId;
}