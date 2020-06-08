package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: User
 * @Author: 三月三
 */
// 注解都是lombok的，真的很方便
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}

