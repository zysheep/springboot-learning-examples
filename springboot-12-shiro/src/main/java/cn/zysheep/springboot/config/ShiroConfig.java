package cn.zysheep.springboot.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: ShiroConfig
 * @Author: 三月三
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFatoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
            /*
                anon:无需认证就可以访问
                authc:必须认证了才能访问
                user:必须拥有记住我 功能才能用
                perms: 拥有对某个资源的权限才能访问
                role: 拥有某个角色权限才能访问
            * */
        //登录拦截
        Map<String, String> filterMap = new LinkedHashMap<>();



        // filterMap.put("/user/*","authc");

        //授权,正常的情况下,没有授权会跳转到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");




        bean.setFilterChainDefinitionMap(filterMap);
        //添加未认证访问
        bean.setLoginUrl("/tologin");
        //添加未授权访问
        bean.setUnauthorizedUrl("/uazd");

        return   bean;
    }
    //DefaultWebSecurityManager:2
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }


    //创建realm对象  需要自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}