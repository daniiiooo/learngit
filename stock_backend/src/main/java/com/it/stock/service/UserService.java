package com.it.stock.service;

import com.it.stock.pojo.entity.SysUser;
import com.it.stock.vo.req.LoginReqVo;
import com.it.stock.vo.resp.LoginRespVo;
import com.it.stock.vo.resp.R;
import org.springframework.stereotype.Component;

import java.util.Map;


public interface UserService {
    SysUser findByName(String userName);

    //用户登录
    R<LoginRespVo> login(LoginReqVo vo);

    R<Map> getCaptchaCode();


}
