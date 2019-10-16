package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
/**
 * @Aspect注解用于标识此类为一个AOP横向切面对象
 * 
 *
 */
@Service
@Aspect
@Slf4j
public class SysLogAspect {
	/*@Pointcut注解用于定义切入点
	 * bean表达式为切入点表达式
	 * bean表达式内部指定的bean对象中所有的方法为切入点(进行功能扩展的点)
	 * 
	 */
@Pointcut("bean(sysUserServiceImpl)")
public void logPointCut() {}
public Object around(ProceedingJoinPoint jp) throws Throwable{
	try {
		log.info("start:"+System.currentTimeMillis());
		Object result = jp.proceed();
		log.info("end:"+System.currentTimeMillis());
		return result;
	} catch (Throwable e) {
		log.error(e.getMessage());
		throw e;
	}
}
}
