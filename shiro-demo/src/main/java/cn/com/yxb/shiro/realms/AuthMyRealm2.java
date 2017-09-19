package cn.com.yxb.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created on 2017/9/19.
 *
 * @author frank.
 */
public class AuthMyRealm2 implements Realm {

    @Override
    public String getName() {
        return "authMyRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        if (!"name".equals(userName)) {
            throw new UnknownAccountException("对不起，账户输入错误。");
        }

        if (!"password".equals(password)) {
            throw new IncorrectCredentialsException("对不起，密码输入错误。");
        }

        return new SimpleAuthenticationInfo(userName + "@163.com", password, getName());
    }
}
