package cn.zysheep.springboot;

import cn.zysheep.springboot.entity.User;
import cn.zysheep.springboot.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@SpringBootTest
class Springboot06DataTkmybatisApplicationTests {


    /**
     * 注入数据查询接口
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 测试插入数据
     */
    @Test
    public void testInsert() {

        User user = new User();
        user.setName("zysheep");
        user.setAge(20);
        user.setId(10L);
        user.setEmail("topsale@vip.qq.com");
        userMapper.insert(user);
    }

    /**
     * 测试删除数据
     */
    @Test
    public void testDelete() {
        // 构造条件，等同于 DELETE from tb_user WHERE username = 'zysheep'
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", "zysheep");
        userMapper.deleteByExample(example);
    }

    /**
     * 测试修改数据
     */
    @Test
    public void testUpdate() {
        // 构造条件
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", "zysheep");
        // 构造一条测试数据
        User user = new User();
        user.setAge(12);
        user.setName("三月三");
        user.setEmail("zysheep@126.com");
        // 修改数据
        userMapper.updateByExample(user, example);
    }


    /**
     * 测试查询集合
     */
    @Test
    public void testSelect() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testPage() {
        // PageHelper 使用非常简单，只需要设置页码和每页显示笔数即可
        PageHelper.startPage(0, 2);

        // 设置分页查询条件
        Example example = new Example(User.class);
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByExample(example));

        // 获取查询结果
        List<User> users = pageInfo.getList();
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

}
