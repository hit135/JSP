package kr.or.youth.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotationAspect {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(public * kr.or.youth.*.service.*.*(..))	"
			//+ "|| execution(public * kr.or.youth.main.*.*(..)) "
			//+ "|| execution(public * kr.or.youth.ajax.*.*(..))")
			)
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
