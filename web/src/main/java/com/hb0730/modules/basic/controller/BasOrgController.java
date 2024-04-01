package com.hb0730.modules.basic.controller;

import com.hb0730.base.R;
import com.hb0730.base.utils.TreeUtil;
import com.hb0730.commons.JR;
import com.hb0730.rpc.basic.domain.BasOrgDto;
import com.hb0730.rpc.basic.domain.query.BasOrgQuery;
import com.hb0730.rpc.basic.service.BasOrgRpcService;
import com.hb0730.security.util.SecurityUtil;
import com.hb0730.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @date 2024/3/31
 */
@RestController
@RequestMapping("/bas/org")
@RequiredArgsConstructor
@Tag(name = "履约端：机构管理")
public class BasOrgController {
    private final BasOrgRpcService basOrgRpcService;


    /**
     * 机构树
     *
     * @return .
     */
    @GetMapping("/tree")
    @Operation(summary = "机构树")
    public R<List<BasOrgDto>> tree(BasOrgQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        JR<List<BasOrgDto>> jr = basOrgRpcService.list(query);
        if (!jr.isSuccess()) {
            return R.NG(jr.getMessage());
        }
        List<BasOrgDto> result = jr.getResult();
        return R.OK(TreeUtil.buildTree(result));
    }

    /**
     * 机构信息
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "机构列表查询")
    @PreAuthorize("hasAuthority('bas:org:query')")
    public R<List<BasOrgDto>> list(BasOrgQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        JR<List<BasOrgDto>> jr = basOrgRpcService.listDefaultRootQuery(query);
        return ResponseUtil.converter(jr);
    }

    /**
     * 新增机构
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "新增机构")
    @PreAuthorize("hasAuthority('bas:org:save')")
    public R<String> save(@Valid @RequestBody BasOrgDto dto) {
        String username = SecurityUtil.getUsername();
        String sysCode = SecurityUtil.getSysCode();
        dto.setCreated(new Date());
        dto.setCreatedBy(username);
        dto.setSysCode(sysCode);
        JR<String> jr = basOrgRpcService.save(dto);
        return ResponseUtil.converter(jr);
    }

    /**
     * 修改机构
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改机构")
    @PreAuthorize("hasAuthority('bas:org:update')")
    public R<String> update(@Valid @RequestBody BasOrgDto dto) {
        String id = dto.getId();
        if (id == null) {
            return R.NG("id不能为空");
        }
        String sysCode = SecurityUtil.getSysCode();
        String username = SecurityUtil.getUsername();
        dto.setModified(new Date());
        dto.setModifiedBy(username);
        dto.setSysCode(sysCode);
        JR<String> jr = basOrgRpcService.updateById(dto);
        return ResponseUtil.converter(jr);
    }
}
