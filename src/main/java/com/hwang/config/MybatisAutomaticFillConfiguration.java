package com.hwang.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hwang.utiltools.BaseContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Objects;

/**
 * Description:
 *
 * @Author 王辉
 * @Create 2023/4/27 23:34
 * @Version 1.0
 */
@Configuration
@Slf4j
public class MybatisAutomaticFillConfiguration implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("创建数据时，自动填充基础信息开始！");
        if(Objects.nonNull(BaseContextUtil.getCurrentId())){
            this.setFieldValByName("createTime", new Date(), metaObject);
            this.setFieldValByName("createUser",BaseContextUtil.getCurrentId() , metaObject);
            this.setFieldValByName("updateTime", new Date(), metaObject);
            this.setFieldValByName("updateUser", BaseContextUtil.getCurrentId(), metaObject);
        }
        log.info("创建数据时，自动填充基础信息完成！");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新数据时，自动填充基础信息开始！");
        if(Objects.nonNull(BaseContextUtil.getCurrentId())){
            this.setFieldValByName("updateTime", new Date(), metaObject);
            this.setFieldValByName("updateUser", BaseContextUtil.getCurrentId(), metaObject);
        }
        log.info("更新数据时，自动填充基础信息完成！");
    }
}
