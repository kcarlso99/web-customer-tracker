package com.luv2code.springdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Utility class to define Point-Cut Declarations that can be used by other classes
 * 
 * @author kcarlso1
 *
 */
@Aspect
public class AopExpressions {
	
	/**
	 * Point-cut declarations are available to REUSE Point-cut expressions
	 */
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	public void doForDao() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	public void doForController() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	public void doForService() {}
	
	/**
	 * Point-cut expression to execute for any method in the Controller, Dao, or Service package
	 */
	@Pointcut("doForDao() || doForController() || doForService()")
	public void doForAppFlow() {}

}
