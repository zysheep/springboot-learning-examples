package cn.zysheep.springboot;

import cn.zysheep.springboot.mapper.UserMapper;
import cn.zysheep.springboot.model.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Springboot06DataMybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

   /////////////////////////////新增操作//////////////////////////////////


    /**
     * @author: 三月三
     * @description: 新增记录
     * @param: []
     * @return: void
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("孙尚香");
        user.setAge(20);
        user.setEmail("zysheep@126.com");
        assertThat(userMapper.insert(user)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(user.getId()).isNotNull();
        //assertThat() 是 Assert 的一个精通方法，用来比对返回结果
    }


    /////////////////////////////删除操作//////////////////////////////////
    /**
     * @author: 三月三
     * @description: deleteById
     * @param: []
     * @return: void
     */
    @Test
    public void testDeleteById() {
        //执行删除操作
        int result = this.userMapper.deleteById(2L);
        System.out.println("result = " + result);
    }

    /**
     * @author: 三月三
     * @description: delete
     * @param: []
     * @return: void
     */
    @Test public void testDeleteByMap() {
        User user = new User();
        user.setAge(20);
        user.setName("李白");
        //将实体对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int result = this.userMapper.delete(wrapper);
        System.out.println("result = " + result);
    }

    /**
     * @author: 三月三
     * @description: deleteBatchIds
     * @param: []
     * @return: void
     */
    @Test
    public void testDeleteByIds() {
        //根据id集合批量删除
        int result = this.userMapper.deleteBatchIds(Arrays.asList(1L,10L,20L));
        System.out.println("result = " + result);
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


    /////////////////////////////修改操作//////////////////////////////////

    /**
     * @author: 三月三
     * @description: 修改操作
     * @param: []
     * @return: void
     */
    @Test public void testUpdate() {
        User user = new User();
        user.setAge(22); //更新的字段
        //更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "孙尚香");
        //可以设置多个条件...

        //执行更新操作
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result = " + result);
    }
    //UpdateWrapper进行更新，将email字段更新为NULL.
    @Test public void testUpdate2() {
        //更新的条件以及字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 4).set("age", 23).set("email",null);
        //执行更新操作
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result = " + result);
    }


    /////////////////////////////查询操作//////////////////////////////////
    /**
     * @author: 三月三
     * @description: selectById
     * @param: []
     * @return: void
     */
    @Test
    public void testSelectById() {
        //根据id查询数据
        User user = this.userMapper.selectById(2L);
        System.out.println("result = " + user);
    }

    /**
     * @author: 三月三
     * @description: testSelectBatchIds
     * @param: []
     * @return: void
     */
    @Test public void testSelectBatchIds() {
        //根据id集合批量查询
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * @author: 三月三
     * @description: testSelectOne
     * @param: []
     * @return: void
     */
    @Test public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("name", "李白");
        //根据条件查询一条数据，如果结果超过一条会报错
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    /**
     * @author: 三月三
     * @description: selectCount
     * @param: []
     * @return: void
     */
    @Test public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 20);
        //年龄大于23岁
        // 根据条件查询数据条数
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }

    /**
     * @author: 三月三
     * @description: selectList
     * @param: []
     * @return: void
     */
    @Test public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
         wrapper.gt("age", 20);
         //年龄大于23岁
        // 根据条件查询数据
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }


    /**
     * @author: 三月三
     * @description: 分页查询
     * @param: []
     * @return: void
     */
    @Test public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 20);
        //年龄大于20岁
        // 参数1：当前页码，小于1的按1算
        // 参数2：每页记录数
        Page<User> page = new Page<>(1,2);
        //根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("总页数：" + iPage.getPages());
        //取出分页记录
        List<User> users = iPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     * @author: 三月三
     * @description: 查询结果显示指定的内容
     * @param: []
     * @return: void
     */
    @Test public void testWrapperSelectValue() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,name,age FROM user WHERE name = ? OR age = ?
        wrapper.eq("name", "李白") .or() .eq("age", 24) .select("id", "name", "age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }



    /////////////////////////////条件构造器 测试查询//////////////////////////////////

    /**
     * @author: 三月三
     * @description: 构造条件查询
     * @param: []
     * @return: void
     */
    @Test public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE  age >= ? AND name IN (?,?,?)
        wrapper .ge("age", 20) .in("name", "李白", "韩信", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * @author: 三月三
     * @description: Lambda方式构造条件：
     * @param: []
     * @return: void
     */
    @Test public void testLombokEq() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE  age >= ? AND name IN (?,?,?)
        wrapper .ge(User::getAge, 20) .in(User::getName, "李白", "韩信", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    /////////////////////////////模糊查询  like //////////////////////////////////

    /**
     * @author: 三月三
     * @description: 模糊查询like
     * @param: []
     * @return: void
     */
    @Test public void testWrapperLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM user WHERE name LIKE ?
        // Parameters: %五%(String)
        wrapper.like("name", "白");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /////////////////////////////逻辑查询  or  and//////////////////////////////////
    @Test public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM user WHERE name = ? OR age = ?
        wrapper.eq("name","李白").or().eq("age", 24);
        //变为and方式 wrapper.eq("name","李四").eq("age", 24)
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /////////////////////////////排序  orderBy //////////////////////////////////

    @Test public void testWrapperOrderBy () {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,password,name,age,email FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
