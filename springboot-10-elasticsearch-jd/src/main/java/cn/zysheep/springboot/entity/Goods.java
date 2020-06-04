package cn.zysheep.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * @version v1.0.0
 * @ProjectName: springboot-examples
 * @ClassName: Goods
 * @Author: 三月三
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Goods {
    private String img;
    private String name;
    private String price;
}

