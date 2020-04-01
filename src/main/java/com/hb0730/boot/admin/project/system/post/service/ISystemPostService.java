package com.hb0730.boot.admin.project.system.post.service;

import com.hb0730.boot.admin.project.system.post.model.entity.SystemPostEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.lang.NonNull;

/**
 * <p>
 * 系统岗位  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-28
 */
public interface ISystemPostService extends IService<SystemPostEntity> {

    /**
     * 根据id删除
     * @param id id
     * @return 是否成功
     */
    boolean deleteById(@NonNull Long id);
}
