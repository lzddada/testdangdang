package com.cy.pj.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.service.SysUserService;

@RestController
@RequestMapping("/user/")
public class SysUserDeptController {
	@Autowired
	private SysUserService sysUserDeptService;
	@Autowired
	private SysRoleService sysRoleService;
	

	
@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		return new JsonResult( sysUserDeptService.findPageObjects(username, pageCurrent));
	}
@RequestMapping("doValidById")
public JsonResult doValidById(Integer id,Integer valid,String modifiedUser) {
	sysUserDeptService.validById(id, valid,"admin");
	return new JsonResult("update ok");
}
@RequestMapping("doSaveObject")

public JsonResult doSaveObject(
		SysUser entity,
		Integer[] roleIds){
	sysUserDeptService.saveObject(entity,roleIds);
	return new JsonResult("save ok");
}
@RequestMapping("doFindObjectById")

public JsonResult doFindObjectById(
		Integer id){
	Map<String,Object> map=
	sysRoleService.findObjectById(id);
	return new JsonResult(map);
}
@RequestMapping("doUpdateObject")
public JsonResult doUpdateObject(
    SysUser entity,Integer[] roleIds){
	sysRoleService.updateObject(entity,
			roleIds);
	return new JsonResult("update ok");
}
}
