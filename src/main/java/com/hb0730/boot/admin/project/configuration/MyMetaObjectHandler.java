package com.hb0730.boot.admin.project.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;
import java.util.Objects;


/**
 * <p>
 * mybatis填充
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (!Objects.isNull(attributes)) {
            this.fillStrategy(metaObject, "createUserId", SecurityUtils.getLoginUser().getId());
        }
        this.fillStrategy(metaObject, "createTime", new Date());
        this.fillStrategy(metaObject, "version", 1);
        this.fillStrategy(metaObject, "isEnabled", 1);
        this.fillStrategy(metaObject, "delFlag", 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (!Objects.isNull(attributes)) {
            this.fillStrategy(metaObject, "updateUserId", SecurityUtils.getLoginUser().getId());
        }
        this.fillStrategy(metaObject, "updateTime", new Date());
    }

}
