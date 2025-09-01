package com.subrutin.catalog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
	
	@Pointcut("execution(* com.subrutin.catalog.web.*.*(..))")
	private void restAPI() {}
	
	@Pointcut("within(com.subrutin.catalog.web.*)")
	private void withinPointcutExample() {}
	
	@Pointcut("args(com.subrutin.catalog.dto.CategoryCreateRequestDTO)")
	private void argsPointcutExample() {}
	
	@Pointcut("@args(com.subrutin.catalog.annotation.LogThisArg)")
	private void argsAnnotationPointcutExample() {}
	
	@Pointcut("@annotation(com.subrutin.catalog.annotation.LogThisMethod)")
	private void annotationPointcutExample() {}
	
	@Before("annotationPointcutExample()")
	public void beforeExecutedLogging() {
		log.info("this is log from aspect before method executed");
	}
	
	@After("annotationPointcutExample()")
	public void afterExecutedLogging() {
		log.info("this is log from aspect after method executed");
	}

	@AfterReturning("annotationPointcutExample()")
	public void afterReturnExecutedLogging() {
		log.info("this is log from aspect after returning method executed");
	}

	@AfterThrowing("annotationPointcutExample()")
	public void afterThrowingExecutedLogging() {
		log.info("this is log from aspect throwing method executed");
	}
	
	@Around("restAPI()")
	public Object processingTimeLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		try {
			log.info("start {}.{}",joinPoint.getTarget().getClass().getName(), 
					joinPoint.getSignature().getName() );
			stopWatch.start();
			return joinPoint.proceed();
		} finally {
			stopWatch.stop();
			log.info("finish {}.{} execution time = {}",joinPoint.getTarget().getClass().getName(), 
					joinPoint.getSignature().getName(),
					stopWatch.getTotalTimeMillis());
		}
	}

}
