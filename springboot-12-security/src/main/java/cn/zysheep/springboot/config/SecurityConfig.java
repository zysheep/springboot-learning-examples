package cn.zysheep.springboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: SecurityConfig
 * @Author: 三月三
 */
@EnableWebSecurity //开启WebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()  // 首页所有人可以访问
                .antMatchers("/level1/**").hasRole("vip1")  // 功能页只有对应的权限可以访问
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        // /login?error 重定向到这里表示登录失败
        http.formLogin().loginPage("/tologin") //配置自己的登入页面
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login");  // 登陆表单提交请求

        // 开启自动配置的注销的功能
        // 默认为/logout 注销请求
        http.logout().deleteCookies("remove").invalidateHttpSession(true).logoutSuccessUrl("/");//注销成功以后来到首页

        http.csrf().disable(); // 关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求

        // 开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
        // 登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        // 点击注销会删除cookie
    }


    // 定义认证规则
    // 在内存中定义，也可以在jdbc中去拿,或者在xml中定义
    // Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
        // spring security 官方推荐的是使用bcrypt加密方式。
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").password(new BCryptPasswordEncoder().encode("root"))
                .roles("vip1", "vip2", "vip3")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip3");
    }
}

