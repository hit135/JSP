package kr.or.nextit.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// 컴포넌트, 컨트롤러, 서비스, 레파지토리
@Component
@Aspect
public class AnnotationAspect {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	@Before("execution(public * kr.or.nextit.member.service.*.*(..))")
	public void beforeLog(JoinPoint joinPoint) {
		// 클래스 획득
		String targetClass = joinPoint.getTarget().getClass().getSimpleName();
		// 메소드명 취득
		String targetMethod = joinPoint.getSignature().getName();
		logger.info("[" + targetClass + "_" + targetMethod + " ] 호출 되었습니다.");
	}
	*/
	/*
	@After("execution(public * kr.or.nextit.member.service.*.*(..))")
	public void afterLog(JoinPoint joinPoint) {
		// 클래스 획득
		String targetClass = joinPoint.getTarget().getClass().getSimpleName();
		// 메소드명 취득
		String targetMethod = joinPoint.getSignature().getName();
		logger.info("[" + targetClass + "_" + targetMethod + " ] 호출 되었습니다.");
	}
	*/
	
	// 리턴 값이 ret
	/*@AfterReturning */
	/*
	@AfterReturning(pointcut="execution(public * kr.or.nextit.*.service.*.*(..))", returning = "ret")
	public void afterReturning(JoinPoint joinPoint, Object ret) {
		String targetClass = joinPoint.getTarget().getClass().getSimpleName();
		String targetMethod = joinPoint.getSignature().getName();
		
		logger.info("@AfterReturning [ " +  targetClass+"_"+targetMethod +" ] 리턴갑 : "+ ret );
		logger.info("@AfterReturning [ " +  targetClass+"_"+targetMethod +" ] 가 종료되었습니다." );
	}
	*/
	
	
	/*@AfterThrowing*/
	/*@AfterThrowing(pointcut="execution(public * kr.or.nextit.*.service.*.*(..))", throwing = "ex")
	public void afterReturning(JoinPoint joinPoint, Throwable ex) {
		String targetClass = joinPoint.getTarget().getClass().getSimpleName();
		String targetMethod = joinPoint.getSignature().getName();

		logger.info("@AfterReturning [ " +  targetClass+"_"+targetMethod +" ] 메서드  실행 중 예외 발생" );
		logger.info("@AfterReturning [ " +  targetClass+"_"+targetMethod +" ] 예외  : "+ ex.getMessage() );
	}*/
	
	// pointcut이 여러개인경우 || , &&, !, 로 처리 가능
	// ..이 아니라 . 이므로 컨트롤러만 해당된다
	@Around("execution(public * kr.or.nextit.login.*.*(..))	"
			+ "|| execution(public * kr.or.nextit.member.*.*(..)) "
			+ "|| execution(public * kr.or.nextit.free.*.*(..))")
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
