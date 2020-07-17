package com.hb0730.boot.admin.project.monitor.job.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.utils.excel.ExcelConstant;
import com.hb0730.boot.admin.utils.excel.ExcelUtils;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.domain.result.ResponseResult;
import com.hb0730.boot.admin.commons.domain.result.Result;
import com.hb0730.boot.admin.exception.export.ExportException;
import com.hb0730.boot.admin.project.monitor.job.model.dto.JobLogExportDTO;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobLogEntity;
import com.hb0730.boot.admin.project.monitor.job.model.vo.JobLogParams;
import com.hb0730.boot.admin.project.monitor.job.model.vo.SystemJobLogVO;
import com.hb0730.boot.admin.project.monitor.job.service.ISystemJobLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants.REQUEST_JOB_LOG;

/**
 * <p>
 * 定时任务日志  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-07
 */
@RestController
@RequestMapping(REQUEST_JOB_LOG)
public class SystemJobLogController extends BaseController<JobLogParams, SystemJobLogVO, Long, SystemJobLogEntity> {
    private final ISystemJobLogService systemJobLogService;

    public SystemJobLogController(ISystemJobLogService systemJobLogService) {
        super(systemJobLogService);
        this.systemJobLogService = systemJobLogService;
    }

    /**
     * <p>
     * 分页查询
     * </p>
     *
     * @param params 过滤条件
     * @return 分页后的日志
     */
    @PostMapping("/all/page")
    @PreAuthorize("hasAnyAuthority('job:log:query','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<Page<SystemJobLogVO>> getAllPage(@RequestBody JobLogParams params) {
        Page<SystemJobLogVO> page = systemJobLogService.page(params);
        return ResponseResult.resultSuccess(page);
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
    @Log(paramsName = "params", module = ModuleName.JOBLOG, title = "调度日志导出", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("hasAnyAuthority('job:log:export','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public void export(HttpServletResponse response, @RequestBody JobLogParams params) {
        Map<String, Object> maps = Maps.newHashMap();
        maps.put(ExcelConstant.FILE_NAME, "job_log_export");
        try {
            List<JobLogExportDTO> export = systemJobLogService.export(params);
            maps.put(ExcelConstant.DATA_LIST, export);
            ExcelUtils.writeWeb(response, maps, ExcelTypeEnum.XLS, JobLogExportDTO.class);
        } catch (Exception e) {
            e.getStackTrace();
            throw new ExportException("导出任务调度日志失败", e);
        }
    }

    /**
     * <p>
     * 删除
     * </p>
     *
     * @param ids 日志id
     * @return 是否成功
     */
    @Override
//    @PostMapping("/delete")
    @Log(paramsName = "ids", module = ModuleName.JOBLOG, title = "删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('jog:log:delete','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> deleteByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResponseResult.resultFall("请选择");
        }
        systemJobLogService.removeByIds(ids);
        return ResponseResult.resultSuccess("删除成功");
    }

    /**
     * <p>
     * 清除
     * </p>
     *
     * @param jobId 任务id
     * @return 是否成功
     */
    @GetMapping("/clean/job/{jobId}")
    @Log(module = ModuleName.JOBLOG, title = "清除", businessType = BusinessTypeEnum.CLEAN)
    @PreAuthorize("hasAnyAuthority('job:log:clean','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> cleanByJobId(@PathVariable Long jobId) {
        QueryWrapper<SystemJobLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemJobLogEntity.JOB_ID, jobId);
        systemJobLogService.remove(queryWrapper);
        return ResponseResult.resultSuccess("清除成功");
    }

}

