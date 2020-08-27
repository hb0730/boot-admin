package com.hb0730.boot.admin.project.system.user.info.controller;


import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.project.system.user.info.model.entity.UserInfoEntity;
import com.hb0730.boot.admin.project.system.user.info.model.query.UserInfoParams;
import com.hb0730.boot.admin.project.system.user.info.model.vo.UserInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息  前端控制器
 *
 * @author bing_huang
 * @since 3.0.0
 */
@RestController
@RequestMapping("/api/v3/system/user/info")
public class UserInfoController extends AbstractBaseController<Long, UserInfoVO, UserInfoParams, UserInfoEntity> {

}

