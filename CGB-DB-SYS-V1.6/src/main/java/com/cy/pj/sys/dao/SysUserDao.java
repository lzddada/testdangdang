package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;



@Mapper
public interface SysUserDao {
	
/**
 * 分页查询所有用户信息
 * @param username
 * @param startIndex
 * @param pageSize
 * @return
 */
List<SysUserDeptVo> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

	@Select("select count(*) from sys_users")
	int getRowCount(@Param("username")String username);
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	/**
	 * 负责将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
	/**
	 * 查询用户自身信息+对应的部门信息(封装到此对象中SysUserDeptVo)
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	/**
	 * 更新用户信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
}
