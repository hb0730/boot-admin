package com.hb0730.boot.admin.project.system.post.controller;


import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.project.system.post.model.dto.PostDTO;
import com.hb0730.boot.admin.project.system.post.model.entity.PostEntity;
import com.hb0730.boot.admin.project.system.post.model.query.PostParams;
import com.hb0730.boot.admin.project.system.post.service.IPostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 岗位  前端控制器
 *
 * @author bing_huang
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/api/v3/system/post")
@PreAuth("post")
@ClassDescribe("岗位管理")
public class PostController extends AbstractBaseController<Long, PostDTO, PostParams, PostEntity> {
    private final IPostService service;

    public PostController(IPostService service) {
        super(service);
        this.service = service;
    }
}

