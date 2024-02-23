package com.it.stock.mapper;

import com.it.stock.pojo.entity.SysRolePermission;

/**
* @author 77519
* @description 针对表【sys_role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2024-02-20 21:29:54
* @Entity com.it.stock.pojo.entity.SysRolePermission
*/
public interface SysRolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

}
