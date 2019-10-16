package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

public interface SysMenuService {
List<Map<String, Object>> findObjects();//
int deleteObject(Integer id);//根据控制层的id执行删除方法
List<Node> findZtreeMenuNodes();
int saveObject(SysMenu entity);
int updateObject(SysMenu entity);
}
