package com.hb0730.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hb0730.base.core.UserContext;
import com.hb0730.base.core.UserInfo;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.mybatis.core.domain.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Slf4j
public class InjectionMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (null != metaObject && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
                Date current = null != baseEntity.getCreated() ? baseEntity.getCreated() : new Date();
                UserInfo userInfo = getUserInfo();
                String username = null;
                if (null != userInfo) {
                    username = userInfo.getUsername();
                }
                baseEntity.setCreated(current);
                baseEntity.setCreatedBy(username);
            }

        } catch (Exception e) {
            throw new ServiceException("mybatis自动填充用户信息异常", 500, e);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (null != metaObject && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
                Date current = null != baseEntity.getModified() ? baseEntity.getModified() : new Date();
                UserInfo userInfo = getUserInfo();
                String username = null;
                if (null != userInfo) {
                    username = userInfo.getUsername();
                }
                baseEntity.setModified(current);
                baseEntity.setModifiedBy(username);
            }
        } catch (Exception e) {
            throw new ServiceException("mybatis自动填充用户信息异常", 500, e);
        }
    }


    private UserInfo getUserInfo() {
        try {
            return UserContext.get();
        } catch (Exception e) {
            log.warn("mybatis自动填充用户信息警告，获取当前用户信息失败！请检查是否在请求上下文中设置了用户信息。UserContext.set(UserInfo userInfo)");
            return null;
        }

    }
}
