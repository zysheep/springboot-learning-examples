package cn.zysheep.springboot.mapper;

import cn.zysheep.springboot.entity.Employee;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: EmployeeMapper
 * @Author: 三月三
 */
public interface EmployeeMapper {
    public Employee getEmployeeById(int id);

    public int insertEmployee(Employee employee);
}
