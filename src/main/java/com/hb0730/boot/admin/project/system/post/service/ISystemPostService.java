package com.hb0730.boot.admin.project.system.post.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.domain.service.IExportService;
import com.hb0730.boot.admin.project.system.post.model.dto.PostExcelDto;
import com.hb0730.boot.admin.project.system.post.model.entity.SystemPostEntity;
import com.hb0730.boot.admin.project.system.post.model.vo.PostParams;
import com.hb0730.boot.admin.project.system.post.model.vo.SystemPostVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 系统岗位  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-28
 */
public interface ISystemPostService extends IBaseService<Long, PostParams, SystemPostVO, SystemPostEntity>, IExportService<PostExcelDto> {

    /**
     * 分页查询
     *
     * @param params 过滤条件
     * @return 分页后信息
     * @since v2.0
     */
    @Override
    Page<SystemPostVO> page(@NonNull PostParams params);

    /**
     * 列表查询
     *
     * @param params 过滤条件
     * @return 列表
     * @since v2.0
     */
    @Override
    List<SystemPostVO> list(@NonNull PostParams params);

    /**
     * 根据id删除
     *
     * @param id id
     * @return 是否成功
     */
    boolean deleteById(@NonNull Long id);

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param params 过滤条件
     * @return 数据
     */
    List<PostExcelDto> export(PostParams params);
}
