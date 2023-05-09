package com.hwang.annotation;

import java.lang.annotation.*;

/**
 * Description:
 *  自定义注解，打印方法执行的日志信息
 * @Author 王辉
 * @Create 2023/5/6 11:37
 * @Version 1.0
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintLogs {
    String BusinessName();//业务名称
}
