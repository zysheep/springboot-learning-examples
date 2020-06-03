package cn.zysheep.springboot.mapper;

import cn.zysheep.springboot.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: DepartmentMapper
 * @Author: 三月三
 */
@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);
}

