package cn.com.yxb.shiro.test.role;

import cn.com.yxb.shiro.test.BaseTest;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2017/9/19.
 *
 * @author frank.
 */
public class ShiroRoleTest extends BaseTest {


    /**
     * 测试隐式角色.
     */
    @Test
    public void testRole() {
        Subject subject = login("role/shiro-role.ini", "name", "password");

        subject.checkRole("role1");
        subject.checkRole("role2");
    }


    @Test
    public void testIsPermitted() {
        Subject subject = login("role/shiro-permission.ini", "name", "password");
        //判断拥有权限:user:create
        Assert.assertTrue(subject.isPermitted("user:create"));
        //判断拥有权限:user:update and user:delete
        Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
        //判断没有权限:user:view
        Assert.assertFalse(subject.isPermitted("user:view"));
    }


}
