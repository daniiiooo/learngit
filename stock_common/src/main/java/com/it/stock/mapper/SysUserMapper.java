package com.it.stock.mapper;

import com.it.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* @author 77519
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-02-20 21:29:54
* @Entity com.it.stock.pojo.entity.SysUser
*/
@Component
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findUserInfoByUserName(@Param("userName") String userName);

}
