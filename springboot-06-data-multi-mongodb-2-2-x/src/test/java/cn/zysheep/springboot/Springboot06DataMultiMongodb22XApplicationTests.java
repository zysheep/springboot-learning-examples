package cn.zysheep.springboot;



import cn.zysheep.springboot.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;





@SpringBootTest
class Springboot06DataMultiMongodb22XApplicationTests {
    //这里用Resource，没有用Autoware
    @Resource
    private MongoTemplate primaryMongoTemplate;
    @Resource
    private MongoTemplate secondaryMongoTemplate;

   //右键运行，看看两个库是否同时插入了数据
    @Test
    public void insert(){
        Person p=new Person("001","tom",88);
        primaryMongoTemplate.insert(p);
        Person p2=new Person("N0012","zhangsan",14);
        secondaryMongoTemplate.insert(p2);
    }

    @Test
    public void queryon(){
        Person qp = primaryMongoTemplate.findOne(new Query(new Criteria("name").is("tom")), Person.class);
        System.out.println(qp);
    }

    @Test
    public void getOne(){
        //不指定数据集，根据实体类的类名获取数据集。 这里Person也会被当作collectionName
        Person zz = primaryMongoTemplate.findOne(new Query(new Criteria("name").is("tom")), Person.class);
        //指定数据集   同一个数据库下，建了一个user集合，跟person结构相同，这时就要指定了
        Person one = primaryMongoTemplate.findOne(new Query(new Criteria("name").is("tom")), Person.class,"primary");
        System.out.println(one);
    }



}
