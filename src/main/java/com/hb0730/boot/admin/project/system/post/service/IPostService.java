package com.hb0730.boot.admin.project.system.post.service;

import com.hb0730.boot.admin.domain.service.IBaseService;
import com.hb0730.boot.admin.project.system.post.model.dto.PostDTO;
import com.hb0730.boot.admin.project.system.post.model.entity.PostEntity;
import com.hb0730.boot.admin.project.system.post.model.query.PostParams;

/**
 * 岗位  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IPostService extends IBaseService<Long, PostParams, PostDTO, PostEntity> {

}
