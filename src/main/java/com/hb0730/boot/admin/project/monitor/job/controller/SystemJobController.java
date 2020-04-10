package com.hb0730.boot.admin.project.monitor.job.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.utils.PageInfoUtil;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.commons.utils.excel.ExcelConstant;
import com.hb0730.boot.admin.commons.utils.excel.ExcelUtils;
import com.hb0730.boot.admin.commons.utils.excel.UploadDataListener;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.exception.ExportException;
import com.hb0730.boot.admin.project.monitor.job.model.dto.JobExportDto;
import com.hb0730.boot.admin.project.monitor.job.model.entity.SystemJobEntity;
import com.hb0730.boot.admin.project.monitor.job.model.vo.JobParams;
import com.hb0730.boot.admin.project.monitor.job.model.vo.SystemJobVO;
import com.hb0730.boot.admin.project.monitor.job.service.ISystemJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
public class SystemJobController extends BaseController {
    @Autowired
    private ISystemJobService systemJobService;


    /**
     * <p>
     * 查询定时任务
     * </p>
     *
     * @param page     页数
     * @param pageSize 数量
     * @param params   过滤条件
     * @return 分页后的定时任务
     */
    @PostMapping("/all/page/{page}/{pageSize}")
    public Result getAllPage(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody JobParams params) {
        PageHelper.startPage(page, pageSize);
        QueryWrapper<SystemJobEntity> queryWrapper = query(params);
        PageInfo<SystemJobEntity> pageInfo = new PageInfo<>(systemJobService.list(queryWrapper));
        PageInfo<SystemJobVO> info = PageInfoUtil.toBean(pageInfo, SystemJobVO.class);
        return ResponseResult.resultSuccess(info);
    }

    /**
     * 获取全部定时任务
     *
     * @return 定时任务
     */
    @GetMapping("/all")
    public Result getAll() {
        List<SystemJobEntity> list = systemJobService.list();
        List<SystemJobVO> vos = BeanUtils.transformFromInBatch(list, SystemJobVO.class);
        return ResponseResult.resultSuccess(vos);
    }

    /**
     * <p>
     * 新增任务调度
     * </p>
     *
     * @param vo 定时任务信息
     * @return 是否成功
     */
    @PostMapping("/save")
    @Log(paramsName = "vo", module = ModuleName.JOB, title = "新增", businessType = BusinessTypeEnum.INSERT)
    public Result save(@Validated @RequestBody SystemJobVO vo) {
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
    @PostMapping("/update/{id}")
    @Log(paramsName = "vo", module = ModuleName.JOB, title = "修改", businessType = BusinessTypeEnum.UPDATE)
    public Result update(@PathVariable Long id, @Validated @RequestBody SystemJobVO vo) {
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
    @Log(module = ModuleName.JOB, title = "删除", businessType = BusinessTypeEnum.DELETE)
    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
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
    @Log(paramsName = "id", module = ModuleName.JOB, title = "删除", businessType = BusinessTypeEnum.DELETE)
    @PostMapping("/delete")
    public Result deleteById(@RequestBody List<Long> id) {
        if (CollectionUtils.isEmpty(id)) {
            return ResponseResult.resultFall("请选择");
        }
        systemJobService.removeByIds(id);
        return ResponseResult.resultSuccess("修改成功");
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
    public Result upload(MultipartFile file) throws IOException {
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
    public void export(HttpServletResponse response, @RequestBody JobParams params) {
        QueryWrapper<SystemJobEntity> queryWrapper = query(params);
        List<SystemJobEntity> entities = systemJobService.list(queryWrapper);
        List<JobExportDto> exportDtos = BeanUtils.transformFromInBatch(entities, JobExportDto.class);
        Map<String, Object> maps = Maps.newHashMap();
        maps.put(ExcelConstant.FILE_NAME, "job_export");
        maps.put(ExcelConstant.DATA_LIST, exportDtos);
        try {
            ExcelUtils.writeWeb(response, maps, ExcelTypeEnum.XLS, JobExportDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExportException("导出任务调度失败", e);
        }
    }

    /**
     * <p>
     * 过滤条件
     * </p>
     *
     * @param params 过滤参数
     * @return 过滤
     */
    private QueryWrapper<SystemJobEntity> query(JobParams params) {
        QueryWrapper<SystemJobEntity> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(params)) {
            if (Objects.nonNull(params.getNumber())) {
                queryWrapper.eq(SystemJobEntity.NUMBER, params.getNumber());
            }
            if (Objects.nonNull(params.getName())) {
                queryWrapper.like(SystemJobEntity.NAME, params.getName());
            }
            if (Objects.nonNull(params.getEnabled())) {
                queryWrapper.eq(SystemJobEntity.IS_ENABLED, params.getEnabled());
            }
        }
        return queryWrapper;
    }
}

