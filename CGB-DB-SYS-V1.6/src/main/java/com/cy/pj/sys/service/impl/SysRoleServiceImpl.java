package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;
import com.cy.pj.sys.vo.SysUserDeptVo;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class SysRoleServiceImpl implements SysRoleService{
@Autowired
private SysRoleDao sysRoleDao;
@Autowired
private SysRoleMenuDao sysRoleMenuDao;
@Autowired
private SysUserRoleDao sysUserRoleDao;
@Autowired
private SysUserDao sysUserDao;

@Override
public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
	if (pageCurrent==null||pageCurrent<1)throw new IllegalArgumentException("当前页码不正确");
	int rowCount = sysRoleDao.getRowCount(name);
	if (rowCount==0)throw new ServiceException("记录不存在");
	int pageSize=2;
	int startIndex=(pageCurrent-1)*pageSize;
	List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
	PageObject<SysRole> pageObject = new PageObject<>(pageCurrent, pageSize, rowCount, records);
	return pageObject;
}

@Override
public int deleteObject(Integer id) {
	if (id==null||id<1)throw new ServiceException("id的值不正确,id="+id);
	sysRoleMenuDao.deleteObjectsByRoleId(id);
	sysUserRoleDao.deleteObjectsByRoleId(id);
	int rows = sysRoleDao.deleteObject(id);
	if (rows==0)throw new ServiceException("数据可能已经不存在");
	return rows;
}

@Override
public int insertObject(SysRole entity,Integer[] menuIds) {
	if (entity==null) throw new ServiceException("保存数据不能为空");//保存数据对象校验
	if (StringUtils.isEmpty(entity.getName()))throw new ServiceException("角色名不能为空");//保存数据对象属性数据校验
	if (menuIds==null||menuIds.length==0) throw new ServiceException("必须为角色赋予权限");//校验角色的访问权限,权限不能没有
	int rows = sysRoleDao.insertObject(entity);//保存角色自身信息
	sysRoleMenuDao.insertObjects(entity.getId(), menuIds);//保存角色和菜单的关系信息
	return rows;
}

@Override
public SysRoleMenuVo findobjectById(Integer id) {
	if(id==null||id<=0)
    	throw new ServiceException("id的值不合法");
	SysRoleMenuVo result = sysRoleDao.findobjectById(id);
 	if(result==null)
    	throw new ServiceException("此记录已经不存在");
	return result;
}

@Override
public int updateObject(SysRole entity,Integer[] menuIds) {
	//1.合法性验证
	if(entity==null)
throw new ServiceException("更新的对象不能为空");
	if(entity.getId()==null)
	throw new ServiceException("id的值不能为空");
	
	if(StringUtils.isEmpty(entity.getName()))
	throw new ServiceException("角色名不能为空");
	if(menuIds==null||menuIds.length==0)
	throw new ServiceException("必须为角色指定一个权限");
	int row = sysRoleDao.updateObject(entity);
	sysRoleMenuDao.deleteObejctsByMenuId(entity.getId());
	sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
	return row;
}

@Override
public List<CheckBox> findObjects() {
	
	return sysRoleDao.findObjects();
}

@Override
public Map<String, Object> findObjectById(Integer userId) {
	//1.合法性验证
			if(userId==null||userId<=0)
			throw new ServiceException(
			"参数数据不合法,userId="+userId);
			//2.业务查询
			SysUserDeptVo user=
			sysUserDao.findObjectById(userId);
			if(user==null)
			throw new ServiceException("此用户已经不存在");
			List<Integer> roleIds=
			sysUserRoleDao.findRoleIdsByUserId(userId);
			//3.数据封装
			Map<String,Object> map=new HashMap<>();
			map.put("user", user);
			map.put("roleIds", roleIds);
	return map;
}

@Override
public int updateObject(SysUser entity, Integer[] roleIds) {
	//1.参数有效性验证
			if(entity==null)
				throw new IllegalArgumentException("保存对象不能为空");
			if(StringUtils.isEmpty(entity.getUsername()))
				throw new IllegalArgumentException("用户名不能为空");
			if(roleIds==null||roleIds.length==0)
				throw new IllegalArgumentException("必须为其指定角色");
			//其它验证自己实现，例如用户名已经存在，密码长度，...
			//2.更新用户自身信息
			int rows=sysUserDao.updateObject(entity);
			//3.保存用户与角色关系数据
			sysUserRoleDao.deleteObjectsByUserId(entity.getId());
			sysUserRoleDao.insertObjects(entity.getId(),
					roleIds);
			//4.返回结果
	return rows;
}





}
