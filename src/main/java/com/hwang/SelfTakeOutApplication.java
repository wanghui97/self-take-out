package com.hwang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.rmi.activation.ActivationGroup_Stub;

/**
 * Description:
 *
 * @Author 王辉
 * @Create 2023/4/27 15:34
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
public class SelfTakeOutApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfTakeOutApplication.class,args);
        log.info("个人外卖系统成功启动！");
    }
}
