package com.hb0730.boot.admin.commons.domain.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.boot.admin.commons.domain.model.domain.BusinessDomain;
import com.hb0730.boot.admin.commons.utils.spring.SecurityUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 基类service(填充删除修改) <br>
 * mybatis Plus 无法填充 非entity {@link com.baomidou.mybatisplus.core.MybatisDefaultParameterHandler#process(MappedStatement, Object)}
 *
 * @author bing_huang
 * @date 2020/06/26 12:13
 * @since V1.0
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
    @Override
    public boolean update(Wrapper<T> updateWrapper) {
        if (updateWrapper instanceof UpdateWrapper) {
            UpdateWrapper<T> update = (UpdateWrapper<T>) updateWrapper;
            update.set(BusinessDomain.UPDATE_TIME, new Date());
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (Objects.nonNull(attributes) && Objects.nonNull(SecurityUtils.getLoginUser())) {
                update.set(BusinessDomain.UPDATE_USER_ID, SecurityUtils.getLoginUser().getId());
            }
        }
        return super.update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        T entity = super.getById(id);
        if (entity instanceof BusinessDomain) {
            BusinessDomain e1 = (BusinessDomain) entity;
            e1.setUpdateTime(new Date());
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            if (Objects.nonNull(attributes) && Objects.nonNull(SecurityUtils.getLoginUser())) {
                e1.setUpdateUserId(SecurityUtils.getLoginUser().getId());
            }
            super.updateById(entity);
        }
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        List<T> entities = super.listByIds(idList);
        if (!CollectionUtils.isEmpty(entities)) {
            entities.forEach((entity) -> {
                if (entity instanceof BusinessDomain) {
                    BusinessDomain e1 = (BusinessDomain) entity;
                    e1.setUpdateTime(new Date());
                    RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
                    if (Objects.nonNull(attributes) && Objects.nonNull(SecurityUtils.getLoginUser())) {
                        e1.setUpdateUserId(SecurityUtils.getLoginUser().getId());
                    }
                }
            });
            super.updateBatchById(entities);
        }
        return super.removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(Wrapper<T> queryWrapper) {
//        List<T> list = super.list(queryWrapper);
//        updateBatchById(list);
        return super.remove(queryWrapper);
    }
}
