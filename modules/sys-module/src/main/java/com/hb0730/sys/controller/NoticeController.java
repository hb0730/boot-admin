package com.hb0730.sys.controller;

import com.hb0730.base.R;
import com.hb0730.common.api.JsfPage;
import com.hb0730.sys.domain.dto.NoticeDto;
import com.hb0730.sys.domain.query.NoticeQuery;
import com.hb0730.sys.service.SysNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/29
 */
@RestController
@RequestMapping("/sys/notice")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：系统公告管理")
public class NoticeController {
    private final SysNoticeService sysNoticeService;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:notice:query')")
    public R<JsfPage<NoticeDto>> page(NoticeQuery query) {
        JsfPage<NoticeDto> res = sysNoticeService.page(query);
        return R.OK(res);
    }

    /**
     * 保存
     *
     * @param dto dto
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "新增公告")
    @PreAuthorize("hasAnyAuthority('sys:notice:save')")
    public R<String> save(@Valid @RequestBody NoticeDto dto) {
        sysNoticeService.save(dto);
        return R.OK();
    }

    /**
     * 更新公告
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "更新公告")
    @PreAuthorize("hasAnyAuthority('sys:notice:update')")
    public R<String> updateById(@Valid @RequestBody NoticeDto dto) {
        sysNoticeService.updateById(dto);
        return R.OK();
    }

    /**
     * 根据ID删除公告
     *
     * @param ids .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除公告")
    @PreAuthorize("hasAnyAuthority('sys:notice:delete')")
    public R<String> deleteByIds(@RequestBody List<String> ids) {
        sysNoticeService.deleteByIds(ids);
        return R.OK();
    }
}
