package cn.zysheep.tk.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 *自己的 Mapper
 * @author ：三月三
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
