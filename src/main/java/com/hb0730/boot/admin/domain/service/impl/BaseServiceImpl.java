package com.hb0730.boot.admin.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb0730.boot.admin.domain.model.entity.BusinessDomain;

import java.util.Date;

/**
 * 基类service(填充删除修改) <br>
 * mybatis Plus 无法填充 非entity {@link com.baomidou.mybatisplus.core.MybatisDefaultParameterHandler#process(MappedStatement, Object)}
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class BaseServiceImpl<MAPPER extends BaseMapper<ENTITY>, ENTITY> extends ServiceImpl<MAPPER, ENTITY> {

    @Override
    public boolean update(Wrapper<ENTITY> updateWrapper) {
        if (updateWrapper instanceof UpdateWrapper) {
            UpdateWrapper<ENTITY> update = (UpdateWrapper<ENTITY>) updateWrapper;
            update.set(BusinessDomain.UPDATE_TIME, new Date());
            // 设置用户
        }
        return super.update(updateWrapper);
    }
}
