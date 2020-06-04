package cn.zysheep.springboot.entity;

import io.searchbox.annotations.JestId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Article
 * @Author: 三月三
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
    @JestId
    private Integer id;
    private String author;
    private String title;
    private String content;
}
