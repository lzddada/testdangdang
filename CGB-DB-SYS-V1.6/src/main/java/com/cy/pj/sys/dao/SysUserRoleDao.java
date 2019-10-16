package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysUserRoleDao {
	
	//基于id删除角色和用户的关系数据
	  int deleteObjectsByRoleId(Integer roleId);
	  /**
		 * 负责将用户与角色的关系数据写入到数据库
		 * @param userId 用户id
		 * @param roleIds 多个角色id
		 * @return
		 */
		int insertObjects(
				@Param("userId")Integer userId,
				@Param("roleIds")Integer[]roleIds);
/**
 *根据用户id查找用户对应的角色ids
 * @param id
 * @return
 */
		List<Integer> findRoleIdsByUserId(Integer id);
		/**
		 * 删除用户信息便于跟新操作
		 * @param userId
		 * @return
		 */
		int deleteObjectsByUserId(Integer userId);
}
