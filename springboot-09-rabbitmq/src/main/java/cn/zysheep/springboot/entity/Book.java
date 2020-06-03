package cn.zysheep.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Book
 * @Author: 三月三
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private String bookName;
    private String author;

}
