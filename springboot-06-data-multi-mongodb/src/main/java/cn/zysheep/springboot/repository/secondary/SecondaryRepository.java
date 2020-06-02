package cn.zysheep.springboot.repository.secondary;


import cn.zysheep.springboot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String> {
}
