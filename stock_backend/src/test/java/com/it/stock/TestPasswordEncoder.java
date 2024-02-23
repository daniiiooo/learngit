package com.it.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestPasswordEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test01(){
        String pwd = "123456";
        String encode = passwordEncoder.encode(pwd);
        System.out.println(encode);
    }

//    @Test
//    public void test02(){
//        String pwd = "123456";
//        String enPwd = "$2a$10$ZEGGDX0jciBsfzBfnXLnGeiFah6BWOeIjoh8H7CD/npSgMcoqvMBq";
//        boolean isSuccess = passwordEncoder.matches(pwd,enPwd);
//        System.out.println(isSuccess?"密码匹配成功":"密码匹配失败");
//    }
}
