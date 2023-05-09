package com.hwang.aop;

import com.alibaba.fastjson.JSON;
import com.hwang.annotation.PrintLogs;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.generic.RET;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.logging.Handler;

/**
 * Description:
 *  打印日志切面类
 * @Author 王辉
 * @Create 2023/5/6 11:45
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class PrintLogsAspect {
    @Around("@annotation(com.hwang.annotation.PrintLogs)")
    public Object pintLogs(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        try {
            HandlerBefore(pjp);
            result = pjp.proceed();
            HandlerAfterReturn(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return result;
    }

    private void HandlerBefore(ProceedingJoinPoint pjp) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        assert Objects.nonNull(servletRequestAttributes);
        HttpServletRequest request = servletRequestAttributes.getRequest();
        PrintLogs printLogsInfo = getPrintLogsInfo(pjp);
        log.info("===============Start================");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", printLogsInfo.BusinessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", pjp.getSignature().getDeclaringTypeName(),((MethodSignature)pjp.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}",request.getRemoteHost());
        // 打印请求入参
        if(!"upload".equals(((MethodSignature)pjp.getSignature()).getName())&&!"download".equals(((MethodSignature)pjp.getSignature()).getName())){
            log.info("Request Args   : {}", JSON.toJSONString(pjp.getArgs()));
        }
    }

    private PrintLogs getPrintLogsInfo(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(PrintLogs.class);
    }

    private void HandlerAfterReturn(Object result) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(result));
    }

}
