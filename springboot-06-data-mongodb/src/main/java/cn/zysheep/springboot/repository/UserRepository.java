package cn.zysheep.springboot.repository;

import cn.zysheep.springboot.entity.User;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UserRepository
 * @Author: 三月三
 */
public interface UserRepository {

    public void saveUser(User user);

    public void testFindAll() ;

    public User findUserByUserName(String userName);

    public long updateUser(User user);

    public void deleteUserById(Long id);


}
