package com.de.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @编写人:de
 * @时间:2019/10/20
 * @描述:
 */
@Component
@Aspect
public class Exmaop {
//    lalala
//    嘻嘻
//    @Pointcut("execution(* com.de.service..*(..)) && args(count,msg)")
////    public void empServicePointCut(int count,String msg){}
////
////    @AfterReturning(value = "empServicePointCut(count,msg)",returning = "re")
////    public Object empServiceAdvice(int count,String msg,Object re) throws Throwable {
//////        System.out.println("前置通知========================" + count);
//////        joinPoint.proceed();
//////        joinPoint.proceed(new Object[] {count,msg});
//////        preProcess
////        System.out.println("后者通知========================" + count);
////        return re;
//    }
}
