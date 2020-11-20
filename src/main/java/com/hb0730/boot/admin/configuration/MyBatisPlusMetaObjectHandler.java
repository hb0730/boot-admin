package com.hb0730.boot.admin.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hb0730.boot.admin.security.model.User;
import com.hb0730.boot.admin.security.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis-plus 填充
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增填充
     *
     * @param metaObject {@link MetaObject}
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        User currentUser = SecurityUtils.getCurrentUser();
        if (null != currentUser) {
            this.fillStrategy(metaObject, "createUserId", currentUser.getId());
        }
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
        this.fillStrategy(metaObject, "version", 1);

    }

    /**
     * 修改填充
     *
     * @param metaObject {@link MetaObject}
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        User currentUser = SecurityUtils.getCurrentUser();
        if (null != currentUser) {
            this.fillStrategy(metaObject, "updateUserId", currentUser.getId());
        }
        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
    }
}
