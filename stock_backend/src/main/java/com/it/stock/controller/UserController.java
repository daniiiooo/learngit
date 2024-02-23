package com.it.stock.controller;

import com.it.stock.pojo.entity.SysUser;
import com.it.stock.service.UserService;
import com.it.stock.vo.req.LoginReqVo;
import com.it.stock.vo.resp.LoginRespVo;
import com.it.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//定义web层接口资源bean
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    //根据用户名称查询用户信息
    @GetMapping("/user/{userName}")
    public SysUser getUserByUserName(@PathVariable("userName") String name){
        return userService.findByName(name);

    }
    //用户登录功能
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){

        return userService.login(vo);
    }
}
