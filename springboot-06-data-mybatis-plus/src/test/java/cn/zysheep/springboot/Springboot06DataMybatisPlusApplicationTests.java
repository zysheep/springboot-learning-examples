package cn.zysheep.springboot;

import cn.zysheep.springboot.mapper.UserMapper;
import cn.zysheep.springboot.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Springboot06DataMybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * @author: 三月三
     * @description: 查询一条记录
     * @param: []
     * @return: void
     */
    @Test
    public void testSelectOne() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    /**
     * @author: 三月三
     * @description: 新增记录
     * @param: []
     * @return: void
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("zysheep");
        user.setAge(20);
        user.setEmail("zysheep@126.com");
        assertThat(userMapper.insert(user)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(user.getId()).isNotNull();
        //assertThat() 是 Assert 的一个精通方法，用来比对返回结果
    }

    /**
     * @author: 三月三
     * @description: 删除操作
     * @param: []
     * @return: void
     */
    @Test
    public void testDelete() {
        assertThat(userMapper.deleteById(3L)).isGreaterThan(0);
        assertThat(userMapper.delete(new QueryWrapper<User>()
                .lambda().eq(User::getName, "李白"))).isGreaterThan(0);
    }

    /**
     * @author: 三月三
     * @description: 修改操作
     * @param: []
     * @return: void
     */
    @Test
    public void testUpdate() {
        User user = userMapper.selectById(2);
        assertThat(user.getAge()).isEqualTo(36);
        assertThat(user.getName()).isEqualTo("keep");

        userMapper.update(
                null,
                Wrappers.<User>lambdaUpdate().set(User::getEmail, "123@123").eq(User::getId, 2)
        );
        assertThat(userMapper.selectById(2).getEmail()).isEqualTo("123@123");
    }
   /**
    * @author: 三月三
    * @description: 测试查询所有数据
    * @param: []
    * @return: void
    */
    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * @author: 三月三
     * @description: 条件查询
     * @param: []
     * @return: void
     */
    @Test
    public void testSelectCondition() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

    /**
     * @author: 三月三
     * @description: 测试非分页查询
     * @param: []
     * @return: void
     */
    @Test
    public void testPage() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
                .gt("age", 6));
        assertThat(page).isSameAs(userIPage);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
        System.out.println("----- baseMapper 自带分页 ------");
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }
}
