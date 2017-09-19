package cn.com.yxb.shiro.test.auth;

import cn.com.yxb.shiro.test.BaseTest;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2017/9/19.
 *
 * @author frank.
 */
public class ShiroAuthTest extends BaseTest {

    @Test
    public void testIniShiro() {

        Subject subject = login("auth/shiro.ini", "name", "password");
        // 断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());

        // 登出
        subject.logout();
    }

    @Test
    public void testMyRealm() {

        Subject subject = login("auth/shiro-realm.ini", "name", "password");

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();

    }

    @Test
    public void testJDBCRealm() {

        Subject subject = login("auth/shiro-jdbc-realm.ini", "name", "password");

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

    @Test
    public void testAllSuccess() {

        Subject subject = login("auth/shiro-authenticator-all-success.ini", "name", "password");

        PrincipalCollection principalCollection = subject.getPrincipals();

        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test(expected = UnknownAccountException.class)
    public void testFail() {
        Subject subject = login("auth/shiro-authenticator-fial.ini", "name", "password");
    }


}
