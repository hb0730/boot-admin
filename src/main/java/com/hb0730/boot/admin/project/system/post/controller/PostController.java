package com.hb0730.boot.admin.project.system.post.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.annotation.ClassDescribe;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.commons.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.domain.controller.SuperSimpleBaseController;
import com.hb0730.boot.admin.domain.controller.base.IBasePoiController;
import com.hb0730.boot.admin.domain.result.R;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.excel.listener.UploadDataListener;
import com.hb0730.boot.admin.excel.utils.ExcelConstant;
import com.hb0730.boot.admin.excel.utils.ExcelUtils;
import com.hb0730.boot.admin.exceptions.ExportExceptions;
import com.hb0730.boot.admin.project.system.post.model.dto.PostDTO;
import com.hb0730.boot.admin.project.system.post.model.dto.PostExcelDTO;
import com.hb0730.boot.admin.project.system.post.model.entity.PostEntity;
import com.hb0730.boot.admin.project.system.post.model.query.PostParams;
import com.hb0730.boot.admin.project.system.post.service.IPostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 岗位  前端控制器
 *
 * @author bing_huang
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/system/post")
@PreAuth("post")
@ClassDescribe("岗位管理")
public class PostController extends SuperSimpleBaseController<Long, PostDTO, PostParams, PostEntity> implements IBasePoiController<PostParams> {
    private final IPostService service;

    public PostController(IPostService service) {
        super(service);
        this.service = service;
    }

    /**
     * 导出
     *
     * @param response 响应
     * @param params   请求参数,用于导出过滤
     * @throws ExportExceptions 导出异常
     */
    @Override
    @PostMapping("/export")
    @Log(value = "导出", paramsName = {"params"}, businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR','post:export')")
    public void export(HttpServletResponse response, @RequestBody PostParams params) throws ExportExceptions {
        List<PostExcelDTO> export = service.export(params);
        HashMap<String, Object> map = Maps.newHashMap();
        try {
            map.put(ExcelConstant.FILE_NAME, "post_excel");
            map.put(ExcelConstant.DATA_LIST, export);
            ExcelUtils.writeWeb(response, map, ExcelTypeEnum.XLS, PostExcelDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExportExceptions("岗位导出失败", e);
        }
    }

    @Override
    @PostMapping("/upload")
    @Log(value = "导入", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR','post:upload')")
    public Result<String> upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), PostExcelDTO.class, new UploadDataListener<PostExcelDTO>(service)).doReadAll();
        return R.success("导入成功");
    }
}

