package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;



@Mapper
public interface SysRoleDao {
	/**
	 * 查询记录总数
	 * @param(key)动态sql中使用此注解,后面的string name为value,存储在map底层中,在查询中去解析匹配sql语句中的条件
	 * @return
	 */
int getRowCount(@Param("name")String name);
List<SysRole> findPageObjects(@Param("name")String name,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
//根据id删除角色信息
int deleteObject(Integer id);
//添加角色
int insertObject(SysRole entity);//把封装的角色实体插入数据库

SysRoleMenuVo findobjectById(Integer id);
int updateObject(SysRole entity);

List<CheckBox> findObjects();
}
