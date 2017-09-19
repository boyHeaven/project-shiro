package cn.com.yxb.shiro.test.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2017/9/19.
 *
 * @author frank.
 */
public class ShiroAuthTest {

    @Test
    public void testIniShiro() {

        Subject subject = login("auth/shiro.ini");
        // 断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());

        // 登出
        subject.logout();
    }

    @Test
    public void testMyRealm() {

        Subject subject = login("auth/shiro-realm.ini");

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();

    }

    @Test
    public void testJDBCRealm() {

        Subject subject = login("auth/shiro-jdbc-realm.ini");

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

    @Test
    public void testAllSuccess() {

        Subject subject = login("auth/shiro-authenticator-all-success.ini");

        PrincipalCollection principalCollection = subject.getPrincipals();

        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test(expected = UnknownAccountException.class)
    public void testFail() {
        Subject subject = login("auth/shiro-authenticator-fial.ini");
    }

    private Subject login(String configFile) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:" + configFile);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("name", "password");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (UnknownAccountException e) {
            //5、身份验证失败
            throw e;
        }

        return subject;

    }

    @After
    public void tearDown() throws Exception {

        //退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }

}
