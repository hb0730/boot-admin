package com.hb0730.boot.admin.project.img.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb0730.boot.admin.commons.web.controller.BaseController;
import com.hb0730.boot.admin.commons.web.response.ResponseResult;
import com.hb0730.boot.admin.commons.web.response.Result;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgParams;
import com.hb0730.boot.admin.project.img.model.vo.BaseImgVO;
import com.hb0730.boot.admin.project.img.service.IBaseImgService;
import lombok.AllArgsConstructor;
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

    @PostMapping("/list/page")
    public Result<Page<BaseImgVO>> page(@RequestBody BaseImgParams params) {
        Page<BaseImgVO> page = service.page(params);
        return ResponseResult.resultSuccess(page);
    }

    @PostMapping("/list")
    public Result<List<BaseImgVO>> list(@RequestBody BaseImgParams params) {
        List<BaseImgVO> list = service.list(params);
        return ResponseResult.resultSuccess(list);
    }

    @GetMapping("/delete/{id}")
    public Result<String> deleteById(@PathVariable Long id) {
        service.removeById(id);
        return ResponseResult.resultSuccess("删除成功");
    }

    @PostMapping("/delete")
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        service.removeByIds(ids);
        return ResponseResult.resultSuccess("删除成功");
    }

    @PostMapping("/upload")
    public Result<String> upload(@RequestPart("file") MultipartFile file) {
        service.upload(file);
        return ResponseResult.resultSuccess("上传成功");
    }

    @PostMapping("/uploads")
    public Result<String> uploads(@RequestPart("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            service.upload(file);
        }
        return ResponseResult.resultSuccess("上传成功");
    }
}

