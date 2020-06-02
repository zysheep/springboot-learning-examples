package cn.zysheep.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: JdbcUtils
 * @Author: 三月三
 */
public class JdbcUtils {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate = null;

    public synchronized  JdbcTemplate getJdbcTemplate(){
        if(jdbcTemplate==null){
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }


    /**
     * @author: 三月三
     * @description: 返回List<Map<String, Object>> 结果集
     * @param: [sql]
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public  List<Map<String, Object>> gtQueryForListMap(String sql){
        List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
        try {
            list = this.getJdbcTemplate().queryForList(sql);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }


    /**
     * @author: 三月三
     * @description: 返回一条MAP数据
     * @param: [sql]
     * @return: java.util.Map
     */
    @SuppressWarnings("rawtypes")
    public Map getMap(String sql){
        List<Map<String, Object>> list = this.gtQueryForListMap(sql);
        Map   map = new HashMap();
        if(list!=null&&list.size()>0){
            map=list.get(0);
        }
        return map;
    }

    /**
     * @author: 三月三
     * @description:  修改语句
     * @param: [sql]
     * @return: void
     */
    public void update(String sql){
        try {
            this.getJdbcTemplate().execute(sql);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /**
     * @author: 三月三
     * @description: 返回记录数
     * @param: [sql]
     * @return: int
     */
    @SuppressWarnings("deprecation")
    public int count(String sql){
        int count=0;
        try {
            count = this.getJdbcTemplate().getMaxRows();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return count;
    }

    /**
     * @author: 三月三
     * @description: 删除记录数
     * @param: [sql]
     * @return: void
     */
    public void delete(String sql) {
        try {
            this.getJdbcTemplate().execute(sql);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

   /**
    * @author: 三月三
    * @description: 添加记录数
    * @param: [sql]
    * @return: void
    */
    public void insert(String sql) {
        try {
            this.getJdbcTemplate().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据用户id获取用户姓名
     */
    public String userName(String userId) {
        String sql =" select  jj.user_name from sys_user  jj  where jj.id="+userId;
        return this.getMapValue(sql);
    }

    /**
     * @author: 三月三
     * @description: 只返回一Map里的一个个结果集
     * @param: [sql]
     * @return: java.lang.String
     */
    @SuppressWarnings("unchecked")
    public String getMapValue(String sql){
        Object data=null;
        Map<String, Object> map =this.getMap(sql);
        if(!map.isEmpty()){
            for(Map.Entry<String, Object> entry: map.entrySet()) {
                data=entry.getValue();
            }

        }
        if(data!=null&&data!=""){
            return data.toString();
        }
        return null;
    }
}
