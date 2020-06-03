package cn.zysheep.springboot;

import cn.zysheep.springboot.entity.Department;
import cn.zysheep.springboot.mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot08RedisApplicationTests {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Autowired
    private RedisTemplate redisTemplate;  //k-v都是对象的


    /**
     * Redis常见的五大数据类型
     * String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     * stringRedisTemplate.opsForValue()[String（字符串）]
     * stringRedisTemplate.opsForList()[List（列表）]
     * stringRedisTemplate.opsForSet()[Set（集合）]
     * stringRedisTemplate.opsForHash()[Hash（散列）]
     * stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    public void test01() {
        //给redis中保存数据
        stringRedisTemplate.opsForValue().set("string_msg","hello world redis!");
        String msg = stringRedisTemplate.opsForValue().get("string_msg");

        System.out.println(msg);

		stringRedisTemplate.opsForList().leftPush("mylist","1");
		stringRedisTemplate.opsForList().leftPush("mylist","2");
    }
    //测试保存对象
    @Test
    public void test02() {
        Department deptById = departmentMapper.getDeptById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("dep-01",deptById);
        //1、将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
        //myRedisTemplate.opsForValue().set("emp-02", empById);

    }

}
