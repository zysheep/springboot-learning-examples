package cn.zysheep.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Book
 * @Author: 三月三
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "zysheep", type = "news")
public class Book {
    private Integer id;
    private String bookName;
    private String author;
}
