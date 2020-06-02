package cn.zysheep.springboot.repository.primary;


import cn.zysheep.springboot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PrimaryRepository extends MongoRepository<User, String> {
}
