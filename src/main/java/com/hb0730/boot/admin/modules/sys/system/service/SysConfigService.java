package com.hb0730.boot.admin.modules.sys.system.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.cache.ConfigCache;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysConfigMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysConfig;
import com.hb0730.boot.admin.modules.sys.system.model.query.ConfigQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.ConfigVO;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置 SERVICE
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysConfigService extends BaseServiceImpl<SysConfigMapper, SysConfig> {
    private final ConfigCache cache;

    /**
     * 分页查询
     *
     * @param query .
     * @return .
     */
    public R<BasePage<ConfigVO>> queryPage(ConfigQuery query) {
        Page<ConfigVO> page = new Page<>(query.getCurrent(), query.getSize());
        List<ConfigVO> list = baseMapper.queryPage(page, query);
        page.setRecords(list);
        BasePage<ConfigVO> _page = new BasePage<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
        return R.OK(_page);
    }

    /**
     * 列表查询
     *
     * @param query .
     * @return .
     */
    public R<List<ConfigVO>> queryList(ConfigQuery query) {
        List<ConfigVO> list = baseMapper.queryPage(null, query);
        return R.OK(list);
    }

    public boolean save(SysConfig entity) {
        if (null == entity) {
            return false;
        }
        boolean save = super.save(entity);
        cache.putCache(entity);
        return save;
    }

    /**
     * 更新ID
     *
     * @param entity 实体对象 .
     * @return .
     */
    public boolean updateById(SysConfig entity) {
        if (null == entity) {
            return false;
        }
        if (null == entity.getId()) {
            return false;
        }
        boolean update = super.updateById(entity);
        cache.putCache(entity);
        return update;
    }

    /**
     * 删除
     *
     * @param ids .
     * @return .
     */
    public boolean removeByIds(List<String> ids) {
        if (null == ids || ids.isEmpty()) {
            return false;
        }
        List<SysConfig> configs = listByIds(ids);
        if (CollectionUtil.isNotEmpty(configs)) {
            configs.stream().map(SysConfig::getCode).forEach(cache::delCache);
        }
        return super.removeByIds(ids);
    }

    public R<String> refreshCache(@Nonnull String id){
        SysConfig config = getById(id);
        if (null == config) {
            return R.NG("配置不存在");
        }
        cache.putCache(config);
        return R.OK("刷新成功");
    }
}
