package cn.zysheep.springboot.config;

import cn.zysheep.springboot.entity.User;
import cn.zysheep.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UserRealm
 * @Author: 三月三
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行了==>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");

        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User userPrincipal = (User) subject.getPrincipal(); //拿到User对象

        info.addStringPermission(userPrincipal.getPerms()); //设置当前用户的权限



        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了==>认证doGetAuthenticationInfo");
        //用户名, 密码  数据库中取
//        String name = "root";
//        String pwd = "123456";
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //连接数据库
        //通过username从数据库中查找 User对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findUserById(userToken.getUsername());

        if (user==null){
            return null;
        }
        //MD5加密:
        //密码认证,shiro
        //用户需要提供 principals （身份）和 credentials（证明）给 shiro，从而应用能验证用户身份：
        // principals： 身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。一个主体可以有多个 principals，但只有一个   //Primaryprincipals，一般是用户名 / 密码 / 手机号。
        //credentials： 证明 / 凭证，即只有主体知道的安全值，如密码 / 数字证书等。
        //最常见的 principals 和 credentials 组合就是用户名 / 密码了。接下来先进行一个基本的身份认证。
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
