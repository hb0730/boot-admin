package com.hb0730.modules.sys.controller;

import com.hb0730.base.R;
import com.hb0730.commons.JR;
import com.hb0730.commons.JsfPage;
import com.hb0730.rpc.sys.system.domain.NoticeDto;
import com.hb0730.rpc.sys.system.domain.query.NoticeQuery;
import com.hb0730.rpc.sys.system.service.NoticeRpcService;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
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

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/2
 */
@RestController
@RequestMapping("/sys/notice")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：系统公告管理")
public class NoticeController {
    private final NoticeRpcService noticeRemoteRpcService;

    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:notice:query')")
    public R<JsfPage<NoticeDto>> page(NoticeQuery query) {
        JR<JsfPage<NoticeDto>> jr = noticeRemoteRpcService.page(query);
        return ResponseUtil.converter(jr);
    }


    /**
     * 新增公告
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "新增公告")
    @PreAuthorize("hasAnyAuthority('sys:notice:save')")
    public R<String> save(@Valid @RequestBody NoticeDto dto) {
        String username = SecurityUtil.getUsername();
        dto.setCreated(new Date());
        dto.setCreatedBy(username);
        JR<String> jr = noticeRemoteRpcService.save(dto);
        return ResponseUtil.converter(jr);
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
        String username = SecurityUtil.getUsername();
        dto.setModified(new Date());
        dto.setModifiedBy(username);
        JR<String> jr = noticeRemoteRpcService.updateById(dto);
        return ResponseUtil.converter(jr);
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
        noticeRemoteRpcService.deleteByIds(ids);
        return R.OK();
    }


}
