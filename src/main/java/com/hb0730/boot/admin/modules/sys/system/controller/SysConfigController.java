package com.hb0730.boot.admin.modules.sys.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.data.domain.BasePage;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysConfig;
import com.hb0730.boot.admin.modules.sys.system.model.query.ConfigQuery;
import com.hb0730.boot.admin.modules.sys.system.model.vo.ConfigVO;
import com.hb0730.boot.admin.modules.sys.system.service.SysConfigService;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/12
 */
@RestController
@RequestMapping("/sys/config")
@Tag(name = "系统：系统配置")
@RequiredArgsConstructor
@Validated
public class  SysConfigController {
    private final SysConfigService configService;

    @GetMapping("/query/page")
    @PreAuthorize("hasAuthority('sys:config:query')")
    @Operation(summary = "分页查询")
    public R<BasePage<ConfigVO>> queryPage(HttpServletRequest request, @ParameterObject ConfigQuery query) {
        return configService.queryPage(query);
    }

    @GetMapping("/query/list")
    @Operation(summary = "列表查询")
    public R<List<ConfigVO>> queryList(HttpServletRequest request, @ParameterObject ConfigQuery query) {
        return configService.queryList(query);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:config:save')")
    public R<ConfigVO> save(HttpServletRequest request, @RequestBody ConfigVO vo) {
        String username = SecurityUtil.getCurrentUsername();
        SysConfig config = BeanUtil.toBean(vo, SysConfig.class);
        config.setCreated(LocalDateTime.now());
        config.setCreatedBy(username);
        configService.save(config);
        ConfigVO bean = BeanUtil.toBean(config, ConfigVO.class);
        return R.OK(bean);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:config:update')")
    public R<ConfigVO> updateById(HttpServletRequest request, @PathVariable String id, @RequestBody ConfigVO vo) {
        String username = SecurityUtil.getCurrentUsername();
        SysConfig config = configService.getById(id);
        if (null == config) {
            return R.NG("数据不存在");
        }
        BeanUtil.copyProperties(vo, config);
        config.setModified(LocalDateTime.now());
        config.setModifiedBy(username);
        configService.updateById(config);
        ConfigVO bean = BeanUtil.toBean(config, ConfigVO.class);
        return R.OK(bean);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:config:del')")
    public R<String> delBatch(HttpServletRequest request, @RequestBody List<String> ids) {
        configService.removeByIds(ids);
        return R.OK();
    }

    @PutMapping("/refresh/{id}")
    @Operation(summary = "刷新缓存")
    @PreAuthorize("hasAuthority('sys:config:refresh')")
    public R<String> refreshCache(HttpServletRequest request, @PathVariable String id) {
        return configService.refreshCache(id);
    }
}
