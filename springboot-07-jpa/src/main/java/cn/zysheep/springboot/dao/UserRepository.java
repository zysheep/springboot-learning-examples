package cn.zysheep.springboot.dao;

import cn.zysheep.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UserRepository
 * @Author: 三月三
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);


}
