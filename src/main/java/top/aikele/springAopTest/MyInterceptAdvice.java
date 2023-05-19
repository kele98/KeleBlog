package top.aikele.springAopTest;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.springAopTest
 * @className: MyInterceptAdvice
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/16 22:44
 * @version: 1.0
 */
public class MyInterceptAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("my intercept before");
        return invocation.proceed();
    }
}
