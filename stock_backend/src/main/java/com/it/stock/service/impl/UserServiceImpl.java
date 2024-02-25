package com.it.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import com.it.stock.constant.StockConstant;
import com.it.stock.mapper.SysUserMapper;
import com.it.stock.pojo.entity.SysUser;
import com.it.stock.service.UserService;
import com.it.stock.utils.IdWorker;
import com.it.stock.vo.req.LoginReqVo;
import com.it.stock.vo.resp.LoginRespVo;
import com.it.stock.vo.resp.R;
import com.it.stock.vo.resp.ResponseCode;
import jdk.nashorn.internal.ir.CallNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userService")
@Slf4j  //
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public SysUser findByName(String userName) {
        return sysUserMapper.findUserInfoByUserName(userName);
    }

    //用户登录功能
    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        //判断参数是否合法
        if (vo==null || StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword())){
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //判断输入的验证码是否有效
        if(StringUtils.isBlank(vo.getCode()) || StringUtils.isBlank((vo.getSessionId()))){
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }
        //判断redis中保存的验证码和输入的验证码是否相同
        String redisCode = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + vo.getSessionId());
        if (StringUtils.isBlank(redisCode)) {
            return R.error(ResponseCode.CHECK_CODE_TIMEOUT);
        }
        if (!redisCode.equalsIgnoreCase(vo.getCode())) {
            return R.error(ResponseCode.CHECK_CODE_ERROR);
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

    //生成图片验证码功能
    @Override
    public R<Map> getCaptchaCode() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250,40,4,5);
        captcha.setBackground(Color.CYAN);//验证码图片背景色
        String checkCode = captcha.getCode();
        String imageData = captcha.getImageBase64();
        String sessionId = String.valueOf(idWorker.nextId());
        log.info("当前生成图片验证码:{}，会话id:{}",checkCode,sessionId);
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX + sessionId,checkCode,5, TimeUnit.MINUTES);
        Map<String,String> data = new HashMap();
        data.put("imageData",imageData);
        data.put("sessionId",sessionId);
        return R.ok(data);


    }
}

