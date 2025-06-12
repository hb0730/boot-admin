package com.hb0730.mybatis.core.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 数据查询器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public class DataQuery<T> {
    private final BaseMapper<T> mapper;
    private Wrapper<T> wrapper;


    public DataQuery(BaseMapper<T> mapper) {
        this(mapper, null);
    }

    public DataQuery(BaseMapper<T> mapper, Wrapper<T> wrapper) {
        this.mapper = mapper;
        this.wrapper = wrapper;
    }

    public static <T> DataQuery<T> of(BaseMapper<T> mapper) {
        return new DataQuery<>(mapper);
    }

    public static <T> DataQuery<T> of(BaseMapper<T> mapper, Wrapper<T> wrapper) {
        return new DataQuery<>(mapper, wrapper);
    }


    public DataQuery<T> wrapper(Wrapper<T> wrapper) {
        this.wrapper = wrapper;
        return this;
    }

    // -------------------- id --------------------

    /**
     * 根据id获取
     *
     * @param id id
     * @return T
     */
    public T get(Serializable id) {
        return mapper.selectById(id);
    }

    /**
     * 根据id获取
     *
     * @param id id
     * @return Optional<T>
     */
    public Optional<T> getOptional(Serializable id) {
        return Optional.ofNullable(get(id));
    }

    // -------------------- statistic --------------------

    /**
     * 统计
     *
     * @return 数量
     */
    public Long count() {
        return mapper.selectCount(wrapper);
    }

    /**
     * 是否不存在
     *
     * @return true 不存在
     */
    public boolean absent() {
        return count() == 0;
    }

    /**
     * 是否不存在
     *
     * @return Optional<Boolean>
     */
    public Optional<Boolean> absentOptional() {
        return Optional.of(absent());
    }

    /**
     * 是否存在
     *
     * @return true 存在
     */
    public boolean present() {
        return count() > 0;
    }

    // -------------------- list --------------------

    /**
     * 列表
     *
     * @return List<T>
     */
    public List<T> list() {
        return mapper.selectList(wrapper);
    }

    /**
     * 列表
     *
     * @return Optional<List < T>>
     */
    public Optional<List<T>> listOptional() {
        return Optional.of(list());
    }

    // -------------------- one --------------------

    /**
     * 获取一个
     *
     * @return T
     */
    public T one() {
        List<T> res = mapper.selectList(wrapper);
        return res.isEmpty() ? null : res.get(0);
    }

    /**
     * 获取一个
     *
     * @return Optional<T>
     */
    public Optional<T> oneOptional() {
        return Optional.ofNullable(one());
    }

    // -------------------- remove --------------------

    /**
     * 删除
     *
     * @return 是否成功
     */
    public boolean remove() {
        return mapper.delete(wrapper) > 0;
    }

    /**
     * 删除
     *
     * @param wrapper 条件
     * @return 是否成功
     */
    public boolean remove(Wrapper<T> wrapper) {
        return mapper.delete(wrapper) > 0;
    }
}
