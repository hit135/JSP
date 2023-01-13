package kr.or.nextit.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlAspect {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	public void beforeLog(JoinPoint joinPoint) {
		// 클래스 획득
		String targetClass = joinPoint.getTarget().getClass().getSimpleName();
		// 메소드명 취득
		String targetMethod = joinPoint.getSignature().getName();
		
		logger.info("[" + targetClass + "_" + targetMethod + " ] 호출 되었습니다.");
	}
	
	public void afterLog(JoinPoint joinPoint) {
		// 클래스 획득
		String targetClass = joinPoint.getTarget().getClass().getSimpleName();
		// 메소드명 취득
		String targetMethod = joinPoint.getSignature().getName();
		
		logger.info("[" + targetClass + "_" + targetMethod + " ] 종료 되었습니다.");
	}
	*/
	
	
	// joinPoint는 보조업무에서 주업무를 가져올 수 있도록 해주는 것
	// joinPoint.getArgs() 뭐 파라미터로 찍을 수 있다..
	
	
	// around는 ProceedingJoinPoint를 쓴다
	// ProceedingJoinPoint로 joinPoint.proceed()를 사용하면 주업무를 부른다
	// 그러니 joinPoint.proceed(); 이거 전에가 주업무 전 보조 업무
	// joinPoint.proceed(); 이것 후에가 주업무 후 보조 업무
	public Object aroundLog(ProceedingJoinPoint joinPoint) {
		String targetMethod = joinPoint.getSignature().toShortString();
		
		logger.info(targetMethod + "_start");
		
		// joinPoint.getArgs()는 현재 날라간 파라미터를 취득할 수 있다
		logger.info(targetMethod + "_parameter" + Arrays.toString(joinPoint.getArgs()));
		
		long startTime = System.currentTimeMillis();
		
		Object result = null;
		
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			long finishTime = System.currentTimeMillis();
			logger.info(targetMethod + "_end");
			logger.info(targetMethod + "_실행시간 : " + (finishTime - startTime) + "ms");
		}
		return result;
	}
	

}
