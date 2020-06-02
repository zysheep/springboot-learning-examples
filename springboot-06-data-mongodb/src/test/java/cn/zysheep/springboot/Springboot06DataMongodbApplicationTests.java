package cn.zysheep.springboot;

import cn.zysheep.springboot.entity.User;
import cn.zysheep.springboot.repository.UserRepository;
import cn.zysheep.springboot.utils.MongodbUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Springboot06DataMongodbApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongodbUtils mongodbUtils;

    // 测试添加文档
    @Test
    public void testSaveUser() throws Exception {
        User user=new User();
        user.setId(1l);
        user.setUserName("明世隐");
        user.setPassWord("123456");
        userRepository.saveUser(user);
    }


    // 测试根据名字查询文档
    @Test
    public void findUserByUserName(){
        User user= userRepository.findUserByUserName("明世隐");
        System.out.println("user is "+user);
    }

    // 测试修改文档
    @Test
    public void updateUser(){
        User user=new User();
        user.setId(2l);
        user.setUserName("韩信");
        user.setPassWord("123456789");
        userRepository.updateUser(user);
    }

    // 测试删除
    @Test
    public void deleteUserById(){
        userRepository.deleteUserById(1l);
    }


    @Test
    void test1(){
        List<User> user = (List<User>)MongodbUtils.findAll(User.class, "user");
        user.forEach(System.out::println);
    }


    @Test
    void test2(){
        MongodbUtils.saveOne("user",new User(5L,"干将莫邪","123123"));
    }


    @Test
    void test3(){
        ArrayList<User> list = new ArrayList<>();

        list.add(new User(6L,"荆轲","123123"));
        list.add(new User(7L,"赵云","12312"));
        list.add(new User(8L,"公孙离","asdfasdf"));
        list.add(new User(9L,"伽罗","hdfgsdfg"));

        MongodbUtils.saveAll("user",list);
    }

}
