package com.hb0730.boot.admin.project.monitor.operlog.controller;


import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.service.ISystemOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return ResponseResult.resultSuccess(systemOperLogService.list(page, pageSize, params));
    }

    /**
     * <p>
     * 删除操作日志
     * </p>
     *
     * @param ids 日志id
     * @return 是否成功
     */
    @PostMapping("/delete")
    @Log(paramsName = "ids", module = ModuleName.OPER_LOG, title = "删除", businessType = BusinessTypeEnum.DELETE)
    public Result deleteByIds(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.resultFall("请选择");
        }
        systemOperLogService.removeByIds(ids);
        return ResponseResult.resultSuccess("删除成功");
    }

    /**
     * <p>
     * 清空操作日志
     * </p>
     *
     * @return 是否成功
     */
    @GetMapping("/clean")
    @Log(module = ModuleName.OPER_LOG, title = "清空", businessType = BusinessTypeEnum.CLEAN)
    public Result clean() {
        List<SystemOperLogEntity> list = systemOperLogService.list();
        if (!CollectionUtils.isEmpty(list)) {
            Set<Long> ids = list.parallelStream().map(SystemOperLogEntity::getId).collect(Collectors.toSet());
            systemOperLogService.removeByIds(ids);
        }
        return ResponseResult.resultSuccess("清除成功");
    }
}

