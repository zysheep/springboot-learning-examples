package cn.zysheep.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Employee
 * @Author: 三月三
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee implements Serializable {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender; //性别 1男  0女
    private Integer dId;

}
