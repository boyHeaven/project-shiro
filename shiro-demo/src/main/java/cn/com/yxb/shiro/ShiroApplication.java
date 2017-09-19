package cn.com.yxb.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2017/9/18.
 *
 * @author frank.
 */
@SpringBootApplication
@ComponentScan("cn.com.yxb")
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class);
    }
}
