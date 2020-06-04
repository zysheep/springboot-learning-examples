package cn.zysheep.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: User
 * @Author: 三月三
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
    private String name;
    private Integer age;
}
