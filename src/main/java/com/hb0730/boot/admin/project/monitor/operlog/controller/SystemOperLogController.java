package com.hb0730.boot.admin.project.monitor.operlog.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.commons.utils.excel.ExcelConstant;
import com.hb0730.boot.admin.commons.utils.excel.ExcelUtils;
import com.hb0730.boot.admin.commons.domain.result.ResponseResult;
import com.hb0730.boot.admin.commons.domain.result.Result;
import com.hb0730.boot.admin.exception.export.ExportException;
import com.hb0730.boot.admin.project.monitor.operlog.model.dto.OperLogDTO;
import com.hb0730.boot.admin.project.monitor.operlog.model.entity.SystemOperLogEntity;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.OperLogParams;
import com.hb0730.boot.admin.project.monitor.operlog.model.vo.SystemOperLogVO;
import com.hb0730.boot.admin.project.monitor.operlog.service.ISystemOperLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
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
public class SystemOperLogController extends AbstractBaseController<Long, SystemOperLogVO, OperLogParams, SystemOperLogEntity> {
    private final ISystemOperLogService systemOperLogService;

    public SystemOperLogController(ISystemOperLogService systemOperLogService) {
        super(systemOperLogService);
        this.systemOperLogService = systemOperLogService;
    }

    /**
     * <p>
     * 获取操作日志
     * </P>
     *
     * @param params 过滤条件
     * @return 分页后的操作日志
     */
    @PostMapping("/all/page")
    @PreAuthorize("hasAnyAuthority('oper:log:query','ROLE_ADMINISTRATOR','ROLE_OPER_LOG_ADMIN')")
    public Result<Page<SystemOperLogVO>> getAllPage(@RequestBody OperLogParams params) {
        Page<SystemOperLogVO> page = systemOperLogService.page(params);
        return ResponseResult.resultSuccess(page);
    }

    /**
     * <p>
     * 删除操作日志
     * </p>
     *
     * @param ids 日志id
     * @return 是否成功
     */
    @Override
    @Log(paramsName = "ids", module = ModuleName.OPER_LOG, title = "删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('oper:log:delete','ROLE_ADMINISTRATOR','ROLE_OPER_LOG_ADMIN')")
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        return super.deleteByIds(ids);
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
    @PreAuthorize("hasAnyAuthority('oper:log:clean','ROLE_ADMINISTRATOR','ROLE_OPER_LOG_ADMIN')")
    public Result<String> clean() {
        List<SystemOperLogEntity> list = systemOperLogService.list();
        if (!CollectionUtils.isEmpty(list)) {
            Set<Long> ids = list.parallelStream().map(SystemOperLogEntity::getId).collect(Collectors.toSet());
            systemOperLogService.removeByIds(ids);
        }
        return ResponseResult.resultSuccess("清除成功");
    }

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param response 响应
     * @param params   过滤参数
     */
    @PostMapping("/export")
    @Log(paramsName = "params", module = ModuleName.OPER_LOG, title = "导出", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("hasAnyAuthority('oper:log:export','ROLE_ADMINISTRATOR','ROLE_OPER_LOG_ADMIN')")
    public void export(HttpServletResponse response, OperLogParams params) {
        List<OperLogDTO> export = systemOperLogService.export(params);
        try {
            Map<String, Object> maps = Maps.newHashMap();
            maps.put(ExcelConstant.FILE_NAME, "oper_log_export");
            maps.put(ExcelConstant.DATA_LIST, export);
            ExcelUtils.writeWeb(response, maps, ExcelTypeEnum.XLS, OperLogDTO.class);
        } catch (Exception e) {
            e.getStackTrace();
            throw new ExportException("操作日志导出失败", e);
        }
    }
}

