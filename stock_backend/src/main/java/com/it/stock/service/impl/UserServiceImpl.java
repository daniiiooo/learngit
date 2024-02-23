package com.it.stock.service.impl;

import com.it.stock.mapper.SysUserMapper;
import com.it.stock.pojo.entity.SysUser;
import com.it.stock.service.UserService;
import com.it.stock.vo.req.LoginReqVo;
import com.it.stock.vo.resp.LoginRespVo;
import com.it.stock.vo.resp.R;
import com.it.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser findByName(String userName) {
        return sysUserMapper.findUserInfoByUserName(userName);
    }

    //用户登录功能
    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        if (vo==null || StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword())){
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //根据用户名查询用户信息
        SysUser user=this.sysUserMapper.findUserInfoByUserName(vo.getUsername());
        //判断用户是否存在，存在则密码校验比对
        if (user==null ){
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        if(!passwordEncoder.matches(vo.getPassword(),user.getPassword())){
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //组装登录成功数据
        LoginRespVo respVo = new LoginRespVo();
        //属性名称与类型必须相同，否则属性值无法copy
        BeanUtils.copyProperties(user,respVo);
        return  R.ok(respVo);
    }

    }

