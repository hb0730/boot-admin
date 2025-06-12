package com.hb0730.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.mybatis.core.query.DataQuery;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public interface IMapper<T> extends BaseMapper<T> {
    /**
     * 获取 LambdaQueryWrapper 对象
     *
     * @return 获取 wrapper
     */
    default LambdaQueryWrapper<T> lambda() {
        return Wrappers.lambdaQuery();
    }

    /**
     * 获取 DataQuery 对象
     *
     * @return DataQuery
     */
    default DataQuery<T> of() {
        return DataQuery.of(this);
    }

    /**
     * 获取 DataQuery 对象
     *
     * @param wrapper wrapper
     * @return DataQuery
     */
    default DataQuery<T> of(LambdaQueryWrapper<T> wrapper) {
        return DataQuery.of(this, wrapper);
    }

    /**
     * 获取 DataQuery 对象
     *
     * @param supplier supplier
     * @return DataQuery
     */
    default DataQuery<T> of(Supplier<LambdaQueryWrapper<T>> supplier) {
        return DataQuery.of(this, supplier.get());
    }

    /**
     * 获取 DataQuery 对象
     *
     * @param function function
     * @return DataQuery
     */
    default DataQuery<T> of(Function<LambdaQueryWrapper<T>, LambdaQueryWrapper<T>> function) {
        return DataQuery.of(this, function.apply(Wrappers.lambdaQuery()));
    }

}
