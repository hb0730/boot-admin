package com.hb0730.boot.admin.modules.sys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysConfig;
import com.hb0730.boot.admin.modules.sys.system.model.query.ConfigQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.ConfigVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    /**
     * 分页查询
     *
     * @param page  .
     * @param query .
     * @return .
     */
    List<ConfigVO> queryPage(Page<ConfigVO> page, @Param("query") ConfigQuery query);
}
