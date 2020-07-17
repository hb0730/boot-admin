package com.hb0730.boot.admin.project.system.dict.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.dict.model.entity.SystemDictEntity;
import com.hb0730.boot.admin.project.system.dict.model.vo.DictParams;
import com.hb0730.boot.admin.project.system.dict.model.vo.SystemDictVO;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字段类型  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-30
 */
public interface ISystemDictService extends IBaseService<Long, DictParams, SystemDictVO, SystemDictEntity> {

    /**
     * 获取分页数据
     *
     * @param parentId 父id
     * @param params   过滤条件
     * @return 分页数量
     * @since v2.0
     */
    Page<SystemDictVO> page(Long parentId, @NonNull DictParams params);

    /**
     * <p>
     * 根据id修改
     * </p>
     *
     * @param id id
     * @param vo 参数
     * @return 是否成功
     */
    @Override
    boolean updateById(@NonNull Long id, @NonNull SystemDictVO vo);


    /**
     * <p>
     * 根据id删除
     * </P>
     *
     * @param id id
     * @return 是否成功
     */
    boolean removeById(@NonNull Long id);


    /**
     * <p>
     * 将数据字典转换成map类型
     * </p>
     *
     * @return map类型的数据字典
     */
    Map<String, List<Map<String, Object>>> getDictForMap();

    /**
     * 更新缓存
     */
    void cache();
}
