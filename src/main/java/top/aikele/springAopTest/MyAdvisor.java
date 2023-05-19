package top.aikele.springAopTest;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.stereotype.Component;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.springAopTest
 * @className: MyAdvisor
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/16 22:42
 * @version: 1.0
 */
@Component
public class MyAdvisor implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
//        System.out.println("getPointcut被调用了");
        return new MyPointCut();
    }

    @Override
    public Advice getAdvice() {
//        System.out.println("getAdvice被调用了");
        return new MyInterceptAdvice();
    }
    @Override
    public boolean isPerInstance() {
        return false;
    }
}
