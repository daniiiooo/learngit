package com.it.stock.mapper;

import com.it.stock.pojo.entity.SysLog;

/**
* @author 77519
* @description 针对表【sys_log(系统日志)】的数据库操作Mapper
* @createDate 2024-02-20 21:29:54
* @Entity com.it.stock.pojo.entity.SysLog
*/
public interface SysLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

}
