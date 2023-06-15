package com.hb0730.boot.admin.modules.sys.monitor.controller;

import com.hb0730.boot.admin.base.R;
import com.hb0730.boot.admin.base.util.JsonUtil;
import com.hb0730.boot.admin.config.cache.BootAdminCache;
import com.hb0730.boot.admin.config.cache.DefaultKeyValue;
import com.hb0730.boot.admin.modules.sys.monitor.model.vo.CacheVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 系统缓存
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/14
 */
@RestController
@RequestMapping("/monitor/cache")
@Tag(name = "系统：系统缓存", description = "业务缓存与系统缓存")
@RequiredArgsConstructor
public class CacheController {
    private final BootAdminCache bootAdminCache;

    @GetMapping
    @Operation(summary = "缓存列表")
    @PreAuthorize("hasAuthority('cache:list')")
    public R<List<CacheVO>> queryList(HttpServletRequest request) {
        List<CacheVO> res = Arrays.stream(DefaultKeyValue.values()).map(e -> {
            CacheVO cacheVo = new CacheVO();
            cacheVo.setPrefix(e.getPrefix());
            cacheVo.setName(e.getName());
            cacheVo.setDesc(e.getDesc());
            return cacheVo;
        }).toList();
        return R.OK(res);
    }

    @GetMapping("/query")
    @Operation(summary = "查询缓存")
    @PreAuthorize("hasAuthority('cache:query')")
    public R<CacheVO> query(HttpServletRequest request, CacheVO vo) {
        // 完整缓存：KEY值
        String cacheKey = (vo.getPrefix() == null ? "" : vo.getPrefix() + ":") + vo.getKey();
        boolean exist = bootAdminCache.hasKey(cacheKey);
        if (!exist) {
            return R.NG(String.format("缓存KEY:[%s]不存在", cacheKey));
        }
        // 缓存方式: 默认使用String方式
        String cacheType = StringUtils.isNotBlank(vo.getType()) ? vo.getType() : "String";
        switch (DefaultKeyValue.CacheType.valueOf(cacheType.toUpperCase())) {
            case STRING, OBJECT ->
                // <1> String 缓存
                    vo.setValue(bootAdminCache.getStr(cacheKey).orElse(""));
            case HASHMAP ->
                // <3> Map 缓存
                    bootAdminCache.hmget(cacheKey).ifPresent(e -> vo.setValue(JsonUtil.DEFAULT.toJson(e)));
            case SET ->
                // <4> Set 缓存
                    bootAdminCache.sGet(cacheKey).ifPresent(e -> vo.setValue(JsonUtil.DEFAULT.toJson(e)));
            case LIST ->
                // <5> List 缓存
                    bootAdminCache.lGet(cacheKey, 0, -1).ifPresent(e -> vo.setValue(JsonUtil.DEFAULT.toJson(e)));
            default -> {
            }
        }
        return R.OK(vo);
    }

    @DeleteMapping("/remove")
    @Operation(summary = "删除缓存")
    @PreAuthorize("hasAuthority('cache:remove')")
    public R<String> remove(HttpServletRequest request, @RequestBody @Valid CacheVO vo) {
        String cacheKey = (vo.getPrefix() == null ? "" : vo.getPrefix() + ":") + vo.getKey();
        if (bootAdminCache.hasKey(cacheKey)) {
            bootAdminCache.del(cacheKey);
        }
        return R.OK("");
    }
}
