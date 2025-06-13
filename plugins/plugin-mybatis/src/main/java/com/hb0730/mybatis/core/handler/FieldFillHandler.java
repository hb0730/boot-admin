package com.hb0730.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hb0730.base.AppUtil;
import com.hb0730.base.meta.ICurrentUserService;
import com.hb0730.base.meta.IUserInfo;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 字段填充
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public class FieldFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        fill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fill(metaObject);
    }

    private void fill(MetaObject metaObject) {
        ICurrentUserService currentUserService = AppUtil.getBean(ICurrentUserService.class);
        if (null == currentUserService) {
            return;
        }
        IUserInfo userInfo = currentUserService.getCurrentUser();
        Date date = new Date();
        Object _value = getFieldValByName("created", metaObject);
        if (metaObject.hasSetter("created") && _value == null) {
            setFieldValByName("created", date, metaObject);
        }
        _value = getFieldValByName("createdBy", metaObject);
        if (metaObject.hasSetter("createdBy") && _value == null) {
            setFieldValByName("createdBy", userInfo.getUsername(), metaObject);
        }
        if (metaObject.hasSetter("modified")) {
            setFieldValByName("modified", date, metaObject);
        }
        if (metaObject.hasSetter("modifiedBy")) {
            setFieldValByName("modifiedBy", userInfo.getUsername(), metaObject);
        }

    }
}
