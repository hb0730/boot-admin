package com.hb0730.boot.admin.project.img.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.domain.result.ResponseResult;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.model.constants.ModuleName;
import com.hb0730.boot.admin.model.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.oss.model.UploadResult;
import com.hb0730.boot.admin.project.img.model.entity.BaseImgEntity;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgParams;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgVO;
import com.hb0730.boot.admin.project.img.service.IBaseImgService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.hb0730.boot.admin.model.constants.RequestMappingNameConstants.REQUEST_BASE_IMG;

/**
 * <p>
 * 图库  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-06-30
 */
@RestController
@RequestMapping(REQUEST_BASE_IMG)
public class BaseImgController extends AbstractBaseController<Long, BaseImgVO, BaseImgParams, BaseImgEntity> {
    private final IBaseImgService service;

    public BaseImgController(IBaseImgService service) {
        super(service);
        this.service = service;
    }

    /**
     * 查询
     *
     * @param params 请求
     * @return 分页列表
     */
    @Override
    @PreAuthorize("hasAnyRole('base:image:query','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    public Result<Page<BaseImgVO>> page(@RequestBody BaseImgParams params) {
        return super.page(params);
    }

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    @Override
    public Result<List<BaseImgVO>> list(@RequestBody BaseImgParams params) {
        return super.list(params);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @Override
    @PreAuthorize("hasAnyRole('base:image:delete','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    @Log(module = ModuleName.IMAGE, title = "删除", businessType = BusinessTypeEnum.DELETE)
    public Result<String> deleteById(@PathVariable("id") Long id) {
        return super.deleteById(id);
    }

    /**
     * 删除
     *
     * @param ids id集合
     * @return 是否成功
     */
    @Override
    @PreAuthorize("hasAnyRole('base:image:delete','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    @Log(paramsName = "ids", module = ModuleName.IMAGE, title = "删除", businessType = BusinessTypeEnum.DELETE)
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        return super.deleteByIds(ids);
    }

    /**
     * 上传
     *
     * @param file 文件
     * @return 是否成功
     */
    @PreAuthorize("hasAnyRole('base:image:upload','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    @Log(module = ModuleName.IMAGE, title = "上传", businessType = BusinessTypeEnum.EXECUTOR, isSaveRequestData = false)
    @PostMapping("/upload")
    public Result<UploadResult> upload(@RequestPart("file") MultipartFile file) {
        UploadResult result = service.upload(file);
        return ResponseResult.resultSuccess(result);
    }

    /**
     * 上传
     *
     * @param files 文件
     * @return 是否成功
     */
    @PreAuthorize("hasAnyRole('base:image:upload','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    @Log(module = ModuleName.IMAGE, title = "上传", businessType = BusinessTypeEnum.EXECUTOR, isSaveRequestData = false)
    @PostMapping("/uploads")
    public Result<List<UploadResult>> uploads(@RequestPart("files") MultipartFile[] files) {
        List<UploadResult> results = Lists.newArrayList();
        for (MultipartFile file : files) {
            UploadResult result = service.upload(file);
            results.add(result);
        }
        return ResponseResult.resultSuccess(results);
    }
}

