package com.cy.pj.sys.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;


@RestController
@RequestMapping("/menu/")
public class SysMenuController {
	@Autowired
private SysMenuService sysMenuService;
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {
		List<Map<String, Object>> list = sysMenuService.findObjects();
		return new JsonResult(list);
	}
@RequestMapping("doDeleteObject")
@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		return new JsonResult(sysMenuService.deleteObject(id));
	}
@RequestMapping("doFindZtreeMenuNodes")
@ResponseBody
public JsonResult dofindZtreeMenuNodes() {
	
	return new JsonResult(sysMenuService.findZtreeMenuNodes());
}
@RequestMapping("doSaveObject")
@ResponseBody
public JsonResult dosaveObject(SysMenu entity) {
	sysMenuService.saveObject(entity);
	return new JsonResult("save ok");
}
@RequestMapping("doUpdateObject")
@ResponseBody
public JsonResult doupdateObject(SysMenu entity) {
	sysMenuService.updateObject(entity);
	return new JsonResult("update ok");
}
}
