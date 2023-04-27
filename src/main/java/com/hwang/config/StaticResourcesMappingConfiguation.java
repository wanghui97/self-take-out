package com.hwang.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Description:
 *
 * @Author 王辉
 * @Create 2023/4/27 17:36
 * @Version 1.0
 */
@Configuration
public class StaticResourcesMappingConfiguation extends WebMvcConfigurationSupport {
    /**
     * 修改静态资源映射路径
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
    }
}
