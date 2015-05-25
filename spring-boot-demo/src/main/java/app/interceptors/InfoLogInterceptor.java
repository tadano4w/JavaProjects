package app.interceptors;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InfoLogInterceptor {

	@Pointcut("execution(public * app..*Service.*(..))")
	public void pointCut()	{
	}

	@Around(value = "pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

		try {
			if (log.isInfoEnabled()) {
				log.info("parameters:" + Arrays.deepToString(joinPoint.getArgs()));
			}
			Object result = joinPoint.proceed();
			Object outResult = result;
			if (!log.isDebugEnabled() && outResult instanceof List) {
				// デバッグではない場合、リスト項目は件数のみ出力する
				outResult = "List " + ((List<?>)result).size() + "件";
			}

			if (log.isInfoEnabled()) {
				log.info(outResult.toString());
			}

			return result;
		} catch (Throwable t) {
			log.error("", t);
			throw t;
		}
	}
}
