package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	/**
     * 本方法中要分页查询角色信息,并查询角色总记录数据
     * @param pageCurrent 当表要查询的当前页的页码值
     * @return 封装当前实体数据以及分页信息
     */
	 PageObject<SysRole> findPageObjects(
			 String name,Integer pageCurrent);
	 
	//根据id删除角色信息
	 int deleteObject(Integer id);
	 //添加角色
	 int insertObject(SysRole entity,Integer[] menuIds);
	 
	 SysRoleMenuVo findobjectById(Integer id);
	 int updateObject(SysRole entity,Integer[] menuIds);
	 List<CheckBox> findObjects();
	 
	 Map<String, Object> findObjectById(Integer userId);
	 
	 int updateObject(SysUser entity,Integer[] roleIds);
}
