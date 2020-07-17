package com.hb0730.boot.admin.project.monitor.logininfo.controller;


import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.annotation.Log;
import com.hb0730.boot.admin.model.constants.ModuleName;
import com.hb0730.boot.admin.model.enums.BusinessTypeEnum;
import com.hb0730.boot.admin.domain.controller.AbstractBaseController;
import com.hb0730.boot.admin.utils.excel.ExcelConstant;
import com.hb0730.boot.admin.utils.excel.ExcelUtils;
import com.hb0730.boot.admin.domain.result.ResponseResult;
import com.hb0730.boot.admin.domain.result.Result;
import com.hb0730.boot.admin.exception.export.ExportException;
import com.hb0730.boot.admin.project.monitor.logininfo.model.dto.LoginInfoDTO;
import com.hb0730.boot.admin.project.monitor.logininfo.model.entity.SystemLoginInfoEntity;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.LoginfoParams;
import com.hb0730.boot.admin.project.monitor.logininfo.model.vo.SystemLoginfoVO;
import com.hb0730.boot.admin.project.monitor.logininfo.service.ISystemLoginInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hb0730.boot.admin.model.constants.RequestMappingNameConstants.REQUEST_LOGININFO;

/**
 * <p>
 * 系统登录日志  前端控制器
 * </p>
 *
 * @author bing_huang
 * @since 2020-04-01
 */
@RestController
@RequestMapping(REQUEST_LOGININFO)
public class SystemLoginInfoController extends AbstractBaseController<Long, SystemLoginfoVO, LoginfoParams, SystemLoginInfoEntity> {
    private final ISystemLoginInfoService systemLoginInfoService;

    public SystemLoginInfoController(ISystemLoginInfoService systemLoginInfoService) {
        super(systemLoginInfoService);
        this.systemLoginInfoService = systemLoginInfoService;
    }

    /**
     * <p>
     * 获取日志
     * </p>
     *
     * @param params 过滤参数
     * @return 分页后的日志
     */
    @PostMapping("/all/page")
    @PreAuthorize("hasAnyAuthority('login:log:query','ROLE_ADMINISTRATOR','ROLE_LOGIN_LOG_ADMIN')")
    public Result<Page<SystemLoginfoVO>> getPageAll(@RequestBody LoginfoParams params) {
        Page<SystemLoginfoVO> list = systemLoginInfoService.page(params);
        return ResponseResult.resultSuccess(list);
    }

    /**
     * <p>
     * 登录日志导出
     * </p>
     *
     * @param response 响应
     * @param params   过滤条件
     */
    @PostMapping("/export")
    @Log(paramsName = "params", module = ModuleName.LOGIN_INFO, title = "登录日志导出", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("hasAnyAuthority('login:log:export','ROLE_ADMINISTRATOR','ROLE_LOGIN_LOG_ADMIN')")
    public void export(HttpServletResponse response, @RequestBody LoginfoParams params) {
        List<LoginInfoDTO> export = systemLoginInfoService.export(params);
        Map<String, Object> maps = Maps.newHashMap();
        maps.put(ExcelConstant.FILE_NAME, "login_log");
        maps.put(ExcelConstant.DATA_LIST, export);
        try {
            ExcelUtils.writeWeb(response, maps, ExcelTypeEnum.XLS, LoginInfoDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExportException("导入登录日志失败", e);
        }
    }

    /**
     * 删除
     *
     * @param ids id
     * @return 是否成功
     */
    @Override
    @Log(paramsName = "ids", module = ModuleName.LOGIN_INFO, title = "删除", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("hasAnyAuthority('login:log:delete','ROLE_ADMINISTRATOR','ROLE_LOGIN_LOG_ADMIN')")
    public Result<String> deleteByIds(@RequestBody List<Long> ids) {
        return super.deleteByIds(ids);
    }

    /**
     * <p>
     * 清空
     * </p>
     *
     * @return 是否成功
     */
    @GetMapping("/clean")
    @Log(module = ModuleName.LOGIN_INFO, title = "清空", businessType = BusinessTypeEnum.CLEAN)
    @PreAuthorize("hasAnyAuthority('login:log:clean','ROLE_ADMINISTRATOR','ROLE_LOGIN_LOG_ADMIN')")
    public Result<String> cleanLog() {
        List<SystemLoginInfoEntity> list = systemLoginInfoService.list();
        if (!CollectionUtils.isEmpty(list)) {
            Set<Long> ids = list.parallelStream().map(SystemLoginInfoEntity::getId).collect(Collectors.toSet());
            systemLoginInfoService.removeByIds(ids);
        }
        return ResponseResult.resultSuccess("删除成功");
    }
}

