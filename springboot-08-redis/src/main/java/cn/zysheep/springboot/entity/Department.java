package cn.zysheep.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Department
 * @Author: 三月三
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department implements Serializable {
    private Integer id;
    private String departmentName;
}
