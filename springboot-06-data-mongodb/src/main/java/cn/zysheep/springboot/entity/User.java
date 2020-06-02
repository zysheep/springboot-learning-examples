package cn.zysheep.springboot.entity;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String passWord;
}
