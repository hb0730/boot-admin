package com.hb0730.boot.admin.project.system.post.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.domain.service.IExportService;
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
public interface ISystemPostService extends IService<SystemPostEntity>, IExportService<PostExcelDto> {


    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   过滤条件
     * @return 分页后信息
     */
    PageInfo<SystemPostVO> list(Integer page, Integer pageSize, PostParams params);

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
