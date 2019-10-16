package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cy.pj.common.vo.JsonResult;

@Controller
@RequestMapping("/")
public class PageController {
	@RequestMapping("doIndexUI")
public String doIndexUI() {
	return "starter";
}
//	@RequestMapping("log/log_list")
//	public String doLogUI() {
//		return "sys/log_list";
//	}
	@RequestMapping("doPageUI")
	public String doPageUI() {
			  return "common/page";
	}
    @RequestMapping("{module}/{page}")//rest风格
    public String doMenuUI(@PathVariable String page) {
    	return "sys/"+page;
    }
}
