package com.hb0730.boot.admin.project.monitor.job.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.excel.ExcelConstant;
import com.hb0730.boot.admin.commons.utils.excel.ExcelUtils;
import com.hb0730.boot.admin.commons.utils.excel.UploadDataListener;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.exception.export.ExportException;
import com.hb0730.boot.admin.project.monitor.job.model.dto.JobExportDto;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.project.monitor.job.model.vo.JobParams;
import com.hb0730.boot.admin.project.monitor.job.model.vo.SystemJobVO;
import com.hb0730.boot.admin.project.monitor.job.service.ISystemJobService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants.REQUEST_JOB;

/**
 * <p>
 * 定时任务  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-06
 */
@RestController
@RequestMapping(REQUEST_JOB)
public class SystemJobController extends BaseController<JobParams, SystemJobVO, Long, SystemJobEntity> {
    private final ISystemJobService systemJobService;

    public SystemJobController(ISystemJobService systemJobService) {
        super(systemJobService);
        this.systemJobService = systemJobService;
    }

    /**
     * <p>
     * 查询定时任务
     * </p>
     *
     * @param params 过滤条件
     * @return 分页后的定时任务
     */
    @PostMapping("/all/page")
    @PreAuthorize("hasAnyAuthority('job:query','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<Page<SystemJobVO>> getAllPage(@RequestBody JobParams params) {
        Page<SystemJobVO> page = systemJobService.page(params);
        return ResponseResult.resultSuccess(page);
    }

    /**
     * 获取全部定时任务
     *
     * @return 定时任务
     */
    @GetMapping("/all")
    public Result<List<SystemJobVO>> getAll() {
        List<SystemJobVO> list = systemJobService.list(new JobParams());
        return ResponseResult.resultSuccess(list);
    }

    /**
     * <p>
     * 新增任务调度
     * </p>
     *
     * @param vo 定时任务信息
     * @return 是否成功
     */
    @Override
//    @PostMapping("/save")
    @Log(paramsName = "vo", module = ModuleName.JOB, title = "新增", businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("hasAnyAuthority('job:save','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> save(SystemJobVO vo) {
        SystemJobEntity entity = BeanUtils.transformFrom(vo, SystemJobEntity.class);
        systemJobService.save(entity);
        assert entity != null;
        return ResponseResult.resultSuccess("新增成功");
    }

    /**
     * <p>
     * 修改任务调度
     * </p>
     *
     * @param id 任务id
     * @param vo 定时任务信息
     * @return 是否成功
     */
    @Override
//    @PostMapping("/update/{id}")
    @Log(paramsName = "vo", module = ModuleName.JOB, title = "修改", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('job:update','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> updateById(Long id, SystemJobVO vo) {
        vo.setId(id);
        SystemJobEntity entity = BeanUtils.transformFrom(vo, SystemJobEntity.class);
        systemJobService.updateById(entity);
        assert entity != null;
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据id删除任务
     * </p>
     *
     * @param id id
     * @return 是否成功
     */
    @Override
    @Log(module = ModuleName.JOB, title = "删除", businessType = BusinessTypeEnum.DELETE)
//    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('job:delete','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> deleteById(Long id) {
        systemJobService.removeById(id);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * <p>
     * 根据id删除任务
     * </p>
     *
     * @param id id
     * @return 是否成功
     */
    @Override
    @Log(paramsName = "id", module = ModuleName.JOB, title = "删除", businessType = BusinessTypeEnum.DELETE)
//    @PostMapping("/delete")
    @PreAuthorize("hasAnyAuthority('job:delete','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> deleteByIds(List<Long> id) {
        if (CollectionUtils.isEmpty(id)) {
            return ResponseResult.resultFall("请选择");
        }
        systemJobService.removeByIds(id);
        return ResponseResult.resultSuccess("修改成功");
    }

    /**
     * 立即执行
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/{id}")
    @Log(paramsName = ModuleName.JOB, title = "立即执行", businessType = BusinessTypeEnum.EXECUTOR)
    @PreAuthorize("hasAnyAuthority('job:executor','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> executor(@PathVariable Long id) {
        systemJobService.executor(id);
        return ResponseResult.resultSuccess("执行成功");
    }

    /**
     * <p>
     * 导入
     * </p>
     *
     * @param file 文件
     * @return 是否成功
     */
    @PostMapping("/upload")
    @Log(module = ModuleName.JOB, title = "excel导入", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("hasAnyAuthority('job:upload','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public Result<String> upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), JobExportDto.class, new UploadDataListener(systemJobService)).sheet().doRead();
        return ResponseResult.resultSuccess("导入成功");
    }

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param response 响应
     * @param params   过滤参数
     */
    @Log(paramsName = "params", module = ModuleName.JOB, title = "导出", businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/export")
    @PreAuthorize("hasAnyAuthority('job:export','ROLE_ADMINISTRATOR','ROLE_JOB_ADMIN')")
    public void export(HttpServletResponse response, @RequestBody JobParams params) {
        List<JobExportDto> export = systemJobService.export(params);
        Map<String, Object> maps = Maps.newHashMap();
        maps.put(ExcelConstant.FILE_NAME, "job_export");
        maps.put(ExcelConstant.DATA_LIST, export);
        try {
            ExcelUtils.writeWeb(response, maps, ExcelTypeEnum.XLS, JobExportDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExportException("导出任务调度失败", e);
        }
    }
}

