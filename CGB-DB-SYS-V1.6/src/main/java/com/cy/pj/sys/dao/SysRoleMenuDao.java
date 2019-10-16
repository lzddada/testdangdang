package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
int deleteObejctsByMenuId(Integer menuId);//基于菜单id删除记录

//基于id删除角色和菜单的关系数据
int deleteObjectsByRoleId(Integer roleId);
//2）将角色与菜单的关系数据保存到数据库
int insertObjects(
		@Param("roleId")Integer roleId,
		@Param("menuIds")Integer[] menuIds);
}
