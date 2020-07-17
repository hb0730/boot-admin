package com.hb0730.boot.admin.project.monitor.useronline.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.model.constants.ModuleName;
import com.hb0730.boot.admin.model.constants.RequestMappingNameConstants;
import com.hb0730.boot.admin.model.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.domain.result.ResponseResult;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.project.monitor.useronline.model.vo.ParamsVO;
import com.hb0730.boot.admin.project.monitor.useronline.model.vo.UserOnlineVO;
import com.hb0730.boot.admin.project.monitor.useronline.service.IUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 在线用户
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@RestController
@RequestMapping(RequestMappingNameConstants.REQUEST_USER_ONLINE)
public class UserOnlineController extends BaseController {
    @Autowired
    private IUserOnlineService userOnlineService;


    /**
     * <p>
     * 获取在线用户
     * </p>
     *
     * @param vo 过滤条件
     * @return 在线用户
     */
    @PostMapping("/all/page")
    @PreAuthorize("hasAnyAuthority('user:online:query','ROLE_ADMINISTRATOR','ROLE_USER_ONLINE_ADMIN')")
    public Result getAllPage(@RequestBody ParamsVO vo) {
        List<UserOnlineVO> onlineUser = userOnlineService.getOnlineUser(vo);
        Page<UserOnlineVO> page = new Page<>();
        page.setRecords(onlineUser);
        page.setTotal(onlineUser.size());
        return ResponseResult.resultSuccess(page);
    }

    /**
     * <p>
     * 强退
     * </p>
     *
     * @param token token
     * @return 是否成功
     */
    @GetMapping("/logout/{token}")
    @PreAuthorize("hasAnyAuthority('user:online:logout','ROLE_ADMINISTRATOR','ROLE_USER_ONLINE_ADMIN')")
    public Result logout(@PathVariable String token) {
        List<String> accessToken = Lists.newArrayList();
        accessToken.add(token);
        userOnlineService.logout(accessToken);
        return ResponseResult.resultSuccess("退出成功");
    }

    /**
     * 强制退出
     *
     * @param tokens token
     * @return 是否成功
     */
    @PostMapping("/logout")
    @Log(paramsName = "tokens", module = ModuleName.USER_ONLINE, title = "强制退出", businessType = BusinessTypeEnum.FORCE)
    @PreAuthorize("hasAnyAuthority('user:online:logout','ROLE_ADMINISTRATOR','ROLE_USER_ONLINE_ADMIN')")
    public Result logout(@RequestBody List<String> tokens) {
        userOnlineService.logout(tokens);
        return ResponseResult.resultSuccess("退出成功");
    }
}
