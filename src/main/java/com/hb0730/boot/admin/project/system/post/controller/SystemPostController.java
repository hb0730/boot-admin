package com.hb0730.boot.admin.project.system.post.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.annotation.Log;
import com.hb0730.boot.admin.commons.constant.ModuleName;
import com.hb0730.boot.admin.commons.constant.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.commons.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.commons.utils.excel.ExcelConstant;
import com.hb0730.boot.admin.commons.utils.excel.ExcelUtils;
import com.hb0730.boot.admin.commons.utils.excel.UploadDataListener;
import com.hb0730.boot.admin.commons.domain.result.ResponseResult;
import com.hb0730.boot.admin.commons.domain.result.Result;
import com.hb0730.boot.admin.exception.export.ExportException;
import com.hb0730.boot.admin.project.system.post.model.dto.PostExcelDto;
import com.hb0730.boot.admin.project.system.post.model.entity.SystemPostEntity;
import com.hb0730.boot.admin.project.system.post.model.vo.PostParams;
import com.hb0730.boot.admin.project.system.post.model.vo.SystemPostVO;
import com.hb0730.boot.admin.project.system.post.service.ISystemPostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.hb0730.boot.admin.commons.constant.RequestMappingNameConstants.REQUEST_POST;

/**
 * <p>
 * 系统岗位  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-28
 */
@RestController
@RequestMapping(REQUEST_POST)
public class SystemPostController extends AbstractBaseController<Long, SystemPostVO, PostParams, SystemPostEntity> {
    private final ISystemPostService systemPostService;

    public SystemPostController(ISystemPostService systemPostService) {
        super(systemPostService);
        this.systemPostService = systemPostService;
    }

    /**
     * <p>
     * 保存
     * </p>
     *
     * @param vo 岗位
     * @return 是否成功
     */
    @Override
    @Log(paramsName = {"vo"}, module = ModuleName.POST, title = "岗位保存", businessType = BusinessTypeEnum.INSERT)
    @PreAuthorize("hasAnyAuthority('post:save','ROLE_ADMINISTRATOR','ROLE_POST_ADMIN')")
    public Result<String> save(@RequestBody SystemPostVO vo) {
        return super.save(vo);
    }

    /**
     * <p>
     * 获取分页后的岗位
     * </p>
     *
     * @param params 过滤参数
     * @return 分页后的岗位
     * @since v2.0
     */
    @PostMapping("/all/page")
    @PreAuthorize("hasAnyAuthority('post:query','ROLE_ADMINISTRATOR','ROLE_POST_ADMIN')")
    public Result<Page<SystemPostVO>> getPostPage(@RequestBody PostParams params) {
        Page<SystemPostVO> page = systemPostService.page(params);
        return ResponseResult.resultSuccess(page);
    }

    /**
     * <p>
     * 获取岗位
     * </p>
     *
     * @param params 过滤条件
     * @return 岗位信息
     * @since v2.0
     */
    @PostMapping("/all")
    public Result<List<SystemPostVO>> getPost(@RequestBody PostParams params) {
        List<SystemPostVO> list = systemPostService.list(params);
        return ResponseResult.resultSuccess(list);
    }

    /**
     * <p>
     * 更新岗位
     * </p>
     *
     * @param id 岗位id
     * @param vo 岗位信息
     * @return 是否成功
     */
    @Override
    @Log(paramsName = {"vo"}, module = ModuleName.POST, title = "岗位修改", businessType = BusinessTypeEnum.UPDATE)
    @PreAuthorize("hasAnyAuthority('post:update','ROLE_ADMINISTRATOR','ROLE_POST_ADMIN')")
    public Result<String> updateById(@PathVariable("id") Long id, @RequestBody SystemPostVO vo) {
        vo.setId(id);
        return super.updateById(id, vo);
    }

    /**
     * 删除
     *
     * @param id 岗位id
     * @return 是否成功
     */
    @Override
    @Log(module = ModuleName.POST, title = "岗位删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('post:delete','ROLE_ADMINISTRATOR','ROLE_POST_ADMIN')")
    public Result<String> deleteById(@PathVariable("id") Long id) {
        return super.deleteById(id);
    }

    /**
     * <p>
     * 删除
     * </P>
     *
     * @param ids 岗位id
     * @return 是否成功
     */
    @Override
    @Log(module = ModuleName.POST, title = "岗位删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('post:delete','ROLE_ADMINISTRATOR','ROLE_POST_ADMIN')")
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        return super.deleteByIds(ids);
    }

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param response 响应
     * @param params   过滤条件
     */
    @PostMapping("/export")
    @Log(paramsName = "params", module = ModuleName.POST, title = "岗位导出", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("hasAnyAuthority('post:export','ROLE_ADMINISTRATOR','ROLE_POST_ADMIN')")
    public void export(HttpServletResponse response, @RequestBody PostParams params) {
        List<PostExcelDto> export = systemPostService.export(params);
        try {
            Map<String, Object> maps = Maps.newHashMap();
            maps.put(ExcelConstant.FILE_NAME, "post_export");
            maps.put(ExcelConstant.DATA_LIST, export);
            ExcelUtils.writeWeb(response, maps, ExcelTypeEnum.XLS, PostExcelDto.class);
        } catch (Exception e) {
            e.getStackTrace();
            throw new ExportException("岗位导出失败", e);
        }
    }

    /**
     * <p>
     * 岗位导入
     * </p>
     *
     * @param file file
     * @return 是否成功
     */
    @PostMapping("/upload")
    @Log(module = ModuleName.POST, title = "岗位导入", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("hasAnyAuthority('post:upload','ROLE_ADMINISTRATOR','ROLE_POST_ADMIN')")
    public Result<String> upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), PostExcelDto.class, new UploadDataListener(systemPostService)).sheet().doRead();
        return ResponseResult.resultSuccess("导入成功");
    }


}

