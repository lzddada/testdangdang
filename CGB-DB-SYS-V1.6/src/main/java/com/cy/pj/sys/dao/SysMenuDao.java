package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;
@Mapper
public interface SysMenuDao {
   List<Map<String, Object>> findObjects();
   int deleteObject(Integer id);//根据菜单id删除菜单
   int getChildCount(Integer id);//根据菜单id统计子菜单个数
   List<Node> findZtreeMenuNodes();//查找菜单框
   int insertObject(SysMenu entity);//新增菜单数据
   int updateObject(SysMenu entity);
}
