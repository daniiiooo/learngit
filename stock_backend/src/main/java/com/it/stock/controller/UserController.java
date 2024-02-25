package com.it.stock.controller;

import com.it.stock.pojo.entity.SysUser;
import com.it.stock.service.UserService;
import com.it.stock.vo.req.LoginReqVo;
import com.it.stock.vo.resp.LoginRespVo;
import com.it.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//定义web层接口资源bean
@RestController
@RequestMapping("/api")
@Api(tags="用户相关处理接口")
public class UserController {
    @Autowired
    private UserService userService;
    //根据用户名称查询用户信息
    @ApiOperation(value="根据用户名查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="用户名",dataType="string",required=true,type="path")
    })
    @GetMapping("/user/{userName}")
    public SysUser getUserByUserName(@PathVariable("userName") String name){
        return userService.findByName(name);

    }
    //用户登录功能
    @ApiOperation(value="用户登录功能")
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){
        return userService.login(vo);
    }
    //生成图片验证码功能
    @ApiOperation("验证码生成")
    @GetMapping("/captcha")
    public R<Map> getCaptchaCode(){
        return userService.getCaptchaCode();
    }
}
