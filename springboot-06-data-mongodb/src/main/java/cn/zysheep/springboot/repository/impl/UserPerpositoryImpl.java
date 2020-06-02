package cn.zysheep.springboot.repository.impl;

import cn.zysheep.springboot.entity.User;
import cn.zysheep.springboot.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Component;


import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.*;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UserPerpositoryImpl
 * @Author: 三月三
 */
@Component
public class UserPerpositoryImpl implements UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

   /**
    * @author: 三月三
    * @description: 保存
    * @param: [user]
    * @return: void
    */
    @Override
    public void saveUser(User user) {
        mongoTemplate.insert(user);
    }

    /**
     * @author: 三月三
     * @description: 查找所有文档
     * @param: []
     * @return: void
     */
    @Override
    public void testFindAll() {
        List<User> all = mongoTemplate.findAll(User.class);
        all.forEach(System.out::println);
    }

    /**
     * @author: 三月三
     * @description: 根据名字查找
     * @param: [userName]
     * @return: cn.zysheep.springboot.entity.User
     */
    @Override
    public User findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        User user =  mongoTemplate.findOne(query , User.class);
        return user;
    }

    /**
     * @author: 三月三
     * @description: 修改文档记录
     * @param: [user]
     * @return: long
     */
    @Override
    public long updateUser(User user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return result.getMatchedCount();
        else
            return 0;
    }

    /**
     * @author: 三月三
     * @description: 根据id删除文档记录
     * @param: [id]
     * @return: void
     */
    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }

}
