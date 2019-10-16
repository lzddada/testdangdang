package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		if (list==null||list.size()==0)throw new ServiceException("没有对应的菜单信息");
		return list;
	}
	@Override
	public int deleteObject(Integer id) {
		if (id==null||id<0)throw new IllegalArgumentException("请先选择");
		int count = sysMenuDao.getChildCount(id);
		if (count>0) throw new ServiceException("请先删除子菜单");
		sysRoleMenuDao.deleteObejctsByMenuId(id);
		int rows = sysMenuDao.deleteObject(id);
		if (rows==0) throw new ServiceException("此菜单可能已经不存在");
		
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		// TODO Auto-generated method stub
		return sysMenuDao.findZtreeMenuNodes();
	}
	@Override
	public int saveObject(SysMenu entity) {
		if (entity==null)throw new ServiceException("保存对象不能为空");
		//StringUtils.isEmpty 判断字符串是否为空 如果为空 则返回true
		if (StringUtils.isEmpty(entity.getName()))throw new ServiceException("菜单名不能为空");
		int rows;
		try {
			rows = sysMenuDao.insertObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("保存失败");
		}
		return rows;
	}
	@Override
	public int updateObject(SysMenu entity) {
		//1.保存对象不能为空
		if (entity==null)throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getName()))throw new ServiceException("菜单名不能为空");
		//2.更新操作
		int rows = sysMenuDao.updateObject(entity);
		if (rows==0) throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	
}
