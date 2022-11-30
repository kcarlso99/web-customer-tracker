package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private Logger myLogger = Logger.getLogger(LoggingAspect.class.getName());
	
	/**
	 * Add @Before Advice to the doForAppFlow Pointcut defined in AopExpressions
	 * @param theJoinPoint
	 */
	@Before("com.luv2code.springdemo.aspect.AopExpressions.doForAppFlow()")
	public void beforeAdvice(JoinPoint theJoinPoint) {
		
		// Display method we are calling
		String methodSign = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> @Before method call : " + methodSign );
		
		// Display arguments to method
		Object[] args = theJoinPoint.getArgs();
		
		for (Object arg : args) {
			myLogger.info("====> Argument : " + arg);
		}
		
	}
	
	/**
	 * @AfterReturning Advice for doForAppFlow() Pointcut declaration
	 * @param theJoinPoint
	 * @param theResult
	 */
	@AfterReturning(
			pointcut="com.luv2code.springdemo.aspect.AopExpressions.doForAppFlow()",
			returning="theResult")
	public void afterReturningAdvice(JoinPoint theJoinPoint, Object theResult) {

		// Display method we are calling
		String methodSign = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> @AfterReturning from method : " + methodSign);

		// Display the data returned
		myLogger.info("====> result : " + theResult);

	}

	
	
}
