package top.aikele.springAopTest;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.springAopTest
 * @className: MyPointCut
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/16 22:48
 * @version: 1.0
 */
public class MyPointCut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return AnnotationUtils.isCandidateClass(clazz, MyAop.class);
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                Annotation[] annoArray = method.getDeclaredAnnotations();
                if (annoArray == null || annoArray.length == 0) {
                    return false;
                }

                for (Annotation annotation : annoArray) {
                    if (annotation.annotationType() == MyAop.class) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        };
    }
}
