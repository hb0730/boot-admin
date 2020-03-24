package com.hb0730.boot.admin.user.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hb0730.boot.admin.commons.web.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = SecurityUtils.getUserId();
        this.fillStrategy(metaObject, "createUserId", userId);
        this.fillStrategy(metaObject, "createTime", new Date());
        this.fillStrategy(metaObject, "version", 1);
        this.fillStrategy(metaObject, "isEnabled", 1);
        this.fillStrategy(metaObject, "delFlag", 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = SecurityUtils.getUserId();
        this.fillStrategy(metaObject, "updateUserId", userId);
        this.fillStrategy(metaObject, "updateTime", new Date());
    }

}
