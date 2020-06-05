package cn.zysheep.springboot.mapper;

import cn.zysheep.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    User findUserById(String  username);
}
