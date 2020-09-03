package com.hb0730.boot.admin.project.system.post.service.impl;

import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.project.system.post.mapper.IPostMapper;
import com.hb0730.boot.admin.project.system.post.model.dto.PostDTO;
import com.hb0730.boot.admin.project.system.post.model.entity.PostEntity;
import com.hb0730.boot.admin.project.system.post.model.query.PostParams;
import com.hb0730.boot.admin.project.system.post.service.IPostService;
import org.springframework.stereotype.Service;

/**
 * 岗位  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class PostServiceImpl extends SuperBaseServiceImpl<Long, PostParams, PostDTO, PostEntity, IPostMapper> implements IPostService {

}
