package cn.zysheep.springboot.repository;


import cn.zysheep.springboot.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: BookRepository
 * @Author: 三月三
 * 使用Spring Data 的连接方式
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

    //参照
    // https://docs.spring.io/spring-data/elasticsearch/docs/3.0.6.RELEASE/reference/html/
    public List<Book> findByBookNameLike(String bookName);

}
