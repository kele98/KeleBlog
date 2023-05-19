package top.aikele.config;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import top.aikele.entity.Result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.config
 * @className: GloabExcepetion
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/19 0:46
 * @version: 1.0
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(BindException.class)
    public Result AuthenticationException(BindException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<Object> errorsMessage = fieldErrors.stream().map(error -> error.getField()+":"+error.getDefaultMessage()).collect(Collectors.toList());
        return Result.fail("参数有误",errorsMessage);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result ArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        return Result.fail("参数有误",e.getName());
    }
}
