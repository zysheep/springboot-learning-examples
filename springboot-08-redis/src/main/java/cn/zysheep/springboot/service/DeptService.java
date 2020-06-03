package cn.zysheep.springboot.service;

import cn.zysheep.springboot.entity.Department;
import cn.zysheep.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: DeptService
 * @Author: 三月三
 */
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    CacheManager cacheManager;


    /**
     * 缓存的数据能存入redis；
     * 第二次从缓存中查询就不能反序列化回来；
     * 存的是dept的json数据;CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }

    // 使用缓存管理器得到缓存，进行api调用
    public Department getDeptByIdManager(Integer id) {
        System.out.println("查询部门" + id);
        Department department = departmentMapper.getDeptById(id);
        //获取某个缓存
        Cache cache = cacheManager.getCache("dept");
        // 手动储存数据到缓存中
        cache.put("dept::" + id, department);
        return department;
    }


}
