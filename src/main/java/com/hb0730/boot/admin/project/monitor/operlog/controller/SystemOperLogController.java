package com.hb0730.boot.admin.project.monitor.operlog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.SystemOperLogVO;
import com.hb0730.boot.admin.project.monitor.operlog.service.ISystemOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants.REQUEST_OPERLOG;

/**
 * <p>
 * 业务操作日志  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-02
 */
@RestController
@RequestMapping(REQUEST_OPERLOG)
public class SystemOperLogController extends BaseController {
    @Autowired
    private ISystemOperLogService systemOperLogService;

    /**
     * <p>
     * 获取操作日志
     * </P>
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   过滤条件
     * @return 分页后的操作日志
     */
    @PostMapping("/all/page/{page}/{pageSize}")
    public Result getAllPage(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody OperLogParams params) {
        PageHelper.startPage(page, pageSize);
        PageInfo<SystemOperLogEntity> pageInfo = new PageInfo<>(systemOperLogService.list());
        PageInfo<SystemOperLogVO> info = PageInfoUtil.toBean(pageInfo, SystemOperLogVO.class);
        return ResponseResult.resultSuccess(info);
    }
}

