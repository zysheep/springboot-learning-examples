package cn.zysheep.springboot.service;


import cn.zysheep.springboot.entity.Employee;
import cn.zysheep.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: EmployeeService
 * @Author: 三月三
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    //@Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存， 如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；
    @Cacheable(value = {"emp"}/*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    //@CachePut：既调用方法，又更新缓存数据；同步更新缓存 修改了数据库的某个数据，同时更新缓存；
    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }


    //beforeInvocation = true：代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
    @CacheEvict(value = "emp", beforeInvocation = true/*key = "#id",*/)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:" + id);
        employeeMapper.deleteEmpById(id);
        int i = 10 / 0;
    }

    //@Caching 定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(value="emp",key = "#lastName")
            },
            put = {
                    @CachePut(value="emp",key = "#result.id"),
                    @CachePut(value="emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }


}

