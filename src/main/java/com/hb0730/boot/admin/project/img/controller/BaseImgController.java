package com.hb0730.boot.admin.project.img.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.oss.model.UploadResult;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgParams;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgVO;
import com.hb0730.boot.admin.project.img.service.IBaseImgService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants.REQUEST_BASE_IMG;

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
@AllArgsConstructor
public class BaseImgController extends BaseController {
    private final IBaseImgService service;

    /**
     * 查询
     *
     * @param params 请求
     * @return 分页列表
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAnyRole('base:image:query','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    public Result<Page<BaseImgVO>> page(@RequestBody BaseImgParams params) {
        Page<BaseImgVO> page = service.page(params);
        return ResponseResult.resultSuccess(page);
    }

    /**
     * 列表查询
     *
     * @param params 请求参数
     * @return 列表
     */
    @PostMapping("/list")
    public Result<List<BaseImgVO>> list(@RequestBody BaseImgParams params) {
        List<BaseImgVO> list = service.list(params);
        return ResponseResult.resultSuccess(list);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('base:image:delete','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    @Log(module = ModuleName.IMAGE, title = "删除", businessType = BusinessTypeEnum.DELETE)
    public Result<String> deleteById(@PathVariable Long id) {
        service.removeById(id);
        return ResponseResult.resultSuccess("删除成功");
    }

    /**
     * 删除
     *
     * @param ids id集合
     * @return 是否成功
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('base:image:delete','ROLE_ADMINISTRATOR','ROLE_BASE_ADMIN')")
    @Log(paramsName = "ids", module = ModuleName.IMAGE, title = "删除", businessType = BusinessTypeEnum.DELETE)
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return ResponseResult.resultSuccess("删除成功");
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

